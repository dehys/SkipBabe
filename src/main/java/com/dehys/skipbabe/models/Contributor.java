package com.dehys.skipbabe.models;

import lombok.Data;

@Data
public class Contributor {

    String username;
    String profile_picture;

    public Contributor(String username, String profile_picture) {
        this.username = username;
        this.profile_picture = profile_picture;
    }
}
