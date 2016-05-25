package com.example.devourery;

import android.graphics.Color;

import java.util.Random;

public class EnemyCircle extends SimpleCircle {
    private static final int FROM_RADIUS = 20;
    private static final int TO_RADIUS = 70;
    public static final int ENEMY_COLOR = Color.RED;
    public static final int FOOD_COLOR = Color.rgb(0, 200, 0);
    public static final int RANDOM_SPEED = 7;
    private int dx;
    private int dy;

    public EnemyCircle(int x, int y, int radius, int dx, int dy) {
        super(x, y, radius);
        this.dx = dx;
        this.dy = dy;
    }

    public static EnemyCircle getRandomCircle() {
        Random random = new Random();
        int x = random.nextInt(GameManager.getWidth());                 //Выдает рандомное число входящее в ширину экрана
        int y = random.nextInt(GameManager.getHeight());                //Выдает рандомное число входящее в высоту экрана
        int dx = 1 + random.nextInt(RANDOM_SPEED);
        int dy = 1 + random.nextInt(RANDOM_SPEED);
        int radius = FROM_RADIUS + random.nextInt(TO_RADIUS - FROM_RADIUS); //Выдает рандомный радиус от 10 до 110;
        EnemyCircle enemyCircle = new EnemyCircle(x, y, radius, dx, dy);            //Создали вражеский круг
        return enemyCircle;
    }

    public static EnemyCircle getFoodCircle() {
        Random random = new Random();
        int x = random.nextInt(GameManager.getWidth());                 //Выдает рандомное число входящее в ширину экрана
        int y = random.nextInt(GameManager.getHeight());                //Выдает рандомное число входящее в высоту экрана
        int dx = 1 + random.nextInt(RANDOM_SPEED);
        int dy = 1 + random.nextInt(RANDOM_SPEED);
        int radius = FROM_RADIUS + random.nextInt(INIT_RADIUS - FROM_RADIUS);   //Выдает еду
        EnemyCircle enemyCircle = new EnemyCircle(x, y, radius, dx, dy);            //Создали вражеский круг
        return enemyCircle;
    }

    public void setEnemyOrFoodColorDependOn(MainCircle mainCircle) {            //Врагов делаем едой если размер меньше главного круга
        if (isSmallerThan(mainCircle)) {
            setColor(FOOD_COLOR);                             //Если враг меньше главного, он становиться едой
        }   else {
            setColor(ENEMY_COLOR);
        }
    }

    public boolean isSmallerThan(SimpleCircle circle) {
        if (radius < circle.radius) {
            return true;
        }
        return false;
    }

    public void moveOneStep() {                         //Двигаем врагов при нажатии на экран
        x += dx;
        y += dy;
        checkBounds();
    }

    private void checkBounds() {                        //Проверяем границы
        if (x > GameManager.getWidth() || x < 0) {      //Если круг вышел за границы экрана, то поворациваем его
            dx = -dx;
        }
        if (y > GameManager.getHeight() || y < 0) {      //Если круг вышел за границы экрана, то поворациваем его
            dy = -dy;
        }
    }
}
