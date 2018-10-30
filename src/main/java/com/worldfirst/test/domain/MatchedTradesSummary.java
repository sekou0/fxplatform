package com.worldfirst.test.domain;

import org.springframework.hateoas.ResourceSupport;

/**
 * Created by kamau on 24/10/2018.
 */
public class MatchedTradesSummary extends ResourceSupport {

    private int count;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
