package com.liu.query;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class UserQuery {
    private Integer id;
    private String username;
    private Integer rid;
    private String rname;
}
