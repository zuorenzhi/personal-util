package com.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Toy {
    private  String name;
    private  String age;

    public Toy(String name, String age) {
        this.name = name;
        this.age = age;
    }
//
//    public Toy() {
//    }
}