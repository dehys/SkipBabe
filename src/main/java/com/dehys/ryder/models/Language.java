package com.dehys.ryder.models;


import lombok.Getter;

public enum Language {

    ACTIONSCRIPT3("", "#FFFFF", new String[]{""}),
    APACHE("", "#FFFFF", new String[]{""}),
    APPLESCRIPT("", "#FFFFF", new String[]{""}),
    ASP("", "#FFFFF", new String[]{""}),
    BRAINFUCK("", "#FFFFF", new String[]{""}),
    C("", "#FFFFF", new String[]{""}),
    CFM("", "#FFFFF", new String[]{""}),
    CLOJURE("", "#FFFFF", new String[]{""}),
    CMAKE("", "#FFFFF", new String[]{""}),
    COFFE_SCRIPT("Coffee Script", "#FFFFF", new String[]{"coffeescript", "coffee"}),
    CPP("", "#FFFFF", new String[]{""}),
    CS("", "#FFFFF", new String[]{""}),
    CSHARP("", "#FFFFF", new String[]{""}),
    CSS("", "#FFFFF", new String[]{""}),
    CSV("", "#FFFFF", new String[]{""}),
    BASH("", "#FFFFF", new String[]{""}),
    DIFF("", "#FFFFF", new String[]{""}),
    ELIXIR("", "#FFFFF", new String[]{""}),
    ERB("", "#FFFFF", new String[]{""}),
    GO("", "#FFFFF", new String[]{""}),
    HAML("", "#FFFFF", new String[]{""}),
    HTTP("", "#FFFFF", new String[]{""}),
    JAVA("", "#FFFFF", new String[]{""}),
    JAVASCRIPT("", "#FFFFF", new String[]{""}),
    JSON("", "#FFFFF", new String[]{""}),
    JSX("", "#FFFFF", new String[]{""}),
    LESS("", "#FFFFF", new String[]{""}),
    LOLCODE("", "#FFFFF", new String[]{""}),
    MAKE("", "#FFFFF", new String[]{""}),
    MARKDOWN("", "#FFFFF", new String[]{""}),
    MATLAB("", "#FFFFF", new String[]{""}),
    NGINX("", "#FFFFF", new String[]{""}),
    OBJECTIVEC("", "#FFFFF", new String[]{""}),
    PASCAL("", "#FFFFF", new String[]{""}),
    PHP("", "#FFFFF", new String[]{""}),
    PERL("", "#FFFFF", new String[]{""}),
    PYTHON("", "#FFFFF", new String[]{""}),
    PROFILE("", "#FFFFF", new String[]{""}),
    RUST("", "#FFFFF", new String[]{""}),
    SALT("Salt", "#FFFFF", new String[]{"saltstate"}),
    SHELL("Shellscript", "#FFFFF", new String[]{"sh", "zsh", "bash"}),
    SCSS("", "#FFFFF", new String[]{""}),
    SQL("", "#FFFFF", new String[]{""}),
    SVG("", "#FFFFF", new String[]{""}),
    SWIFT("", "#FFFFF", new String[]{""}),
    RB("Ruby", "#FFFFF", new String[]{"jruby", "ruby"}),
    SMALLTALK("", "#FFFFF", new String[]{""}),
    VIM("", "#FFFFF", new String[]{""}),
    VOLT("", "#FFFFF", new String[]{""}),
    VHDL("", "#FFFFF", new String[]{""}),
    VUE("", "#FFFFF", new String[]{""}),
    XML("", "#FFFFF", new String[]{""}),
    YAML("", "#FFFFF", new String[]{""});

    @Getter private final String name;
    @Getter private final String color;
    @Getter private final String[] aliases;

    Language(String s, String s1, String[] strings) {
        this.name = s;
        this.color = s1;
        this.aliases = strings;
    }
}