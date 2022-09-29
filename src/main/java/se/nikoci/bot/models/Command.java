package se.nikoci.bot.models;

import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import org.jetbrains.annotations.NotNull;
import se.nikoci.bot.Settings;

import java.util.*;

public interface Command {

    @NotNull String name();
    @NotNull default String description(){ return "No description provided for this command"; }
    @NotNull default Collection<String> aliases(){ return new HashSet<>(); }

    default Collection<Permission> permissions() {
        Set<Permission> permissions = new HashSet<>();
        permissions.add(Permission.MESSAGE_SEND);
        return permissions;
    }

    default void execute(CommandInformation ci){
        switch (ci.getType()) {
            case DISCORD_TEXT, DISCORD_PRIVATE -> execute((MessageReceivedEvent) ci.getEvent());
            case DISCORD_SLASH -> execute((SlashCommandEvent) ci.getEvent());
        }
    }

    default void execute(MessageReceivedEvent event){

    }
    default void execute(SlashCommandEvent event) {

    };

    default List<CommandData> commandData(Settings settings){
        List<CommandData> data = new ArrayList<>();
        data.add(new CommandData(this.name(), this.description()));
        if (settings.isAddingAliases()) {
            for (String alias : aliases()){
                data.add(new CommandData(alias, this.description()));
            }
        }
        return data;
    }

    class CommandInformation {

        private final CommandType commandType;

        public CommandInformation(CommandType commandType){
            this.commandType = commandType;
        }

        public CommandType getType(){
            return this.commandType;
        }

        public Object getEvent(){
            return getType().eventObject;
        }
    }

    enum CommandType {
        UNINDENTIFIED,
        DISCORD_TEXT,
        DISCORD_PRIVATE,
        DISCORD_SLASH;

        public Object eventObject;

        public CommandType setEvent(Object event){
            this.eventObject = event;
            return this;
        }
    }



}
