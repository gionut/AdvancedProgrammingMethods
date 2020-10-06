package repository;
import model.Aliment;

public interface Repository {

    public void addAliment(Aliment aliment);
    public Aliment[] getAliments();
}
