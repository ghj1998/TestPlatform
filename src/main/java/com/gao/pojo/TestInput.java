package com.gao.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TestInput {
    private int id;
    private String name;
    private String input;
    private String output;
    private double permissibleError;
}
