package com.example.devourery;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.*;
import android.widget.Toast;

public class CanvasView extends View implements ICanvasView{                          //Этот класс рисует на экране
    private static int width;
    private static int height;
    private GameManager gameManager;
    private Paint paint;
    private Canvas canvas;
    private Toast toast;                                //Хранит сообщ о выигрыше или проигрыше

    public CanvasView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initWidthAndHeight(context);
        initPaint();
        gameManager = new GameManager(this, width, height);                 //this чтобы Canvas мог обращаться к View
    }

    private void initPaint() {                               //Инициализируем кисточку
        paint = new Paint();
        paint.setAntiAlias(true);                            //Сглаживание
        paint.setStyle(Paint.Style.FILL);                    //Круги будут заполнены
    }

    private void initWidthAndHeight(Context context) {
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);//Узнаем размер экрана
        Display display = windowManager.getDefaultDisplay();
        Point point = new Point();
        display.getSize(point);                                                     //Возвращает точку(меняет ее координаты
        width = point.x;
        height = point.y;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
       // gameManager.onDraw(canvas);
        this.canvas = canvas;                             //Чтобы видеть ссылку на canvas
        gameManager.onDraw();
    }

    @Override
    public void drawCircle(SimpleCircle circle) {
        paint.setColor(circle.getColor());
        canvas.drawCircle(circle.getX(), circle.getY(), circle.getRadius(), paint);
    }

    @Override
    public void redraw() {                                  //View перерисовывает сама себя
        invalidate();
    }

    @Override
    public void showMessage(String text) {
        if (toast != null) {                            //Если уже было сообщ то закрываем его
            toast.cancel();
        }
        toast = Toast.makeText(getContext(), text, Toast.LENGTH_SHORT);          //Создаем новое сообщение
        toast.setGravity(Gravity.CENTER, 0, 0);                                 //Отображаем сообщ по центру
        toast.show();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {        //Чтобы пальцем двигать главный круг
        int x = (int) event.getX();                         //Получаем координаты прикосновения к экрану
        int y = (int) event.getY();
        if (event.getAction() == MotionEvent.ACTION_MOVE) {
            gameManager.onTouchEvent(x, y);                 //Узнаем, двигаеться ли палец по экрану
        }
        invalidate();                                       //View перерисовывает себя
        return true;
    }


    // todo DZ
//    public static int recalculateRadius(int radius) {
//        return radius * 768 < height ? width : height;
//    }
}
