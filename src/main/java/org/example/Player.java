package org.example;

import lombok.*;

@ToString
@Setter
@Getter
public class Player<T, U extends Integer, E extends Enum> {
    T name;
    U age;
    E league;


    public Player(T name, U age, E league) {
        this.name = name;
        this.age = age;
        this.league = league;

    }

    @Override
    public String toString() {
        return "" + name;
    }
}
