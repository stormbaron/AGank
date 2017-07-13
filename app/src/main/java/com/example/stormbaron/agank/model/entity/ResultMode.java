package com.example.stormbaron.agank.model.entity;

import java.util.List;

/**
 * Created by stormbaron on 17-6-28.
 */

public class ResultMode<T> {
    public int count;
    public boolean error;
    public List<T> results;
}
