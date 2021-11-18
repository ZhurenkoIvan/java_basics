package com.skillbox.redisdemo;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

import static java.lang.System.in;
import static java.lang.System.out;

public class RedisTest {

    // Запуск докер-контейнера:
    // docker run --rm --name skill-redis -p 127.0.0.1:6379:6379/tcp -d redis

    // Для теста будем считать неактивными пользователей, которые не заходили 2 секунды
    private static final int DELETE_SECONDS_AGO = 2;

    // Допустим пользователи делают 500 запросов к сайту в секунду
    private static final int RPS = 500;

    // И всего на сайт заходило 1000 различных пользователей
    private static final int USERS = 1000;

    // Также мы добавим задержку между посещениями
    private static final int SLEEP = 1; // 1 миллисекунда

    private static final SimpleDateFormat DF = new SimpleDateFormat("HH:mm:ss");

    private static void log(int UsersOnline) {
        String log = String.format("[%s] Пользователей онлайн: %d", DF.format(new Date()), UsersOnline);
        out.println(log);
    }

    public static void main(String[] args) throws InterruptedException {

        RedisStorage redis = new RedisStorage();
        redis.init();
        // Эмулируем работу сайта
        for (int i = 1; i < 21; i++)
            redis.logPageVisit(i);
        while (true) {
            List<String> allUsers = redis.getAllUsers();
            int count = 0;
            for (int i =0; i < 20; i++){
                double random = Math.random();
                if (random < 0.9) {
                    out.println("Пользователь " + allUsers.get(count));
                    count++;
                } else {
                    int userID = (int) ((20.0 - count) * Math.random() + 1 + count);
                    if (allUsers.contains(String.valueOf(userID))) {
                        out.println("Пользователь " + userID + " оплатил продвижение");
                        allUsers.remove(String.valueOf(userID));
                    }
                }
            }
            Thread.sleep(1000);
        }
//        for(int seconds=0; seconds <= 10; seconds++) {
//            // Выполним 500 запросов
//            for(int request = 0; request <= RPS; request++) {
//                int user_id = new Random().nextInt(USERS);
//                redis.logPageVisit(user_id);
//                Thread.sleep(SLEEP);
//            }
//            redis.deleteOldEntries(DELETE_SECONDS_AGO);
//            int usersOnline = redis.calculateUsersNumber();
//            log(usersOnline);
//        }
//        redis.shutdown();
    }
}
