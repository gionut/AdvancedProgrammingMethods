package view.commands;

import controller.Controller;
import exception.*;

import java.io.IOException;

public class RunExample extends Command {
    private final Controller controller;

    public RunExample(String key, String description, Controller controller)
    {
        super(key, description);
        this.controller=controller;
    }
    @Override
    public void execute()
    {
        try
        {
            controller.allSteps();
        }
        catch(RuntimeException e)
        {
            System.out.println(e.toString());
        }
    }
}
