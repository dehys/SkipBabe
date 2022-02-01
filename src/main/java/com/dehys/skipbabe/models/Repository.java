package com.dehys.skipbabe.models;

import lombok.Data;

import java.util.List;

@Data
public class Repository {

    String name;
    String url;
    String description;
    String logo_url;
    List<Contributor> contributors;
    List<Project> projects;

    public Repository(String name, String url, String description, String logo_url, List<Contributor> contributors, List<Project> projects) {
        this.name = name;
        this.url = url;
        this.description = description;
        this.logo_url = logo_url;
        this.contributors = contributors;
        this.projects = projects;
    }

}
