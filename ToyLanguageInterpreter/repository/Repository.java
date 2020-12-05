package repository;

import model.ProgramState;
import model.hashtable.HeapTable;
import model.hashtable.IHeapTable;
import model.values.IValue;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Repository implements IRepository{
    private List<ProgramState> programs;
    private IHeapTable heapTable;
    int currentProgram;
    String filename;
    PrintWriter writer;

    public Repository(String filename)
    {
        programs = new ArrayList<>();
        currentProgram = -1;
        heapTable = new HeapTable();
        this.filename = filename;
        writer = null;
    }

    @Override
    public IHeapTable getHeap() {
        return heapTable;
    }

    @Override
    public void setHeap(Map<Integer, IValue> content) {
        heapTable.setContent(content);
    }

    @Override
    public void addProgram(ProgramState program)
    {
        programs.add(program);
        currentProgram++;
    }

    @Override
    public int getSize()
    {
        return programs.size();
    }

    @Override
    public List<ProgramState> getProgramsList() {
        return programs;
    }

    @Override
    public ProgramState getProgramState(int index)
    {
        return programs.get(index);
    }

    @Override
    public ProgramState getCurrentProgramState()
    {
        return programs.get(currentProgram);
    }

    @Override
    public void setProgramList(List<ProgramState> programs) {
        this.programs = programs;
    }

    @Override
    public void logProgramExecution(ProgramState program){
        try {
            if (writer == null) {
                writer = new PrintWriter(new BufferedWriter(new FileWriter(filename, true)));
            }
        }
        catch(IOException e) {
            throw new RuntimeException(e.toString());
        }

        writer.println("Thread: " + program.getId());
        writer.println("Execution Stack:" + program.getStack().toString());
        writer.println("Symbols Table: " + program.getSymbolTable().toString());
        writer.println("Files Table: " + program.getFileTable().toString());
        writer.println("Heap: " + program.getHeapTable().toString());
        writer.println("Output: " + program.getOutput().toString());
        writer.println("\n");


    }

    @Override
    public void clear()
    {
        PrintWriter clear_writer;
        try
        {
            clear_writer = new PrintWriter(filename);
        }
        catch(IOException e)
        {
            throw new RuntimeException(e.toString());
        }
        clear_writer.println("");
        clear_writer.close();
    }

    @Override
    public void close()
    {
        writer.close();
    }
}
