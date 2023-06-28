package org.example;

import org.example.commands.Command;
import org.example.commands.CommandRegistry;

import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class Parser {

    private String[] delimiter;
    private boolean[] flags;
    private CommandRegistry commandRegistry;

    Parser() {
        this.commandRegistry = new CommandRegistry();
        delimiter = new String[]{"\\|", ">", ">>", "\"\"", ":"};
        flags = new boolean[5];
    }

    public String pars(String commandLine) {
        Pattern pattern = Pattern.compile("\\s+(?=([^\"]*\"[^\"]*\")*[^\"]*$)");
        String[] parts = commandLine.split("\\|");
        List<String[]> commands = new ArrayList<>();
        Arrays.stream(parts)
                .forEach(s -> {
                            commands.add(
                                    Stream.of(pattern.split(s))
                                            .map(String::trim)
                                            .toArray(String[]::new)
                            );
                        }
                );
        StringBuilder result = new StringBuilder();
        for (String[] arr : commands) {
            Command command = commandRegistry.getCommand(arr[0]);
            if (command != null) {
                String[] options = Arrays.stream(arr)
                        .filter(str -> str.startsWith("-") || str.startsWith("+"))
                        .toArray(String[]::new);
                String[] args = Arrays.stream(arr)
                        .skip(1)
                        .filter(option -> !Arrays.asList(options).contains(option))
                        .toArray(String[]::new);
                result.append(command.execute(args,options));
            }
        }
        //Arrays.stream(delimiter).forEach(System.out::println);
        commands.forEach(arr -> Arrays.stream(arr).forEach(System.out::println));
        return result.toString();
    }

    private boolean checkCommand() {
        return false;
    }

    private boolean checkOption() {
        return false;
    }
}
