package se.nikoci.bot.commands;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import org.jetbrains.annotations.NotNull;
import se.nikoci.bot.models.Command;

public class HelloCommand implements Command {
    @Override
    public @NotNull String name() {
        return "hello";
    }

    @Override
    public @NotNull String description() {
        return "Says hello! :D";
    }

    @Override
    public void execute(MessageReceivedEvent event) {
        event.getChannel().sendMessage("Hello there").complete();
    }
}
