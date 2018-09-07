import main.java.Monster;

import java.lang.reflect.Modifier;

public class Main {

    public static void main(String[] args) {
        System.out.println("Hello World!");
        Monster dick = new Monster(15, "Dick", 14);
        System.out.print(dick);
        dick.setHitpoints(225);
        System.out.println(dick.getHitpoints());

        dick.setWisdom(15);
        System.out.println(dick.getWisdomMod());
    }
}
