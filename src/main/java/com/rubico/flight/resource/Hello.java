package com.rubico.flight.resource;

import com.rubico.flight.ydomain.Environment;
import com.rubico.flight.ydomain.GitDao;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class Hello {

    private final GitDao<Environment> gitDao;

    @GetMapping
    public void get() throws Exception {

        Environment environment = new Environment();

        Environment f = gitDao.getOne(environment);
        f.getBranch();
    }


}
