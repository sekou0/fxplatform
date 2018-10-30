package com.worldfirst.test.domain;

import java.util.Comparator;

/**
 *
 * Created by kamau on 12/09/2018.
 */
public class OrderBookLevelComparator implements Comparator<Float> {

    private OrderType side;

    public OrderBookLevelComparator(OrderType side) {

        this.side = side;
    }

    @Override
    public int compare(Float o1, Float o2)
    {
        int compare = Float.compare(o1, o2);

        if(OrderType.ASK.equals(this.side)) {
            compare=compare*-1;
        }
        return compare;
    }
}
