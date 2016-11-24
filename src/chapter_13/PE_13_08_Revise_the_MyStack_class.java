package chapter_13;

import java.util.ArrayList;

/**
 * (Revise the MyStack class) Rewrite the MyStack class in Listing 11.10 to perform
 * a deep copy of the list field.
 */
public class PE_13_08_Revise_the_MyStack_class {
    public static void main(String[] args) {
        PE_13_08_MyStack stack1 = new PE_13_08_MyStack();
        stack1.push("Java");
        stack1.push("is");

        PE_13_08_MyStack stack2 = (PE_13_08_MyStack)stack1.clone();
        stack2.push("fun");

        System.out.println("stack1 " + stack1);
        System.out.println("stack2 " + stack2);
    }
}

class PE_13_08_MyStack implements Cloneable {
    private ArrayList<Object> list = new ArrayList<>();

    public boolean isEmpty() {
        return list.isEmpty();
    }

    public int getSize() {
        return list.size();
    }

    public Object peek() {
        return list.get(getSize() - 1);
    }

    public Object pop() {
        Object o = list.get(getSize() - 1);
        list.remove(getSize() - 1);
        return o;
    }

    public void push(Object o) {
        list.add(o);
    }

    @Override
    public String toString() {
        return "stack: " + list.toString();
    }

    @Override
    public Object clone() {
        try {
            PE_13_08_MyStack clone = (PE_13_08_MyStack)super.clone();
            clone.list = (ArrayList<Object>)this.list.clone();
            return clone;
        }
        catch (CloneNotSupportedException ex) {
            return null;
        }
    }
}