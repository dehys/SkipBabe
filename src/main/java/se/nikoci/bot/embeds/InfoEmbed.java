package se.nikoci.bot.embeds;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;

import java.awt.*;

public class InfoEmbed {

    public static MessageEmbed infoEmbed1(){
        return new EmbedBuilder()
                .setTitle("DennisBot by nikoci", "https://github.com/nikoci/Ryder")
                .setColor(Color.PINK)
                .setImage("https://i.imgur.com/E7DtOH3.png")
                .setDescription("This is some information about the bot.")
                .addField("Field", "some more field", true)
                .build();
    }

}
