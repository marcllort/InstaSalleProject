package Structures;

import Model.Post;

import java.util.Comparator;

public class ComparatorObject implements Comparator<Object> {
    @Override
    public int compare(Object obj1, Object obj2) {

        long l1 = ((Post)obj1).getPublished_when();
        long l2 = ((Post)obj2).getPublished_when();
        if (l2 > l1)
            return -1;
        else if (l1 > l2)
            return 1;
        else
            return 0;
    }

    @Override
    public Comparator<Object> reversed() {
        return null;
    }
}
