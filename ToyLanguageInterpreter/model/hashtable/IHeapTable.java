package model.hashtable;

import model.values.IValue;

import java.util.Map;
import java.util.Set;

public interface IHeapTable {
    IValue get(Integer address);
    void remove(Integer address);
    int insert(IValue content);
    void set(int address, IValue value);
    Set<Map.Entry<Integer, IValue>> entrySet();
    void setContent(Map<Integer, IValue> table);
}
