package model.hashtable;

import model.values.IValue;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class HeapTable implements IHeapTable{
    private int nextAddress;
    private final Map<Integer, IValue> table;

    public HeapTable()
    {
        table = new HashMap<>();
        nextAddress = 1;
    }

    @Override
    public IValue get(Integer address)
    {
        return table.get(address);
    }

    @Override
    public void remove(Integer address)
    {
        table.remove(address);
    }

    @Override
    public int insert(IValue value)
    {
        table.put(nextAddress, value);
        return nextAddress++;
    }

    @Override
    public Set<Map.Entry<Integer, IValue>> entrySet()
    {
        return table.entrySet();
    }

    @Override
    public void set(int address, IValue value)
    {
        table.put(address, value);
    }

    @Override
    public void setContent(Map<Integer, IValue> table)
    {
        this.table.clear();
        this.table.putAll(table);
    }

    @Override
    public String toString()
    {
        StringBuilder text = new StringBuilder();
        for (HashMap.Entry<Integer, IValue> entry : this.table.entrySet()) {
            text.append(entry.getKey().toString()).append(" -> ").append(entry.getValue().toString()).append("; ");
        }

        return text.toString();
    }
}
