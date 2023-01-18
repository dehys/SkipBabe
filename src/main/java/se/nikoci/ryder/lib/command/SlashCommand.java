package se.nikoci.ryder.lib.command;

import lombok.AllArgsConstructor;
import lombok.Getter;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

import java.util.List;

@FunctionalInterface
public interface SlashCommand {
    void execute(CommandAction commandAction);

    @Getter
    @AllArgsConstructor
    class CommandAction {
        private SlashCommandInteractionEvent event;
        private List<String> args;
        private Command command;
    }
}
