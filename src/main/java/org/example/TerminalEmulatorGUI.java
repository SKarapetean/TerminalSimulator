package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TerminalEmulatorGUI extends JFrame {
    private JTextArea terminalOutput;
    private JTextField terminalInput;
    private JLabel currentDirectoryLabel;
    private File currentDirectory;

    public TerminalEmulatorGUI() {
        super("Ubuntu Terminal");

        // Set the initial current directory to the user's home directory
        currentDirectory = new File(System.getProperty("user.home"));

        // Create the terminal output area
        terminalOutput = new JTextArea();
        terminalOutput.setEditable(false);
        terminalOutput.setBackground(Color.BLACK);
        terminalOutput.setForeground(Color.WHITE);
        terminalOutput.setFont(new Font("Monospaced", Font.PLAIN, 12));

        // Create the terminal input field
        terminalInput = new JTextField();
        terminalInput.setBackground(Color.BLACK);
        terminalInput.setForeground(Color.WHITE);
        terminalInput.setFont(new Font("Monospaced", Font.PLAIN, 12));

        // Add an ActionListener to process the command
        terminalInput.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String command = terminalInput.getText();
                executeCommand(command);
                terminalInput.setText(""); // Clear the input field
            }
        });

        // Create the current directory label
        currentDirectoryLabel = new JLabel();
        currentDirectoryLabel.setForeground(Color.BLACK);
        updateCurrentDirectoryLabel();

        // Create a scroll pane for the terminal output
        JScrollPane scrollPane = new JScrollPane(terminalOutput);

        // Add the scroll pane, input field, and current directory label to the frame
        add(scrollPane, BorderLayout.CENTER);
        add(terminalInput, BorderLayout.SOUTH);
        add(currentDirectoryLabel, BorderLayout.NORTH);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null); // Center the frame on the screen
    }


    private void executeCommand(String command) {
        if (command.startsWith("cd ")) {
            changeDirectory(command.substring(3));
        } else {
            try {
                Process process = new ProcessBuilder(command.split("\\s+"))
                        .redirectErrorStream(true)
                        .directory(currentDirectory)
                        .start();

                // Read the command output
                InputStream inputStream = process.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                String line;
                StringBuilder output = new StringBuilder();
                while ((line = reader.readLine()) != null) {
                    output.append(line).append("\n");
                }

                // Display the command output in the terminal output area
                terminalOutput.append(">> " + command + "\n"); // Print command
                terminalOutput.append(output.toString()); // Print output
                terminalOutput.append("------------------------------\n"); // Add separator

                // Wait for the command to finish
                process.waitFor();
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void changeDirectory(String directory) {
        String newDirectory;
        if (directory.equals("..")) {
            newDirectory = currentDirectory.getParent();
        } else {
            newDirectory = currentDirectory.getAbsolutePath() + File.separator + directory;
        }

        File file = new File(newDirectory);
        if (file.isDirectory()) {
            currentDirectory = file;
            updateCurrentDirectoryLabel();
        } else {
            terminalOutput.append("Invalid directory: " + directory + "\n");
        }
    }

    private void updateCurrentDirectoryLabel() {
        currentDirectoryLabel.setText("Current Directory: " + currentDirectory.getAbsolutePath());
    }
}