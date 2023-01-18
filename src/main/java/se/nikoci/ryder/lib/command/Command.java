package se.nikoci.ryder.lib.command;

import lombok.*;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.interactions.commands.build.*;
import org.jetbrains.annotations.NotNull;

import java.util.*;

@Getter
@NoArgsConstructor
@SuppressWarnings("used")
public class Command {
    @Setter private boolean isSlashCommand;

    @Setter private String name;
    @Setter private String description;

    @Setter private Set<Permission> permissions;
    @Setter private Map<String, Command> subcommands;
    @Setter private boolean executeRecursively;

    private SlashCommand commandSlashAction;
    private MessageCommand commandMsgAction;

    @Setter private SlashCommandData commandData;
    @Setter private Set<SubcommandData> subcommandData;
    @Setter private Set<OptionData> options;

    public SlashCommandData getCommandData(){
        commandData.addOptions(getOptions());
        subcommands.forEach((l, c) ->
                subcommandData.add(new SubcommandData(l, c.getDescription()).addOptions(c.getOptions())));

        /* debug
        System.out.println("getCommandData: " + this.getName());
        for (var a : subcommandData) {
            System.out.println("> " + a.getName());
        }*/
        return commandData.addSubcommands(subcommandData);
    }

    private Command(@NotNull String name, @NotNull String description) {
        this.isSlashCommand = false;

        this.permissions = Set.of(Permission.MESSAGE_SEND);
        this.subcommands = new HashMap<>();
        this.executeRecursively = false;
        this.commandData = Commands.slash(name, description);
        this.subcommandData = new HashSet<>();
        this.options = new HashSet<>();

        this.name = name;
        this.description = description;
    }

    public static Command create(@NotNull String name, @NotNull String description) {
        return new Command(name, description);
    }

    public static Command create(@NotNull String name, @NotNull String description, @NotNull MessageCommand exec) {
        var cmd = new Command(name, description);
        cmd.onMessage(exec);
        return cmd;
    }

    public static Command createSlash(@NotNull String name, @NotNull String description) {
        var cmd = new Command(name, description);
        cmd.setSlashCommand(true);
        return cmd;
    }

    public static Command createSlash(@NotNull String name, @NotNull String description, @NotNull SlashCommand exec) {
        var cmd = new Command(name, description);
        cmd.setSlashCommand(true);
        cmd.onSlash(exec);
        return cmd;
    }

    public static Command createSlash(@NotNull String name, @NotNull String description, @NotNull SlashCommandData commandData) {
        var cmd = new Command(name, description);
        cmd.setCommandData(commandData);
        cmd.setSlashCommand(true);
        return cmd;
    }

    public static Command createSlash(@NotNull String name, @NotNull String description, @NotNull SlashCommand exec, @NotNull SlashCommandData commandData) {
        var cmd = new Command(name, description);
        cmd.setSlashCommand(true);
        cmd.onSlash(exec);
        cmd.setCommandData(commandData);
        return cmd;
    }

    public void execute(MessageReceivedEvent e, List<String> args) {
        if (commandMsgAction != null) {
            commandMsgAction.execute(e, args, this);
        } else {
            e.getChannel().sendMessage("`Error:` Implementation needed!").queue();
        }
    }

    public void execute(SlashCommandInteractionEvent e, List<String> args){
        if (commandSlashAction != null) {
            commandSlashAction.execute(e, args, this);
        } else {
            e.reply("`Error:` Implementation needed!").queue();
        }
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
