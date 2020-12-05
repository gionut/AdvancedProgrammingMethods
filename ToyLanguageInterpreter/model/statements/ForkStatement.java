package model.statements;

import exception.TypeCheckerException;
import model.ProgramState;
import model.hashtable.IHashTable;
import model.hashtable.SymbolsTable;
import model.hashtable.TypeEnvironment;
import model.types.IType;
import model.values.IValue;

import java.io.IOException;

public class ForkStatement implements IStatement{
    IStatement statement;

    public ForkStatement(IStatement statement) {
        this.statement = statement;
    }

    @Override
    public ProgramState execute(ProgramState state) {
        ProgramState newProgram = new ProgramState(statement, state.getHeapTable());
        newProgram.setFileTable(state.getFileTable());
        newProgram.setOutput(state.getOutput());

        IHashTable<String, IValue> newSymbolsTable = new SymbolsTable<>();
        for(var entry : state.getSymbolTable().entrySet())
        {
            newSymbolsTable.insert(new String(entry.getKey()), entry.getValue().clone());
        }
        newProgram.setSymbolTable(newSymbolsTable);
        return newProgram;
    }

    @Override
    public IHashTable<String, IType> typeCheck(IHashTable<String, IType> typeEnvironment) throws TypeCheckerException {
        return statement.typeCheck(new TypeEnvironment((TypeEnvironment)typeEnvironment));
    }

    @Override
    public String toString()
    {
        return "fork(" + statement.toString() + "); ";
    }
}
