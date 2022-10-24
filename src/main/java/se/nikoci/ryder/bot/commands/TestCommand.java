package se.nikoci.ryder.bot.commands;

import net.dv8tion.jda.api.Permission;
import se.nikoci.ryder.lib.models.Command;

import java.util.Set;

public class TestCommand extends Command {

    public TestCommand(){
        this.setName("test");
        this.setDescription("This is a test command");
        this.setPermissions(Set.of(Permission.MESSAGE_SEND));
    }

}
