package com.dehys.ryder.models;

import com.dehys.ryder.Ryder;

public interface Handler {

    default Handler subscribe(){
        Ryder.jda.addEventListener(this);
        return this;
    }

    default void unsubscribe(){
        Ryder.jda.removeEventListener(this);
    }

}
