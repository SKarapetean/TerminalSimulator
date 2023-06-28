package org.example;

import javax.swing.*;
import org.example.fileSystem.Permission;

import java.util.Arrays;
import java.util.Scanner;

import static java.lang.Thread.yield;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) {
//        SwingUtilities.invokeLater(() -> {
//            TerminalEmulatorGUI terminalGUI = new TerminalEmulatorGUI();
//            terminalGUI.setVisible(true);
//        });
//        A a = new A();
//        A b = new B();
//        String[] s = b.getClass().getName().split("\\.");
//
//        switch (s[s.length - 1]) {
//            case "A" :
//                System.out.println("a");
//                break;
//            case "B" :
//                System.out.println("b");
//                break;
//        }

        Scanner scanner  = new Scanner(System.in);
        TerminalEmulator te = new TerminalEmulator();
        String s = null;
        while (true) {
            try {
                s = te.execute(scanner.nextLine());
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
            System.out.println(s);
        }

    }
}


