package se.nikoci.ryder.lib.command;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.events.Event;
import net.dv8tion.jda.api.events.guild.GuildJoinEvent;
import net.dv8tion.jda.api.events.guild.GuildReadyEvent;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.build.SlashCommandData;
import org.jetbrains.annotations.NotNull;
import se.nikoci.ryder.lib.Ryder;

import java.util.*;

@Getter
@RequiredArgsConstructor(staticName = "create")
public class CommandHandler extends ListenerAdapter {

    private final Map<String, Command> commands = new HashMap<>();

    @NonNull private Ryder instance;

    public void registerCommand(Command... commands) {
        // TODO: register slash command data based on if slash command data exists
        for (Command c : commands) {
            this.commands.put(c.getName(), c);
            addSlashCommandToGuilds(instance.getJda().getGuilds().toArray(new Guild[0]));
        }
    }

    public boolean isValid(Event event) {
        boolean result = false;
        if (event instanceof MessageReceivedEvent mre) {
            var msg = mre.getMessage().getContentRaw();
            var label = msg.split(" ")[0].replaceFirst(instance.getPrefix(), "");

            result = msg.startsWith(instance.getPrefix()) &&
                    commands.containsKey(label) &&
                    !mre.getAuthor().isBot() &&
                    !mre.getAuthor().isSystem() &&
                    !mre.getAuthor().getId().equalsIgnoreCase(instance.getJda().getSelfUser().getId());

        } else if (event instanceof SlashCommandInteractionEvent scie) {
            result = commands.containsKey(scie.getName()) &&
                    !scie.getUser().isBot() &&
                    !scie.getUser().isSystem() &&
                    !scie.getUser().getId().equalsIgnoreCase(instance.getJda().getSelfUser().getId());
        }
        return result;
    }

    public void executeCommand(Event event, ArrayList<String> args, Command command){
        if (command.getSubcommands() != null) {
            for (int i = 0; i < args.size(); i++) {
                var str = args.get(i);

                if (!command.getSubcommands().containsKey(str)) continue;

                args.remove(str);
                var newCommand = command.getSubcommands().get(str);

                if (command.isExecuteRecursively()) {
                    if (event instanceof MessageReceivedEvent mre) command.execute(mre, args);
                    else if (event instanceof SlashCommandInteractionEvent scie) command.execute(scie, args);
                }

                executeCommand(event, args, newCommand);
                return;
            }
        }

        args.remove(0);
        if (event instanceof MessageReceivedEvent mre) command.execute(mre, args);
        else if (event instanceof SlashCommandInteractionEvent scie) command.execute(scie, args);
    }

    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent event) {
        if (!isValid(event)) return;

        var args = new ArrayList<>(Arrays.asList(
                event.getMessage().getContentRaw().replaceFirst(instance.getPrefix(), "").split(" ") //[args without prefix]
        ));

        Command cmd = commands.get(args.get(0));

        if (event.getMember() != null && event.getMember().hasPermission(cmd.getPermissions()) && !cmd.isSlashCommand()) {
            executeCommand(event, args, cmd);
        }
    }

    @Override
    public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent event) {
        if (!isValid(event)) return;

        var args = new ArrayList<String>();
        args.add(event.getName());
        if (event.getSubcommandGroup() != null) args.add(event.getSubcommandGroup());
        if (event.getSubcommandName() != null) args.add(event.getSubcommandName());

        Command cmd = commands.get(event.getName());

        if (event.getMember() != null && event.getMember().hasPermission(cmd.getPermissions()) && cmd.isSlashCommand()) {
            executeCommand(event, args, cmd);
        }
    }

    @Override
    public void onGuildJoin(GuildJoinEvent event) {
        addSlashCommandToGuilds(event.getGuild());
    }

    public void addSlashCommandToGuilds(Guild ... guilds){
        for (Guild guild : guilds) {
            List<SlashCommandData> commandData = new ArrayList<>();
            commands.values().forEach(cmd -> {
                // Only registers slash commands
                System.out.println(guild.getName());
                System.out.println(cmd.getName() + " is slash: " + cmd.isSlashCommand());
                if (cmd.isSlashCommand()) {
                    commandData.add(cmd.getCommandData());
                }
            });

            guild.updateCommands().addCommands(commandData).queue();
        }
    }
}
