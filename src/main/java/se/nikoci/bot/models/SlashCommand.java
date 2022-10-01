package se.nikoci.bot.models;

import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;

public interface SlashCommand extends Request {

    void execute(SlashCommandEvent event);

    default CommandData getCommandData(){
        return new CommandData(this.getName(), this.getDescription());
    }

}
