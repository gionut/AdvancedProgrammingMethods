package repository;
import exceptions.*;
import model.Aliment;

public interface Repository {

    public void addAliment(Aliment aliment) throws DuplicateElement;
    public void removeAliment(Aliment aliment) throws NonExistingElement;
    public int getSize();
    public Aliment[] getAliments();
}
