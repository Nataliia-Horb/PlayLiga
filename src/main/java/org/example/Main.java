package org.example;

import com.github.javafaker.Faker;

import java.util.List;
import java.util.Random;

public class Main {
    public static void main(String[] args) {


//        1. У нас есть класс игроки
//        2. У игроков есть имена и возраст
//        3. Есть Энам который хранит лиги
//        4. Есть класс который хранит в листе всех игроков
//        5. Есть класс который проводит игру среди игроков одной лиги
//        6. Каждый игрок в каждой лиге должен сыграть 25 раз со своей же лигой
//        7. Есть метод который считает очки у каждого игрока
//        Вам необходимо написать все это + метод который будет брать из нашего листа игроков и проводить с ними игру в
//        одной лиге так что бы все сыграли одинаковое количество раз.После того как отыграют все игроки в каждой
//        лиге, надо найти трех дидеров в каждой лиге и если ЕСТЬ ВОЗМОЖНОСТЬ ПЕРЕМЕСТИТЬ ЛИДЕР В ЛИГУ ВЫШЕ СДЕЛАТЬ
//        ЭТО ЕСЛИ НЕТ ТО НЕ НАДО.

        PlayerManager manager = PlayerManager.getInstance();
        List<Player> playersInFirstLeague = manager.getPlayersInOneLeague(8, League.FIRST_LEAGUE);
        List<Player> playersInSecondLeague = manager.getPlayersInOneLeague(5, League.SECOND_LEAGUE);
        List<Player> playersInPremierLeague = manager.getPlayersInOneLeague(10, League.PREMIER_LEAGUE);
        GameImplementation game = new GameImplementation();
        game.play(playersInFirstLeague);
        game.play(playersInSecondLeague);
        game.play(playersInPremierLeague);

    }
}