package bot;

import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import se.nikoci.ryder.lib.command.Command;
import se.nikoci.ryder.lib.command.CommandExecutal;
import se.nikoci.ryder.lib.command.CommandType;

@Command(
        name = "test",
        description = "This is a test command :D",
        permissions = {Permission.ADMINISTRATOR},
        type = {CommandType.NORMAL, CommandType.PUBLIC}
)
public class TestCmd {

    @CommandExecutal
    public void execute(SlashCommandEvent event){

    }
    
}
