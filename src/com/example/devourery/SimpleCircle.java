package com.example.devourery;

public class SimpleCircle {
    protected int x;                    //сделали protected чтобы было видно предку переменную
    protected int y;
    protected int radius;
    private int color;

    public SimpleCircle(int x, int y, int radius) {           //Инициализируем круги
        this.x = x;
        this.y = y;
        this.radius = radius;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getRadius() {
        return radius;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public SimpleCircle getCircleArea() {
        return new SimpleCircle(x, y, radius * 3);    //Создаем невидимую область вокрг крга чтобы не появлялись очень близко круги на старте
    }

    public boolean isIntersect(SimpleCircle circle) {        //Проверяем, пересекаются ли круги
        return radius + circle.radius >= Math.sqrt(Math.pow(x - circle.x, 2) + Math.pow(y - circle.y, 2));
    }
}
