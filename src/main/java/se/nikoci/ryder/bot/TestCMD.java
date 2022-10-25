package se.nikoci.ryder.bot;

import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import se.nikoci.ryder.lib.command.Command;
import se.nikoci.ryder.lib.command.CommandType;

@Command(name = "test", description = "this is a test command", permissions = {Permission.ADMINISTRATOR}, type = CommandType.PUBLIC_SLASH)
public class TestCMD{

    public void whatever(SlashCommandEvent event){
        event.deferReply().queue();
        event.getHook().sendMessage("Worked :D").queue();
    }

    public void whatever2(MessageReceivedEvent event){
        event.getChannel().sendMessage("Heh").queue();
    }

}