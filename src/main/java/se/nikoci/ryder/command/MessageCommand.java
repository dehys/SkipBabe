package se.nikoci.ryder.command;

import lombok.AllArgsConstructor;
import lombok.Getter;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.util.List;

@FunctionalInterface
public interface MessageCommand {
    void execute(CommandAction commandAction);

    @Getter
    @AllArgsConstructor
    class CommandAction {
        private MessageReceivedEvent event;
        private List<String> args;
        private Command command;
    }
}
