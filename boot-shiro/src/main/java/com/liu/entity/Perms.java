package com.liu.entity;

import com.fasterxml.jackson.databind.cfg.ContextAttributes;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;


@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class Perms implements Serializable {
    private Integer id;
    private String name;
    private String url;
}
