package com.rubico.flight.ydomain;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GitDaoImpl<T> implements GitDao<T> {

    private final JGitImpl jGit;

    @Override
    public <T> T getOne(T var1) throws Exception {

        return (T) jGit.get(var1);
    }
}
