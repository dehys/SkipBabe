package com.dehys.ryder.commands;

import com.dehys.ryder.Ryder;
import com.dehys.ryder.models.Command;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.Set;

public class Commands implements Command {
    @Override
    public @NotNull String name() {
        return "commands";
    }

    @Override
    public @NotNull String description() {
        return "Lists all commands available";
    }

    @Override
    public @NotNull Collection<String> aliases() {
        return Set.of(
                "cmds"
        );
    }

    @Override
    public void execute(MessageReceivedEvent event) {

    }

    @Override
    public void execute(SlashCommandEvent event) {
        event.deferReply().complete();
        StringBuilder sb = new StringBuilder();
        for (Class<? extends Command> cmdClass : Ryder.allCommands) {
            sb.append(((Command) cmdClass).name()).append("\n");
        }
        event.getHook().editOriginal(sb.toString()).queue();
    }
}
