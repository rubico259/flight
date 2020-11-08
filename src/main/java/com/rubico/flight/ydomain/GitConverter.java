package com.rubico.flight.ydomain;

public interface GitConverter<T> {

    <S extends T> S fromGit(S var1);
}
