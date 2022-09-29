package se.nikoci.bot.models;

import net.dv8tion.jda.api.hooks.ListenerAdapter;
import se.nikoci.bot.Dennis;

public class Handler extends ListenerAdapter {

    public Dennis instance;

    public Dennis getInstance(){
        return instance;
    }

    public Handler setInstance(Dennis dennis){
        this.instance = dennis;
        return this;
    }

    public void subscribe() {
        instance.getJda().addEventListener(this);
    }

    public void unsubscribe(){
        instance.getJda().removeEventListener(this);
    }

}
