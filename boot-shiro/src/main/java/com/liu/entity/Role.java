package com.liu.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class Role implements Serializable {
    private Integer id;
    private String name;

    //   当前角色所拥有的权限
    private List<Perms> perms;

}
