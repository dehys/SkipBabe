package se.nikoci.ryder.bot;

import se.nikoci.ryder.lib.command.CommandHandler;

import javax.security.auth.login.LoginException;

public class Bot {

    public static void main(String[] args) throws LoginException, InterruptedException {

        CommandHandler cmdHandler = new CommandHandler();
        
        cmdHandler.registerCommand(TestCMD.class);
    }

}