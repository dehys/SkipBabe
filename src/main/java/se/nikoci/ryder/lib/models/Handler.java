package se.nikoci.ryder.lib.models;

import net.dv8tion.jda.api.hooks.ListenerAdapter;
import se.nikoci.ryder.lib.Ryder;

public class Handler extends ListenerAdapter {

    public Ryder instance;

    public Handler setInstance(Ryder ryder){
        this.instance = ryder;
        return this;
    }

    public void subscribe() {
        instance.getJda().addEventListener(this);
    }

    public void unsubscribe(){
        instance.getJda().removeEventListener(this);
    }

}
