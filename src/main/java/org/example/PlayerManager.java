package org.example;

import com.github.javafaker.Faker;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PlayerManager {
    static PlayerManager pm = null;
    Random random = new Random();
    Faker faker = new Faker();
    List<Player> allPlayers = new ArrayList<>();

    private PlayerManager() {
    }

    static public PlayerManager getInstance() {
        if (pm == null)
            pm = new PlayerManager();
        return pm;
    }

    private Player createPlayer(League l) {
        return new Player(faker.name().firstName(), faker.random().nextInt(18, 50), l);
    }

    private List<Player> createPlayersInOneLeague(int quantity, League l) {
        List<Player> team = new ArrayList<>();
        for (int i = 0; i < quantity; i++) {
            team.add(createPlayer(l));
        }
        return team;
    }

    public List<Player> getPlayersInOneLeague(int quantity, League l) {
        return createPlayersInOneLeague(quantity, l);
    }


}
