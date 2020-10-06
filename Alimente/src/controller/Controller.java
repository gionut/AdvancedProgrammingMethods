package controller;

import model.Aliment;

public interface Controller {

    public void addProduct(Aliment aliment);
    public Aliment[] filterProducts(int lowestPrice);
}
