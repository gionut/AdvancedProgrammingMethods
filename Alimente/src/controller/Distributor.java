package controller;
import model.Aliment;
import repository.Repository;

public class Distributor implements Controller{
    private Repository deposit;

    public Distributor(Repository repository)
    {
        deposit = repository;
    }

    @Override
    public void addProduct(Aliment aliment)
    {
        deposit.addAliment(aliment);
    }

    @Override
    public Aliment[] filterProducts(int lowestPrice)
    {
        Aliment[] aliments = deposit.getAliments();

        int capacity = aliments.length;
        Aliment[] filteredProducts = new Aliment[capacity];

        int size = 0;
        for(Aliment aliment : aliments)
        {
            if(aliment == null)
                break;

            if(aliment.getPrice() > lowestPrice)
                filteredProducts[size++] = aliment;
        }
        return filteredProducts;
    }
}
