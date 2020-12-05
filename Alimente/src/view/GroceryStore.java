package view;

import controller.Controller;
import exceptions.DuplicateElement;
import exceptions.NonExistingElement;
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
        if(price == -1) {
            System.out.println("minimum price: ");
            String priceString = read.next();
            price = Integer.parseInt(priceString);
        }
        Aliment[] aliments = distributor.filterProducts(price);
        for(Aliment aliment: aliments)
        {
            if(aliment == null)
                break;
            System.out.println(aliment);
        }
    }

    public void addAliment() throws DuplicateElement
    {
        System.out.println("base aliment: ");
        String baseAliment = read.next();

        System.out.println("name: ");
        String name = read.next();

        System.out.println("price: ");
        String priceString = read.next();
        int price = Integer.parseInt(priceString);

        Aliment aliment = createAliment(baseAliment, name, price);

        distributor.addProduct(aliment);
    }

    private void removeAliment() throws NonExistingElement {
        System.out.println("base aliment: ");
        String baseAliment = read.next();

        System.out.println("name: ");
        String name = read.next();

        Aliment aliment = createAliment(baseAliment, name, 0);

        distributor.removeProduct(aliment);
    }

    private Aliment createAliment(String baseAliment, String name, int price) {
        Aliment aliment;
        switch(baseAliment)
        {
            case "Flour" -> aliment = new Flour(name, price);
            case "Sugar" -> aliment = new Sugar(name, price);
            case "Salt" -> aliment = new Salt(name, price);
            default -> throw new IllegalStateException("Unexpected value: " + baseAliment);
        }
        return aliment;
    }

    private void populateDeposit() throws DuplicateElement
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
        System.out.println("Give the order, please! The others are waiting...");
        String command;
        command = read.next();

        try {
            populateDeposit();
        }
        catch (DuplicateElement e)
        {}

        while(!command.equals("exit"))
        {
            try {
                switch (command) {
                    case "inventory" -> showAlimentsMoreExpansiveThan(0);
                    case "filter" -> showAlimentsMoreExpansiveThan(-1);
                    case "add" -> addAliment();
                    case "remove"-> removeAliment();
                }
            }
            catch (DuplicateElement e)
            {
                System.out.println(e.toString());
            }
            catch(NonExistingElement e)
            {
                System.out.println(e.toString());
            }

            System.out.println("!!!Give the order, please! The others are waiting...");
            command = read.next();
        }
    }

}
