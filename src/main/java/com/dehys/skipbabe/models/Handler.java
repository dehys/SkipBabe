package com.dehys.skipbabe.models;

import com.dehys.skipbabe.SkipBabe;

public interface Handler {

    default Handler subscribe(){
        SkipBabe.jda.addEventListener(this);
        return this;
    }

    default void unsubscribe(){
        SkipBabe.jda.removeEventListener(this);
    }

}
