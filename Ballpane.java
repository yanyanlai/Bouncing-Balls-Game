package yanlai.chd;

import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.DoubleProperty;
import javafx.geometry.Pos;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import java.awt.*;

public class Ballpane extends Pane {
    public final double radius = 15;                                //设置球半径
    private double x = (Math.random() * 200 + 15), y = radius;      //设置小球初始的x，y坐标
    double dx = 0.5, dy = 0.5;                                          //设置小球的移动速度
    private Circle circle = new Circle(x, y, radius);               //初始化小球
    private Rectangle rt = new Rectangle(100, getHeight(), 50, 5);//设置板的初始位置和大小
    private Timeline animation;
    private Timeline animation1;
    private double locationX,locationY;


    public Ballpane() {
        circle.setFill(Color.RED);             //球的颜色
        rt.setFill(Color.BLACK);               //板的颜色

        getChildren().add(rt);                 //添加板
        getChildren().add(circle);             //添加球

        animation = new Timeline(new KeyFrame(Duration.millis(50), e -> moveball())); //球的速度？那之前那个是什么呢？
        animation.setRate(5);    //又是球的速度？不懂
        animation.setCycleCount(Timeline.INDEFINITE);
       // animation.play();

        //animation1 = new Timeline(new KeyFrame(Duration.millis(5), e -> circlecolor()));//小球的颜色变化速度

        //animation1.setCycleCount(Timeline.INDEFINITE);
        //animation1.play();
    }

    public void circlecolor() {
        circle.setFill(new Color(Math.random(), Math.random(), Math.random(), Math.random()));//球的颜色随机
    }

    public void play() {
        animation.play();
    }
    public void pause() {
        animation.pause();
    }

    public void right() {
        rt.setX(rt.getX() + 5);
    }

    public void left() {
        rt.setX(rt.getX() - 5);
    }

    public void increasespeed() {
        animation.setRate(animation.getRate() + 2);
    }

    public void decreasespeed() {
        animation.setRate(animation.getRate() > 0 ? animation.getRate() - 0.1 : 0.1);
    }

    protected void moveball() {
        // Check boundaries
        if (x < radius || x > getWidth() - radius) {
            dx *= -1; // Change ball move direction
        }
        if ( y > getHeight() - radius ||(y<radius&&x>rt.getX()&&x<rt.getX()+50)) {
            dy *= -1; // Change ball move direction
        }
        if ((y<radius )&&(x<rt.getX() ||x>rt.getX()+50)){
            System.exit(0);
        }


        // Adjust ball position
        x += dx;
        y += dy;
        circle.setCenterX(x);
        circle.setCenterY(y);

    }

    protected void handleMousePressed(MouseEvent e){
        /*
        e.getSceneX()代表的是鼠标点击或按压时的位置，
        text.getX()表示的是文本的原始位置，
        前者减后者就是鼠标点击或按下时距离文本左边缘的距离
         */
        locationX=e.getSceneX()- rt.getX();
        //locationY=e.getSceneY()- rt.getY();
    }

    protected void handleMouseDragged(MouseEvent e){
        /*
        文本的左边缘位置就是鼠标开始松开后的位置减掉locationX，
        文本的下边缘位置就是鼠标开始松开后的位置减掉locationY
         */
        rt.setX(e.getSceneX()-locationX);
        //rt.setY(e.getSceneY()-locationY);
    }

}
