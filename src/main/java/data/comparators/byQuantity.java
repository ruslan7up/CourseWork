package data.comparators;

import domains.Goods;

import java.util.Comparator;

/**
 * Created by ruslan on 08.10.2016.
 */
public class byQuantity implements Comparator<Goods> {
    @Override
    public int compare(Goods o1, Goods o2) {
        if(o1.getQuantity()>o2.getQuantity())
        {
            return 1;
        } else if(o1.getQuantity()<o2.getQuantity())
        {
            return -1;
        }
        return 0;
    }
}
