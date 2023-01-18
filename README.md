# Ryder
My own extension library for JDA that handles normal and slash commands for me

## Usage
The code block below showcases how you can use Ryder to implement commands with no effort using lambda expressions.<br>
In this example we are creating a total of 4 commands: `!info`, `!info link`, `/greet hello` and `/greet hi`<br>
Note that we don't get a `/greet` command due to the limitations of a [Slash Command](https://discord.com/developers/docs/interactions/application-commands)

```java
public static void main(String[] args) throws InterruptedException {
    // Creating an instance of Ryder
    var ryder = Ryder.create("<TOKEN HERE>", Set.of(
            GatewayIntent.GUILD_MESSAGES,
            GatewayIntent.DIRECT_MESSAGES,
            GatewayIntent.MESSAGE_CONTENT
    )).online();

    // Creating a normal command. Example: !info
    var normalCmd = Command.create("info", "Shows information");
    normalCmd.onMessage((e, a) -> e.getChannel().sendMessage("Made by Arijan Nikoci").queue());

    // Creating a subcommand, setting the execution of the command using constructor
    var normalSubCmd = Command.create("link", "Link to repository",
            (e, a) -> e.getChannel().sendMessage("https://github.com/nikoci/Ryder").queue());

    // Adding normalSubCmd as a subcommand for normalCmd
    normalCmd.addSubcommands(normalSubCmd);

    // Creation of a slash command. Example: /greet
    var slashCmd = Command.createSlash("greet", "A greeting command");

    // Creating a subcommand, setting the execution of the command using Command#onSlash(SlashCommand)
    var slashSubCmd1 = Command.createSlash("hello", "says hello");
    slashSubCmd1.onSlash((e, a) -> e.reply("Hello there " + e.getUser().getAsTag()).queue());

    // Creating a subcommand, setting the execution of the command using constructor
    var slashSubCmd2 = Command.createSlash("hi", "says hi",
            (e, a) -> e.reply("Hi there " + e.getUser().getAsTag()).queue());

    // Adding slashSubCmd1 and slashSubCmd2 as subcommands for slashCmd
    slashCmd.addSubcommands(slashSubCmd1, slashSubCmd2);


    // Finally registering the commands (Only parents)
    ryder.getCommandHandler().registerCommand(normalCmd, slashCmd);
}
```
