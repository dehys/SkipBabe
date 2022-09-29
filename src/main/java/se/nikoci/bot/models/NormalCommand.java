package se.nikoci.bot.models;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public interface NormalCommand extends Command{

    void execute(MessageReceivedEvent event);

}
