package com.example.devourery;


import android.graphics.Color;

public class MainCircle extends SimpleCircle{
    public static final int INIT_RADIUS = 50;
    public static final int MAIN_SPEED = 40;
    public static final int OUR_COLOR = Color.BLUE;

    public MainCircle(int x, int y) {           //Наследуем от SimpleCircle x, y
        super(x, y, INIT_RADIUS);
        setColor(OUR_COLOR);                    //Красим главный круг
    }

    public void moveMainCircleWhenTouchAt(int x1, int y1) {     //Реализуем движение круга
        int dx = (x1 - x) * MAIN_SPEED / GameManager.getWidth();        //Чем дальше касаемся от круга, тем выше его скорость
        int dy = (y1 - y) * MAIN_SPEED / GameManager.getHeight();        //Чем дальше касаемся от круга, тем выше его скорость
        x += dx;
        y += dy;
    }

    public void initRadius() {                  //Сбрасываем радиус до начального
        radius = INIT_RADIUS;
    }

    public void growRadius(SimpleCircle circle) {                               //Увеличиваем радиус нашего круга на радиус поглощенного
        radius = (int) Math.sqrt(Math.pow(radius, 2) + Math.pow(circle.radius, 2));
    }
}
