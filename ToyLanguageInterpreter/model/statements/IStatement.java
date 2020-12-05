package model.statements;

import exception.TypeCheckerException;
import model.ProgramState;
import model.hashtable.IHashTable;
import model.types.IType;

import java.io.IOException;

public interface IStatement {
    ProgramState execute(ProgramState state) throws IOException;
    IHashTable<String, IType> typeCheck(IHashTable<String,IType> typeEnvironment) throws TypeCheckerException;
}
