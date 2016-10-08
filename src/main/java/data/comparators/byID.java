package data.comparators;

import domains.Goods;

import java.util.Comparator;

/**
 * Created by ruslan on 08.10.2016.
 */
public class byID implements Comparator<Goods> {
    @Override
    public int compare(Goods o1, Goods o2) {
        if(o1.getId()>o2.getId())
        {
            return 1;
        } else if(o1.getId()<o2.getId())
        {
            return -1;
        }
        return 0;
    }
}
