package se.nikoci.bot.commands.division2;

import com.google.gson.Gson;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import net.dv8tion.jda.api.interactions.commands.build.SubcommandData;
import okhttp3.*;
import org.jetbrains.annotations.NotNull;
import se.nikoci.bot.models.Command;
import se.nikoci.bot.models.SlashCommand;

import java.io.IOException;

public class DivisionCommand implements SlashCommand, Command {

    @Override
    public CommandData getCommandData() {
        CommandData commandData = new CommandData(this.getName(), this.getDescription());
        commandData.addSubcommands(
                new SubcommandData("stats", "retrieves statistics of the specified player")
                        .addOption(OptionType.STRING, "platform", "Specify a platform: pc, xbox, ps or stadia", true)
                        .addOption(OptionType.STRING, "username", "Specify username", true)
                ,
                new SubcommandData("profile", "retrieves information of the specified player")
                        .addOption(OptionType.STRING, "platform", "Specify a platform: pc, xbox, ps or stadia", true)
                        .addOption(OptionType.STRING, "username", "Specify username", true)
                ,
                new SubcommandData("news", "displays latest news about Tom Clancy's: The Division 2")
        );
        return commandData;
    }

    @Override
    public void execute(MessageReceivedEvent event) {
        event.getChannel().sendMessage("YES").queue();
    }

    @Override
    public String[] getAliases() {
        return new String[]{
                "division2"
        };
    }

    @Override
    public @NotNull String getName() {
        return "div2";
    }

    @Override
    public @NotNull String getDescription() {
        return "Get stats for players on The Division 2";
    }

    @Override
    public void execute(SlashCommandEvent event) {
        event.deferReply().complete();
        /*OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url("https://public-api.tracker.gg/v2/division-2/standard/profile/uplay/nikoci")
                .addHeader("User-Agent", "OkHttp Bot")
                .addHeader("Authorization", Credentials.basic("myUser", "myPwd"))
                .addHeader("TRN-Api-Key", "085376ea-0e63-433c-97d6-fa9b4bf01dc6")
                .get()
                .build();
        Response response = null;
        try {
            response = client.newCall(request).execute();
        } catch (IOException ignored){}

        if (response != null){
            Gson gson = new Gson();
            try {
                PlayerStats playerStats = gson.fromJson(response.body().string(), PlayerStats.class);

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            //event.getHook().sendMessage(pd.getPlatformInfo().getPlatformUserHandle()).queue();
        }else {
            event.getHook().sendMessage("Failed to retrieve player information.").queue();
        }*/

        event.getHook().sendMessage("this is the division command").queue();

    }
}
