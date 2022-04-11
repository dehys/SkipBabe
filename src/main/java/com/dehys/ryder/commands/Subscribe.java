package com.dehys.ryder.commands;

import com.dehys.ryder.models.Command;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.Set;

public class Subscribe implements Command {
    @Override
    public @NotNull String name() {
        return "sample";
    }

    @Override
    public @NotNull String description() {
        return "";
    }

    @Override
    public @NotNull Collection<String> aliases() {
        return Set.of(
                ""
        );
    }

    @Override
    public void execute(MessageReceivedEvent event) {

    }

    @Override
    public void execute(SlashCommandEvent event) {

    }
}
