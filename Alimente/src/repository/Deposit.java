package repository;
import model.Aliment;
import exceptions.*;

public class Deposit implements Repository{
    private Aliment[] aliments;
    private int size;
    private int capacity;

    public Deposit(int capacity)
    {
        aliments = new Aliment[capacity];
        this.capacity = capacity;
        this.size = 0;
    }

    private void removeOnPosition(int i) {
        for(int j = i +1; j < size; j++)
        {
            aliments[j-1] = aliments[j];
        }
        aliments[size-1] = null;
        size--;
    }

    @Override
    public void removeAliment(Aliment aliment) throws NonExistingElement {
        for(int i = 0; i < size; i++)
        {
            if(aliment.equals(aliments[i]))
            {
                removeOnPosition(i);
                return;
            }
        }
        throw new NonExistingElement("Could not find aliment in the deposit!");
    }

    @Override
    public void addAliment(Aliment aliment) throws DuplicateElement {
        if(size >= capacity)
            throw new NullPointerException("full or empty Array");

        for(int i = 0; i < size; i++)
            if (aliment.equals(aliments[i]))
                throw new DuplicateElement("The aliment is already in the deposit!");

        aliments[size++] = aliment;
    }

    @Override
    public int getSize()
    {
        return this.size;
    }

    @Override
    public Aliment[] getAliments()
    {
        return aliments;
    }

}
