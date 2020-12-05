package controller;

import exceptions.DuplicateElement;
import exceptions.NonExistingElement;
import model.Aliment;

public interface Controller {

    public void addProduct(Aliment aliment) throws DuplicateElement;
    public void removeProduct(Aliment aliment) throws NonExistingElement;
    public Aliment[] filterProducts(int lowestPrice) ;
}
