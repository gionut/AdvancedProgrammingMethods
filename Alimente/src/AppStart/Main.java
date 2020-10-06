package AppStart;

import controller.Controller;
import controller.Distributor;
import repository.Deposit;
import repository.Repository;
import view.GroceryStore;
import view.View;

public class Main {

    public static void main(String[] args) {


        Repository deposit = new Deposit(20);
        Controller distributor = new Distributor(deposit);
        View groceryStore = new GroceryStore(distributor);

        groceryStore.takeOrder();

    }
}
