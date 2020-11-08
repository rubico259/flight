package com.rubico.flight.ydomain;

import lombok.Data;

import java.util.Map;

@Data
@GitEntity
public class Environment {

    @GitId
    private String id;

    @GitBranch
    private String branch;

    @GitRepository
    private String repository;

    private String projectKey;

    @VCFilePathYML
    private Config config;

    @VCFilePathYML("values")
    private Values values;

    private Map<String, String> valuesFiles;
}
