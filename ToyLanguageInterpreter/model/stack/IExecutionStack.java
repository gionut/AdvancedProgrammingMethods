package model.stack;

public interface IExecutionStack<T> {
    T pop();
    void push(T value);
    boolean isEmpty();
    void clear();
}
