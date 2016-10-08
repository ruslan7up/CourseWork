package data.comparators;

import domains.Goods;

import java.util.Comparator;

/**
 * Created by ruslan on 08.10.2016.
 */
public class byName implements Comparator<Goods> {
    @Override
    public int compare(Goods o1, Goods o2) {
        return o1.getName().compareTo(o2.getName());
    }
}
