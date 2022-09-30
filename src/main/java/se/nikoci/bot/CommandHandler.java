package se.nikoci.bot;

import net.dv8tion.jda.api.entities.ChannelType;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import se.nikoci.bot.models.Command;
import se.nikoci.bot.models.Handler;
import se.nikoci.bot.models.NormalCommand;
import se.nikoci.bot.models.SlashCommand;

import java.util.ArrayList;
import java.util.List;

public class CommandHandler extends Handler {

    private final List<Command> commands = new ArrayList<>();

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        if (!event.getMessage().getContentRaw().startsWith(this.instance.getSettings().getPrefix())) return;
        if (event.getAuthor().equals(this.getInstance().getJda().getSelfUser())) return;

        Member member = event.getMember();
        String label = event.getMessage().getContentRaw().split(" ")[0].replaceFirst(this.instance.getSettings().getPrefix(), "");
        NormalCommand command = (NormalCommand) getCommand(label);

        if (!event.isFromType(ChannelType.TEXT) || member == null || command == null) return;

        if (member.hasPermission(command.permissions())){
            command.execute(event); //Execute the command
        } else {
            event.getChannel().sendMessage(this.instance.getSettings().getPermission_error()).queue();
        }

    }

    @Override
    public void onSlashCommand(SlashCommandEvent event) {
        Member member = event.getMember();
        SlashCommand command = (SlashCommand) getCommand(event.getName());

        if (!event.isFromGuild() || member == null || command == null) return;

        if (member.hasPermission(command.permissions())) {
            command.execute(event);
        } else {
            event.getChannel().sendMessage(this.instance.getSettings().getPermission_error()).queue();
        }
    }

    public final void addCommands(Command... commandArray) {
        List<CommandData> slashCommandData = new ArrayList<>();

        for (Command command : commandArray){
            if (command instanceof SlashCommand slashCommand){
                slashCommandData.addAll(slashCommand.CommandData(this.getInstance().getSettings()));
            }
        }

        this.getInstance().getJda()
                .updateCommands()
                .addCommands(slashCommandData)
                .complete();
        commands.addAll(List.of(commandArray));
    }

    public final void removeCommands(Command... commandArray) {
        this.getInstance().getJda().updateCommands().queue();
        for (Command command : commandArray){
            commands.remove(command);
        }
        commands.forEach(this::addCommand);
    }

    public void addCommand(Command command) {
        this.addCommands(command);
    }

    public void removeCommand(Command command) {
        this.removeCommands(command);
    }

    public void removeCommand(String name) {
        Command command = getCommand(name);
        removeCommand(command);
    }

    public Command getCommand(String name) {
        return commands.stream().filter(
                cmd -> cmd.name().equalsIgnoreCase(name) || cmd.aliases().contains(name.toLowerCase())
        ).findFirst().orElse(null);
    }

    public List<Command> getCommands(){
        return commands;
    }
}
