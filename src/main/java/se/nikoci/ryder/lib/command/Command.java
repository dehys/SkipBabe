package se.nikoci.ryder.lib.command;

import lombok.*;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.interactions.commands.build.*;
import org.jetbrains.annotations.NotNull;

import java.util.*;

@Getter
@SuppressWarnings("unused")
public class Command {
    @Setter private boolean isSlashCommand;

    @Setter private String name;
    @Setter private String description;

    @Setter private Set<Permission> permissions;
    @Setter private Map<String, Command> subCommands;
    @Setter private boolean executeRecursively;

    private SlashCommand commandSlashAction;
    private MessageCommand commandMsgAction;

    @Setter private SlashCommandData commandData;
    @Setter private Set<SubcommandData> subCommandData;
    @Setter private Set<OptionData> options;

    public SlashCommandData getCommandData(){
        commandData.addOptions(getOptions());
        subCommands.forEach((l, c) ->
                subCommandData.add(new SubcommandData(l, c.getDescription()).addOptions(c.getOptions())));

        /* debug
        System.out.println("getCommandData: " + this.getName());
        for (var a : subcommandData) {
            System.out.println("> " + a.getName());
        }*/
        return commandData.addSubcommands(subCommandData);
    }

    public Command(@NotNull String name, @NotNull String description) {
        this.isSlashCommand = false;

        this.permissions = Set.of(Permission.MESSAGE_SEND);
        this.subCommands = new HashMap<>();
        this.executeRecursively = false;
        this.commandData = Commands.slash(name, description);
        this.subCommandData = new HashSet<>();
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

    public void execute(MessageReceivedEvent event, List<String> args) {
        if (commandMsgAction != null) {
            commandMsgAction.execute(new MessageCommand.CommandAction(event, args, this));
        } else {
            event.getChannel().sendMessage("`Error:` Implementation needed!").queue();
        }
    }

    public void execute(SlashCommandInteractionEvent event, List<String> args){
        if (commandSlashAction != null) {
            commandSlashAction.execute(new SlashCommand.CommandAction(event, args, this));
        } else {
            event.reply("`Error:` Implementation needed!").queue();
        }
    }

    public void onSlash(SlashCommand slashCommand) {
        this.commandSlashAction = slashCommand;
    }

    public void onMessage(MessageCommand messageCommand) {
        this.commandMsgAction = messageCommand;
    }

    public Command addSubCommands(Command ... commands) {
        for (Command command : commands) {
            subCommands.put(command.getName(), command);
        }
        return this;
    }
}
