package org.example.commands;

import org.example.TerminalEmulator;

public class PWD extends Command{
    public String execute(String[] args, String[] options) {
        return  TerminalEmulator.getCurrentDirectory().getPath();
    }
}
