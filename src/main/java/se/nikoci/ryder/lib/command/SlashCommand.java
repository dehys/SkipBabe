package se.nikoci.ryder.lib.command;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

import java.util.List;

@FunctionalInterface
public interface SlashCommand {
    void execute(SlashCommandInteractionEvent event, List<String> args);
}
