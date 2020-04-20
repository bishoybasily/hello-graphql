package com.gmail.bishoybasily.graphql.model.service;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.gmail.bishoybasily.graphql.model.entity.File;
import org.springframework.stereotype.Service;


@Service
public class FileQuery implements GraphQLQueryResolver {

    public File browse(String path) {
        java.io.File f = new java.io.File(path);
        return new File().setDirectory(f.isDirectory()).setPath(f.getAbsolutePath());
    }

}
