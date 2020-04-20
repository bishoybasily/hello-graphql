package com.gmail.bishoybasily.graphql.model.resolver;

import com.coxautodev.graphql.tools.GraphQLResolver;
import com.gmail.bishoybasily.graphql.model.entity.File;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class FileResolver implements GraphQLResolver<File> {

    public List<File> getFiles(File file) {
        java.io.File f = new java.io.File(file.getPath());
        if (f.isDirectory())
            return Stream.of(f.listFiles())
                    .map(it -> new File().setDirectory(it.isDirectory()).setPath(it.getAbsolutePath()))
                    .collect(Collectors.toList());

        return Collections.emptyList();
    }

}
