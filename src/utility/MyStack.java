package utility;

import java.util.ArrayList;

/**
 * Created for Chapter 11 Exercise 10.
 */
public class MyStack extends ArrayList{
    public MyStack() {
    }

    public int getSize() {
        return this.size();
    }

    public Object peek() {
        return get(getSize() - 1);
    }

    public Object pop() {
        Object o = get(getSize() - 1);
        remove(getSize() - 1);
        return o;
    }

    public void push(Object o) {
        add(o);
    }
}
