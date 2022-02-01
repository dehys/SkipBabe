package com.dehys.skipbabe;

import com.dehys.skipbabe.models.Command;
import com.dehys.skipbabe.models.Handler;
import net.dv8tion.jda.api.entities.ChannelType;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static com.dehys.skipbabe.Options.*;

public class CommandHandler extends ListenerAdapter implements Handler {

    private static final List<Command> commands = new ArrayList<>();

    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent event) {
        if (!event.getMessage().getContentRaw().startsWith(PREFIX)) return; //checks if the message received does not start with the prefix
        if (event.getAuthor().equals(SkipBabe.jda.getSelfUser())) return; //checks if the author of the message received is the bot itself

        Member member = event.getMember();
        String label = event.getMessage().getContentRaw().split(" ")[0].replaceFirst(PREFIX, "");
        Command command = getCommand(label);

        if (command == null) return; //not a valid command

        if (command.permissions().isEmpty()) { //check if the command requires any permissions
            switch (event.getChannelType()){
                case TEXT -> command.execute(new Command.CommandInformation(Command.CommandType.DISCORD_TEXT.setEvent(event)));
                case PRIVATE -> command.execute(new Command.CommandInformation(Command.CommandType.DISCORD_PRIVATE.setEvent(event)));
                default -> command.execute(new Command.CommandInformation(Command.CommandType.UNIDENTIFIED.setEvent(event)));
            }
            return;
        }

        if (!event.isFromType(ChannelType.TEXT) || member == null) return; //checks if the message was not received from a guild text channel or if member is null

        if (member.hasPermission(command.permissions())) {
            command.execute(new Command.CommandInformation(Command.CommandType.DISCORD_TEXT.setEvent(event)));
        } else {
            event.getChannel().sendMessage(PERMISSION_ERROR).complete();
        }
    }


    @Override
    public void onSlashCommand(@NotNull SlashCommandEvent event) {
        Member member = event.getMember();
        Command command = getCommand(event.getName());

        if (!event.isFromGuild() || member == null) return;
        if (command == null) return;

        if (member.hasPermission(command.permissions())) {
            command.execute(new Command.CommandInformation(Command.CommandType.DISCORD_SLASH.setEvent(event)));
        } else {
            event.getChannel().sendMessage(PERMISSION_ERROR).complete();
        }
    }

    public CommandHandler addCommands(Command ... commandArray){
        List<CommandData> slashCommandData;
        if (ADD_ALIASES_AS_SLASH_COMMAND) {
            slashCommandData = Arrays.stream(commandArray).filter(Command::backingSlash).map(Command::commandData).flatMap(Collection::stream).toList();
        } else {
            slashCommandData = Arrays.stream(commandArray).filter(Command::backingSlash).map(Command::simpleCommandData).flatMap(Collection::stream).toList();
        }
        SkipBabe.jda.getGuilds().forEach(guild -> guild.updateCommands().addCommands(slashCommandData).complete());
        commands.addAll(List.of(commandArray));
        return this;
    }

    public static void addCommand(Command command){
        commands.add(command);
    }

    public static void removeCommand(Command command){
        commands.remove(command);
    }

    public static void removeCommand(String label){
        commands.stream().filter(cmd -> cmd.name().equalsIgnoreCase(label)).forEach(commands::remove);
    }

    public static Command getCommand(String name) {
        return commands.stream().filter(command -> command.name().equalsIgnoreCase(name) || command.aliases().contains(name.toLowerCase())).findFirst().orElse(null);
    }

    public static List<Command> getCommands(){
        return commands;
    }
}
