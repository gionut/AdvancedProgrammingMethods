package model.statements;

import exception.TypeCheckerException;
import model.ProgramState;
import model.hashtable.IHashTable;
import model.types.IType;

public class NOPStatement implements IStatement{
    @Override
    public ProgramState execute(ProgramState state) {
        return null;
    }

    @Override
    public IHashTable<String, IType> typeCheck(IHashTable<String, IType> typeEnvironment) {
        return typeEnvironment;
    }

    @Override
    public String toString()
    {
        return "nop";
    }
}
