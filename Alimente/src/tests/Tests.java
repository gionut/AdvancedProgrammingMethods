package tests;

import exceptions.DuplicateElement;
import exceptions.NonExistingElement;
import model.Aliment;
import model.Flour;
import model.Salt;
import repository.Deposit;
import repository.Repository;

public class Tests {

    private void testGetAliments_DepositIsEmpty()
    {
        Repository deposit = new Deposit(5);
        assert(deposit.getAliments()[0] == null);
    }

    private void testGetAliments_DepositIsNotEmpty() throws DuplicateElement
    {
        Repository deposit = new Deposit(5);
        Aliment aliment = new Flour("flour", 13);
        deposit.addAliment(aliment);
        assert(deposit.getAliments()[0].equals(aliment));
    }

    private void testAddAliment_NonExistingAliment() throws DuplicateElement
    {
        Repository deposit = new Deposit(5);
        Aliment aliment = new Flour("flour", 13);
        Aliment aliment1 = new Salt("salt", 15);
        deposit.addAliment(aliment);
        deposit.addAliment(aliment1);
        assert(deposit.getAliments()[0].equals(aliment));
        assert(deposit.getAliments()[1].equals(aliment1));
    }

    private void testAddAliment_ExistingAliment() throws DuplicateElement
    {
        Repository deposit = new Deposit(5);
        Aliment aliment = new Flour("flour", 13);
        deposit.addAliment(aliment);
        try
        {
            deposit.addAliment(aliment);
            assert(false);
        }
        catch(DuplicateElement e)
        {
            assert(true);
        }
    }

    private void testRemoveAliment_ExistingAliment() throws DuplicateElement, NonExistingElement
    {
        Repository deposit = new Deposit(5);
        Aliment aliment = new Flour("flour", 13);
        Aliment aliment1 = new Salt("salt", 15);
        deposit.addAliment(aliment);
        deposit.addAliment(aliment1);
        deposit.removeAliment(aliment);
        assert(deposit.getAliments()[0].equals(aliment1));
        assert(deposit.getSize() == 1);

    }

    private void testRemoveAliment_NonExistingAliment() throws DuplicateElement, NonExistingElement
    {
        Repository deposit = new Deposit(5);
        Aliment aliment = new Flour("flour", 13);
        Aliment aliment1 = new Salt("salt", 15);
        deposit.addAliment(aliment);
        try {
            deposit.removeAliment(aliment1);
            assert(false);
        }
        catch(NonExistingElement e)
        {
            assert(true);
        }
    }

    public void tests()
    {
        try {

            testGetAliments_DepositIsEmpty();
            testGetAliments_DepositIsNotEmpty();
            testAddAliment_NonExistingAliment();
            testAddAliment_ExistingAliment();
            testRemoveAliment_NonExistingAliment();
            testRemoveAliment_ExistingAliment();
            System.out.println("All tests have passed!");
        }
        catch(DuplicateElement e)
        {
            assert(false);
        }
        catch(NonExistingElement e)
        {
            assert(false);
        }
    }

}
