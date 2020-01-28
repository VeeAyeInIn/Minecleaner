package me.vann.school;

import java.util.Arrays;

public class Minecleaner {

    public static void main(String[] args) {
        Game game = new Game(5, 5, 10);
        game.start();
        System.out.println(game.getBoard());
    }

    public static void setup(String[] args) {

    }
}
