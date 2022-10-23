package se.nikoci.bot.embeds;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;
import se.nikoci.bot.commands.division2.PlayerStats;

import java.awt.*;

public class Division2Embed {

    public static MessageEmbed ProfileEmbed(PlayerStats playerStats){
        return new EmbedBuilder()
                .setColor(Color.cyan)
                .setThumbnail(playerStats.getData().getPlatformInfo().getAvatarUrl())
                .setDescription("This is some information about the bot.")
                .addField("Field", "some more field", true)
                .build();
    }

    public static MessageEmbed StatsEmbed(){
        return new EmbedBuilder()
                .setTitle("DennisBot by nikoci", "https://github.com/nikoci/Ryder")
                .setColor(Color.PINK)
                .setImage("https://i.imgur.com/E7DtOH3.png")
                .setDescription("This is some information about the bot.")
                .addField("Field", "some more field", true)
                .build();
    }


}
