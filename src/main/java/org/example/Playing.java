package org.example;

import java.util.List;
import java.util.Map;

public interface Playing {
    List<Player> play(List<? extends Player> list);
}
