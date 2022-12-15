package org.example;

import java.util.*;
import java.util.Map.Entry;
import java.util.stream.Collectors;

public class GameImplementation implements Playing {
    Random random;
    private int allGamesCount;

    public GameImplementation() {
        this.random = new Random();
    }

    @Override
    public List<Player> play(List<? extends Player> list) {
        allGamesCount = 0;

        Enum league = list.get(0).getLeague();
        // соперники которые еще учавствуют в играх
        ArrayDeque<Player> dequeOpponents = new ArrayDeque<>(list);

        // Карта   ключ-это соперник с которыми должен сыграть один игрок, значение-это количество баллов игрока в игре с соперником
        // -1 по умолчанию балы до старта игры, далее 0-проиграл, 1-ничья, 2-выиграл
        Map<Player, Integer> onePlayerPlayMap = list.stream().collect(Collectors.toMap(k -> k, v -> -1));

        // Итоговая карта игр всех участников одной Лиги  ключ-это текущий игрок,  значение-это карта результатов всех проведенных игр этого игрока
        Map<Player, Map<Player, Integer>> allPlayersPlayMap = list.stream().collect(Collectors.toMap(key -> key, value -> {
            Map<Player, Integer> interMap = new HashMap<>(onePlayerPlayMap);

            // удаляем текущего игрока из таблицы соперников, т.к. он не будет играть сам с собой
            interMap.remove(value);
            return interMap;
        }));


        int allGamesCountInOneLeague = 0;
        // заполняем итоговую карту всех игр
        for (int i = 0; i < list.size(); i++) {

            // беру одного текущего игрока
            Player someOnePlayer = list.get(i);

            // удаляю текущего игрока из очереди соперников, которые готовяться с ним играть, т.к. он не будет играть сам с собой
            dequeOpponents.remove(someOnePlayer);

            // делаю копию очереди, чтобы можно было запускать Stream при каждой итерации
            //при каждой итерации очередь уменьшается т.е временная сложность n*Log(n)
            ArrayDeque<Player> dequeClone = new ArrayDeque<>(dequeOpponents);


            dequeClone.stream().forEach(el -> {

                // рандомное число определяет балы текущего игрока и его соперника
                // 0-текущий игрок проиграл значит соперник получает 2 балла
                // 1- текущий игрок и сопертик сыграли в ничью и оба получают по 1 балллу
                // 2- текущий игрок выиграл значит сопертик получает 0 баллов

                int res = random.nextInt(3);
                allGamesCount++;


                // в итоговую карту всех игр заношу результаты игр,
                // Например игрок 3 играл против игрока 5 и выиграл, тогда:
                // в карту игрока 3 под ключем соперника 5 попадает 2 балла
                // в карту игрока 5 под ключем игрока 3 попадает 0 баллов

                allPlayersPlayMap.get(someOnePlayer).put(el, res);
                allPlayersPlayMap.get(el).put(someOnePlayer, 2 - res);
            });
        }

        System.out.println();
        System.out.println("_____________" + league + " Games____________");
        allPlayersPlayMap.entrySet().forEach(entry -> {
            System.out.print(entry.getKey() + " : " + entry.getValue());
            System.out.println();
        });
        System.out.println();
        System.out.println("Total games played in " + league + ": " + allGamesCount);


        return countAllPlayerPoints(allPlayersPlayMap);
    }


    private static List<Player> countAllPlayerPoints(Map<Player, Map<Player, Integer>> allPlayersPlayMap) {
        Map<Player, Integer> pointsMap = new HashMap<>();
        for (Player key : allPlayersPlayMap.keySet()) {
            int res = allPlayersPlayMap.get(key).entrySet().stream().map(e -> e.getValue()).reduce(0, Integer::sum);
            pointsMap.put(key, res);
        }
        System.out.println("Players and their results: " + pointsMap);
        List<Player> ratingList = pointsMap.entrySet().stream().sorted(Map.Entry.<Player, Integer>comparingByValue().reversed()).map(en -> en.getKey()).collect(Collectors.toList());
        System.out.println("Rating list all Players in League: " + ratingList);
        System.out.println("________________________________________");
        return ratingList;
    }

    public int getAllGamesCount() {
        return allGamesCount;
    }
}
