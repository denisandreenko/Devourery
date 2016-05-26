package com.example.devourery;

import android.graphics.Paint;


public interface ICanvasView {          //C помощью этого интерфейса отделяем данные от логики управления этими данными(переносим все что относиться к рисованию из gameManager
    void drawCircle(SimpleCircle circle);

    void redraw();

    void showMessage(String text);
}
