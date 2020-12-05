package model.stack;

import java.util.Stack;

public class ExecutionStack<T> implements IExecutionStack<T> {
    private final Stack<T> stack;

    public ExecutionStack()
    {
        this.stack = new Stack<T>();
    }

    @Override
    public void clear()
    {
        stack.empty();
    }

    @Override
    public T pop()
    {
        return stack.pop();
    }

    @Override
    public void push(T value)
    {
        stack.push(value);
    }

    @Override
    public boolean isEmpty()
    {
        return stack.isEmpty();
    }

    @Override
    public String toString()
    {
        Stack<T> stackCopy = new Stack<>();
        StringBuilder text = new StringBuilder();

        while(!this.stack.empty())
        {
            stackCopy.push(stack.peek());
            text.append(stack.peek().toString()).append(" | ");
            stack.pop();
        }

        while(!stackCopy.empty())
        {
            stack.push(stackCopy.pop());
        }

        return text.toString();
    }
}
