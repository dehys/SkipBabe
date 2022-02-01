package com.dehys.skipbabe.commands;

import com.dehys.skipbabe.models.Command;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.Set;

public class Help implements Command {
    @Override
    public @NotNull String name() {
        return "help";
    }

    @Override
    public @NotNull String description() {
        return "Show's information about the SkipBabe project";
    }

    @Override
    public @NotNull Collection<String> aliases() {
        return Set.of(
                "info"
        );
    }

    @Override
    public void execute(MessageReceivedEvent event) {



    }

    @Override
    public void execute(SlashCommandEvent event) {

    }
}
