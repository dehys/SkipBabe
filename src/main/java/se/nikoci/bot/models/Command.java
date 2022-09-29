package se.nikoci.bot.models;

import net.dv8tion.jda.api.Permission;
import org.jetbrains.annotations.NotNull;

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
