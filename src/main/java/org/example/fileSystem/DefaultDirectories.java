package org.example.fileSystem;

public enum DefaultDirectories {
    ROOT("/"),
    BOOT("boot"),
    ETC("etc"),
    HOME("home"),
    MEDIA("media"),
    MNT("mnt"),
    OPT("opt"),
    PROC("proc"),
    SUPERROOT("root"),
    RUN("run"),
    SRV("srv"),
    SYS("sys"),
    TMP("tmp"),
    USR("usr"),
    VAR("var");
    private final String path;

    DefaultDirectories(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }
}
