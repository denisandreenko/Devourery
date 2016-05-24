package com.example.devourery;

import android.graphics.Paint;

/**
 * Created by knigi on 15.05.2016.
 */
public interface ICanvasView {          //C помощью этого интерфейса отделяем данные от логики управления этими данными(переносим все что относиться к рисованию из gameManager
    void drawCircle(SimpleCircle circle);

    void redraw();

    void showMessage(String text);
}
