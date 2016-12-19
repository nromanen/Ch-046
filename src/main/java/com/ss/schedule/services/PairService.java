package com.ss.schedule.services;

import com.ss.schedule.model.Pair;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Admin on 18.12.16.
 */
public class PairService {

    public List<Pair> getPair(long maxPair) {

        List<Pair> pairs = new ArrayList<>();

        for (long i = 0; i < maxPair; i ++) {
            pairs.add(Pair.getById(i+1));
        }
        return pairs;
    }
}
