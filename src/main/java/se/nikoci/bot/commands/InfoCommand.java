package se.nikoci.bot.commands;

import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import org.jetbrains.annotations.NotNull;
import se.nikoci.bot.embeds.InfoEmbed;
import se.nikoci.bot.models.NormalCommand;
import se.nikoci.bot.models.SlashCommand;

public class InfoCommand implements SlashCommand, NormalCommand {
    @Override
    public @NotNull String name() {
        return "info";
    }

    @Override
    public @NotNull String description() {
        return "Information about the Bot and other useful stats.";
    }

    @Override
    public void execute(SlashCommandEvent event) {
        event.deferReply().complete();
        event.getHook().editOriginalEmbeds(InfoEmbed.infoEmbed1()).complete();
    }

    @Override
    public void execute(MessageReceivedEvent event) {
        event.getChannel().sendMessage("This is the info command").complete();
    }
}
