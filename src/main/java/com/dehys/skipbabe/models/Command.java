package com.dehys.skipbabe.models;

import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import org.jetbrains.annotations.NotNull;

import java.util.*;

public interface Command {

    default List<CommandData> commandData() {
        List<CommandData> data = new ArrayList<>();
        data.add(new CommandData(this.name(), this.description()));
        for (String alias : aliases()) {
            data.add(new CommandData(alias, this.description()));
        }
        return data;
    }

    default List<CommandData> simpleCommandData(){
        List<CommandData> data = new ArrayList<>();
        data.add(new CommandData(this.name(), this.description()));
        return data;
    }

    default boolean backingSlash(){ return true; }

    @NotNull String name();
    @NotNull default String description() { return "No information found for this command"; }
    @NotNull default Collection<String> aliases() { return new HashSet<>(); }

    default void execute(CommandInformation ci) {
        switch (ci.getType()) {
            case DISCORD_TEXT -> execute((MessageReceivedEvent) ci.getEvent());
            case DISCORD_SLASH -> execute((SlashCommandEvent) ci.getEvent());
        }
    }

    void execute(MessageReceivedEvent event);

    void execute(SlashCommandEvent event);

    default Collection<Permission> permissions() {
        Set<Permission> permissions = new HashSet<>();
        permissions.add(Permission.MESSAGE_SEND);
        return permissions;
    }

    class CommandInformation {

        private final Object eventObject;
        private final CommandType commandType;

        public CommandInformation(CommandType commandType) {
            this.commandType = commandType;
            this.eventObject = commandType.eventObject;
        }

        public CommandType getType() {
            return commandType;
        }

        public Object getEvent() {
            return eventObject;
        }
    }

    enum CommandType {
        UNIDENTIFIED,
        DISCORD_TEXT,
        DISCORD_PRIVATE,
        DISCORD_SLASH;

        public Object eventObject;

        public CommandType setEvent(Object event) {
            this.eventObject = event;
            return this;
        }
    }

}
