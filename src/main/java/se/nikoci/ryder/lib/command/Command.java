package se.nikoci.ryder.lib.command;

import lombok.*;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor(staticName = "create")
@NoArgsConstructor
@Data
public class Command {

    @NonNull private String name;
    @NonNull private String description;
    private List<Permission> permissions = List.of(Permission.MESSAGE_SEND);
    private Map<String, Command> subcommands = new HashMap<>();
    private boolean executeRecursively = false;

    private SlashCommand commandSlashAction;
    private MessageCommand commandMsgAction;

    public void execute(MessageReceivedEvent e, List<String> args){
        commandMsgAction.execute(e, args);
    }

    public void execute(SlashCommandInteractionEvent e, List<String> args){
        commandSlashAction.execute(e, args);
    }

    public Command onSlash(SlashCommand slashCommand) {
        this.commandSlashAction = slashCommand;
        return this;
    }

    public Command onMessage(MessageCommand messageCommand) {
        this.commandMsgAction = messageCommand;
        return this;
    }

    public Command addSubcommands(Command ... commands) {
        for (Command command : commands) {
            subcommands.put(command.getName(), command);
        }
        return this;
    }
}
