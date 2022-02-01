package com.dehys.skipbabe;

import com.dehys.skipbabe.models.Project;
import com.dehys.skipbabe.models.Repository;

import java.util.ArrayList;
import java.util.List;

public abstract class Options {

    static String PREFIX = "!";
    static String PERMISSION_ERROR = "You do not have permission to perform this command!";
    static boolean ADD_ALIASES_AS_SLASH_COMMAND = true;

    static List<Repository> repositories = new ArrayList<>();
    static List<Project> projects = new ArrayList<>();

}
