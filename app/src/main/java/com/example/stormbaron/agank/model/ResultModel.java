package com.example.stormbaron.agank.model;

/**
 * Created by stormbaron on 17-8-3.
 */

public class ResultModel<T> implements BaseModel {
    public int resultCode;
    public String description;
    public T data;
}
