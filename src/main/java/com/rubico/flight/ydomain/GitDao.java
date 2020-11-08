package com.rubico.flight.ydomain;

public interface GitDao<T> {

    <T> T getOne(T var1) throws Exception;
}
