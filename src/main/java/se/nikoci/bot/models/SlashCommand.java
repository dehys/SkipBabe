package se.nikoci.bot.models;

import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import se.nikoci.bot.Settings;

import java.util.ArrayList;
import java.util.List;

public interface SlashCommand extends Command {

    void execute(SlashCommandEvent event);

    default List<CommandData> CommandData(Settings settings){
        List<CommandData> data = new ArrayList<>();
        data.add(new CommandData(this.name(), this.description()));
        if (settings.isAddingAliases()) {
            for (String alias : aliases()){
                data.add(new CommandData(alias, this.description()));
            }
        }
        return data;
    }

}
