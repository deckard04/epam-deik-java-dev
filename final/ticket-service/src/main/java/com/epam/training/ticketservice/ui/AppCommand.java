package com.epam.training.ticketservice.ui;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.commands.Quit;

@ShellComponent
public class AppCommand implements Quit.Command {

    @ShellMethod(key = "exit", value = "terminates the program")
    public void terminate() {
        System.exit(0);
    }
}
