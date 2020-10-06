package repository;
import model.Aliment;

public class Deposit implements Repository{
    Aliment[] aliments;
    int size;
    int capacity;

    public Deposit(int capacity)
    {
        aliments = new Aliment[capacity];
        this.capacity = capacity;
        this.size = 0;
    }

    @Override
    public void addAliment(Aliment aliment) {
        if(size >= capacity)
            throw new NullPointerException("full or empty Array");

        aliments[size++] = aliment;
    }

    @Override
    public Aliment[] getAliments()
    {
        return aliments;
    }

}
