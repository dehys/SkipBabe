package com.dehys.skipbabe.models;

import lombok.Data;
import net.dv8tion.jda.api.entities.User;

import java.util.List;

@Data
public class Project {

    String name;
    String url;
    String description;
    String logo_url;
    User discord_author;
    List<Contributor> contributors;
    List<Language> languages;

    public Project(String name, String url, String description, String logo_url, User discord_author, List<Contributor> contributors, List<Language> languages) {
        this.name = name;
        this.url = url;
        this.description = description;
        this.logo_url = logo_url;
        this.discord_author = discord_author;
        this.contributors = contributors;
        this.languages = languages;
    }
}
