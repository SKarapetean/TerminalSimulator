package org.example.commands;

import java.util.HashMap;

public class CommandRegistry {
    private final HashMap<String, Command> commands;

    public CommandRegistry () {
        commands = new HashMap<>();
        commands.put("pwd", new PWD());
        commands.put("cd", new CD());
    }

    public void addCommand(String name, Command command) {
        commands.put(name, command);
    }

    public Command getCommand (String commandName) {
        return commands.get(commandName);
    }
}
