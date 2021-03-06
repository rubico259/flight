package com.rubico.flight.ydomain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class Values {

    @JsonIgnore
    private String path;
    private Git git;

    private String env;
}
