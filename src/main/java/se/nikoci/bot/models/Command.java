package se.nikoci.bot.models;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public interface Command extends Request {

    void execute(MessageReceivedEvent event);

    default String[] getAliases(){ return new String[]{}; }

}
