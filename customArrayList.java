package Assigment1;

import java.util.ArrayList;

public class customArrayList<T> {
    ArrayList<T> mylist;

    public customArrayList() {
        mylist = new ArrayList<T>();
    }
    public void add(T t) {
        mylist.add(t);
    }
    public T get(int index) {
        return mylist.get(index);
    }
    public int size() {
        return mylist.size();
    }
}
