package view;

import controller.Controller;
import model.Aliment;
import model.Flour;
import model.Salt;
import model.Sugar;

import java.util.Scanner;

public class GroceryStore implements View{

    Scanner read=new Scanner(System.in);
    Controller distributor;

    public GroceryStore(Controller controller)
    {
        distributor = controller;
    }

    public void showAlimentsMoreExpansiveThan(int price)
    {
        Aliment[] aliments = distributor.filterProducts(price);
        for(Aliment aliment: aliments)
        {
            if(aliment == null)
                break;
            System.out.println(aliment);
        }
    }

    public void addAliment()
    {
        System.out.println("base aliment: ");
        String baseAliment = read.nextLine();

        System.out.println("name: ");
        String name = read.nextLine();

        System.out.println("price: ");
        int price = read.nextInt();

        Aliment aliment;
        switch(baseAliment)
        {
            case "Flour" -> aliment = new Flour(name, price);
            case "Sugar" -> aliment = new Sugar(name, price);
            case "Salt" -> aliment = new Salt(name, price);
            default -> throw new IllegalStateException("Unexpected value: " + baseAliment);
        }

        distributor.addProduct(aliment);

    }

    private void populateDeposit()
    {
        Aliment flour = new Flour("plainFlour", 10);
        Aliment flour000 = new Flour("refinedFlour",20);
        Aliment sugar = new Sugar("sugar", 20);
        Aliment brownSugar = new Sugar("brownSugar",37);
        Aliment salt = new Salt("salt",7);
        Aliment HimalayaPinkSalt = new Salt("HimalayaPinkSalt",55);

        distributor.addProduct(flour);
        distributor.addProduct(flour000);
        distributor.addProduct(sugar);
        distributor.addProduct(brownSugar);
        distributor.addProduct(salt);
        distributor.addProduct(HimalayaPinkSalt);
    }

    @Override
    public void takeOrder()
    {
        System.out.println("Give the order, please! The other are waiting...");
        String command;
        command = read.nextLine();

        populateDeposit();

        while(!command.equals("exit"))
        {
            try {
                switch (command) {
                    case "all" -> showAlimentsMoreExpansiveThan(0);
                    case "filter" -> showAlimentsMoreExpansiveThan(20);
                    case "add" -> addAliment();
                }
            }
            catch (NullPointerException e)
            {
                System.out.println(e.toString());
            }

            System.out.println("Give the order, please! The other are waiting...");
            command = read.nextLine();
        }


    }
}
