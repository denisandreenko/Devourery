package com.example.devourery;

import java.util.ArrayList;

public class GameManager {
    private static final int MAX_CIRCLES = 10;
    private MainCircle mainCircle;                              //Чтобы CanvasView мог образаться к главному кругу
    private ArrayList<EnemyCircle> circles;
    private CanvasView canvasView;
    private static int width;
    private static int height;

    public GameManager(CanvasView canvasView, int w, int h) {
        this.canvasView = canvasView;
        width = w;
        height = h;
        initMainCircle();
        initEnemyCircles();
    }

    private void initEnemyCircles() {
        SimpleCircle mainCircleArea = mainCircle.getCircleArea(); //Создаем невидимую область вокрг крга чтобы не появлялись очень близко круги на старте
        circles = new ArrayList<EnemyCircle>();
        for (int i = 0; i < MAX_CIRCLES; i++) {
            EnemyCircle circle;                                 //Создаем вражеские круги
            do {
                circle = EnemyCircle.getRandomCircle();
            } while (circle.isIntersect(mainCircleArea));       //Если созданный круг наслаивается на основной, пересоздаем его
            circles.add(circle);
        }
        calculateAndSetCirclesColor();
    }

    private void calculateAndSetCirclesColor() {                //Расчитываем и устанавливаем цвет кругов
        for (EnemyCircle circle : circles) {
            circle.setEnemyOrFoodColorDependOn(mainCircle);
        }

    }


    public static int getWidth() {
        return width;
    }

    public static int getHeight() {
        return height;
    }

    private void initMainCircle() {                           //Инициализируем главный круг
        mainCircle = new MainCircle(width / 2, height / 2);
    }

    public void onDraw() {
        canvasView.drawCircle(mainCircle);              //Рисуем главный круг
        for (EnemyCircle circle : circles)              //Рисуем врагов
        {
            canvasView.drawCircle(circle);
        }
    }

    public void onTouchEvent(int x, int y) {                    //Обрабатываем прикосновение к экрану
        mainCircle.moveMainCircleWhenTouchAt(x,y);              //Двигаем круг
        checkCollision();
        moveCircles();
    }

    private void checkCollision() {                             //ПРоверяем пересечение с главным кругом
        SimpleCircle circleForDel = null;                       //C помощью пустой ссылки удаляем круги когда съедим
        for (EnemyCircle circle : circles) {
            if (mainCircle.isIntersect(circle))                 //Если пересеклись с врагом то игра закончена
            {
                if (circle.isSmallerThan(mainCircle )) {       //Если враг становить меньше то становиться едой
                    mainCircle.growRadius(circle);              //Растем на радис поглощенного круга
                    circleForDel = circle;
                    calculateAndSetCirclesColor();              //Т.к. размер главного изменился, делаем переоценку всех цветов
                    break;
                } else {
                    gameEnd("YOU LOSE!");
                    return;
                }
            }
        }
        if (circleForDel != null) {                              //Удаляем круг который съели
            circles.remove(circleForDel);
        }
        if (circles.isEmpty()) {                                 //Если круги кончились - конец игры
            gameEnd("YOU WIN!");
        }
    }

    private void gameEnd(String text) {                            //Перерисовываем себя, врагом, еду
        canvasView.showMessage(text);
        mainCircle.initRadius();                        //Сбрасываем радиус до начального
        initEnemyCircles();
        canvasView.redraw();
    }

    private void moveCircles() {
        for (EnemyCircle circle : circles) {
            circle.moveOneStep();
        }
    }
}
