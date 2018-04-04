package com.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
 public class Dog {
    private  int id;
    private  String name;

    public Dog(int id, String name) {
        this.id = id;
        this.name = name;
    }
}
