package com.gmail.bishoybasily.graphql.model.entity;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author bishoybasily
 * @since 2020-04-20
 */
@Data
@Accessors(chain = true)
public class File {

    private String path;
    private Boolean directory;

}
