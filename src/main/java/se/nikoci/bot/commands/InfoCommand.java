package se.nikoci.bot.commands;

import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import org.jetbrains.annotations.NotNull;
import se.nikoci.bot.embeds.InfoEmbed;
import se.nikoci.bot.models.Command;
import se.nikoci.bot.models.SlashCommand;

public class InfoCommand implements SlashCommand, Command {

    @Override
    public void execute(MessageReceivedEvent event) {
        event.getChannel().sendMessageEmbeds(InfoEmbed.infoEmbed1()).queue();
    }

    @Override
    public String[] getAliases() {
        return new String[]{
                "help"
        };
    }

    @Override
    public @NotNull String getName() {
        return "info";
    }

    @Override
    public @NotNull String getDescription() {
        return "Provides information about the bot itself and the work behind it";
    }

    @Override
    public void execute(SlashCommandEvent event) {
        event.deferReply().complete();
        event.getHook().sendMessageEmbeds(InfoEmbed.infoEmbed1()).queue();
    }
}
