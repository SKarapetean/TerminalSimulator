package org.example.commands;

import org.example.TerminalEmulator;
import org.example.fileSystem.Directory;

import java.util.HashSet;


public class CD extends Command {


    private HashSet<String> options;

    public CD() {
        options = new HashSet<String>();
        options.add("-L");
        options.add("-P");
        options.add("-e");
    }

    public String execute (String[] args, String[] options) {
        Directory result;
        if (args != null) {
                Directory start = TerminalEmulator.getRootDirectory();
                args = argsPars(args);
                if (args[0].equals(start.getName())) {
                    result = execute(args, start);
                } else {
                    result = execute(args, TerminalEmulator.getCurrentDirectory());
                }
        } else {
                result = TerminalEmulator.getTilda();
        }

        TerminalEmulator.setCurrentDirectory(result);

        return "";
    }

    private String[] argsPars (String[] args) {
        String[] result;
        if (args.length == 1) {
            String[] directories = args[0].split("/");
            if (args[0].startsWith("/")) {
                result = new String[directories.length];
                result[0] = "/";
                System.arraycopy(directories, 1, result, 1, directories.length - 1);
            } else {
                result = directories;
            }
        } else {
            throw new IllegalArgumentException("too many arguments!");
        }

        return result;
    }


    private Directory execute(String[] path, Directory result) {
        for (String s : path) {
            if (s.equals(".") || s.equals(result.getName()) || result.getSuperDirectory() == null) {

            } else if (s.equals("..")) {
                result = result.getSuperDirectory();
            } else if (result.getSuperDirectory().getName().equals(s)) {
                result = result.getSuperDirectory();
            } else if (result.getSubDirectories().containsKey(s)){
                result = result.getSubDirectories().get(s);
            } else {
                throw new IllegalArgumentException("no such directory!");
            }
        }

        return result;
    }


}
