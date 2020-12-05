package model;

import exception.*;
import model.hashtable.*;
import model.output_list.IOutput;
import model.output_list.Output;
import model.stack.ExecutionStack;
import model.stack.IExecutionStack;
import model.statements.IStatement;
import model.values.IValue;
import model.values.StringValue;

import java.io.BufferedReader;
import java.io.IOException;

public class ProgramState {
    private IExecutionStack<IStatement> stack;
    private IHashTable<String, IValue> symbolTable;
    private IHashTable<StringValue, BufferedReader> fileTable;
    private IHeapTable heapTable;
    private IOutput<IValue> output;
    private int id;
    private static int index = 0;

    public ProgramState(IStatement statement, IHeapTable heapTable)
    {
        this.stack = new ExecutionStack<>();
        this.symbolTable = new SymbolsTable<>();
        this.fileTable = new FileTable<>();
        this.heapTable = heapTable;
        this.output = new Output<>();
        id = index++;
        stack.push(statement);
    }

    public int getId() {
        return id;
    }

    public void setStack(ExecutionStack<IStatement> newStack)
    {
        this.stack = newStack;
    }

    public void setSymbolTable(IHashTable<String, IValue> newTable)
    {
        this.symbolTable = newTable;
    }

    public void setFileTable(IHashTable<StringValue, BufferedReader> fileTable)
    {
        this.fileTable = fileTable;
    }

    public void setFileTable(IHeapTable heapTable)
    {
        this.heapTable = heapTable;
    }

    public void setOutput(IOutput<IValue> newOutput)
    {
        this.output = newOutput;
    }

    public synchronized void index()
    {
        id = index;
    }

    public ProgramState oneStep() throws IOException, ValueNotMatch, EmptyExecutionStack, DivisionByZero, TypeNotMatch, VariableNotDeclared, InexistentVariable, ProgramException, ExistingVariable, UndefinedKey
    {
       if(stack.isEmpty())
            throw new EmptyExecutionStack();

        IStatement currentStatement = stack.pop();
        return currentStatement.execute(this);

    }

    public boolean isNotCompleted()
    {
        return !stack.isEmpty();
    }

    public IExecutionStack<IStatement> getStack()
    {
        return stack;
    }

    public IHashTable<String, IValue> getSymbolTable() {
        return symbolTable;
    }

    public IHashTable<StringValue, BufferedReader> getFileTable()
    {
        return fileTable;
    }

    public IHeapTable getHeapTable()
    {
        return heapTable;
    }

    public IOutput<IValue> getOutput()
    {
        return output;
    }

    @Override
    public String toString()
    {
        return "Thread: " + id + "\n" + "stack:\n" + stack.toString() + "\n\nsymbols:\n" + symbolTable.toString() +
                "\n\nfiles:\n" + fileTable.toString() + "\n\nheap:\n" + heapTable.toString() +
                "\n\noutput:\n" + output.toString() + "\n\n";
    }

}
