package se.nikoci.bot;

import net.dv8tion.jda.api.entities.ChannelType;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import se.nikoci.bot.models.Command;
import se.nikoci.bot.models.Handler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class CommandHandler extends Handler {

    private final List<Command> commands = new ArrayList<>();

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        if (!event.getMessage().getContentRaw().startsWith(this.instance.getSettings().getPrefix())) return;
        if (event.getAuthor().equals(this.getInstance().getJda().getSelfUser())) return;

        Member member = event.getMember();
        String label = event.getMessage().getContentRaw().split(" ")[0].replaceFirst(this.instance.getSettings().getPrefix(), "");
        Command command = getCommand(label);

        if (!event.isFromType(ChannelType.TEXT) || member == null || command == null) return;

        if (member.hasPermission(command.permissions())){
            Command.CommandType commandType;
            switch (event.getChannelType()) {
                case TEXT -> { commandType = Command.CommandType.DISCORD_TEXT; }
                case PRIVATE -> { commandType = Command.CommandType.DISCORD_PRIVATE; }
                default -> { commandType = Command.CommandType.UNINDENTIFIED; }
            }

            command.execute(new Command.CommandInformation(commandType.setEvent(event))); //Execute the command
        } else {
            event.getChannel().sendMessage(this.instance.getSettings().getPermission_error()).queue();
        }

    }

    @Override
    public void onSlashCommand(SlashCommandEvent event) {
        Member member = event.getMember();
        Command command = getCommand(event.getName());

        if (!event.isFromGuild() || member == null || command == null) return;

        if (member.hasPermission(command.permissions())) {
            command.execute(new Command.CommandInformation(Command.CommandType.DISCORD_SLASH.setEvent(event)));
        } else {
            event.getChannel().sendMessage(this.instance.getSettings().getPermission_error()).queue();
        }
    }

    public void addCommands(Command ... commandArray) {
        List<CommandData> slashCommandData;
        slashCommandData = Arrays.stream(commandArray).map(cmd -> cmd.commandData(this.getInstance().getSettings())).flatMap(Collection::stream).toList();

        //Add all slashcommands for all guilds the bot has access to
        this.getInstance().getJda().getGuilds().forEach(guild -> guild.updateCommands().addCommands(slashCommandData).complete());
        commands.addAll(List.of(commandArray));
    }

    public void addCommand(Command command) { commands.add(command); }

    public void removeCommand(Command command) { commands.remove(command); }

    public void removeCommand(String name) { commands.stream().filter(cmd -> cmd.name().equalsIgnoreCase(name)).forEach(commands::remove); }

    public Command getCommand(String name) {
        return commands.stream().filter(
                cmd -> cmd.name().equalsIgnoreCase(name) || cmd.aliases().contains(name.toLowerCase())
        ).findFirst().orElse(null);
    }

    public List<Command> getCommands(){ return commands; }
}
