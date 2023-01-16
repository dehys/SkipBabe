package se.nikoci.ryder.lib.command;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.util.List;

@FunctionalInterface
public interface MessageCommand {
    void execute(MessageReceivedEvent event, List<String> args);
}
