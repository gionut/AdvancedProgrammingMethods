package repository;

import model.ProgramState;
import model.hashtable.IHeapTable;
import model.values.IValue;

import java.io.IOException;
import java.lang.ref.Reference;
import java.util.List;
import java.util.Map;

public interface IRepository {

    List<ProgramState> getProgramsList();

    ProgramState getCurrentProgramState();

    ProgramState getProgramState(int index);

    IHeapTable getHeap();

    int getSize();

    void setHeap(Map<Integer, IValue> content);

    void setProgramList(List<ProgramState> programs);

    void addProgram(ProgramState program);

    void logProgramExecution(ProgramState state);

    void close();

    void clear();
}
