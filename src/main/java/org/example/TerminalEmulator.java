package org.example;

import org.example.commands.CommandRegistry;
import org.example.fileSystem.Directory;
import org.example.commands.Command;
import org.example.fileSystem.FileSystemBuilder;

import java.util.Arrays;

public class TerminalEmulator {
    private static final Directory rootDirectory = FileSystemBuilder.build();
    private static final Directory homeDirectory = rootDirectory.getSubDirectories().get("home");
    private static  Directory currentDirectory = homeDirectory.getSubDirectories().get("defaultUser");
    private static Directory tilda = currentDirectory;
    private Parser parser;


    public TerminalEmulator () {
        this.parser = new Parser();
    }

    public String execute (String commandLine) {
        return  parser.pars(commandLine);
    }

    private String[] extractOptions (String[] args) {
        return Arrays.stream(args)
                .filter(arg -> arg.startsWith("-") || arg.startsWith("+") )
                .toArray(String[]::new);
    }

    public void displayDirectoryStructure () {
        rootDirectory.display();
    }

    public static void setCurrentDirectory (Directory currentDirectory) {
        TerminalEmulator.currentDirectory = currentDirectory;
    }

    public static Directory getCurrentDirectory () {
        return currentDirectory;
    }

    public static Directory getHomeDirectoryDirectory () {
        return homeDirectory;
    }

    public static Directory getRootDirectory () {
        return rootDirectory;
    }

    public static Directory getTilda () {
        return tilda;
    }
}
