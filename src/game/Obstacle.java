package game;

import javax.swing.*;
import java.awt.*;
import java.util.Hashtable;
import java.util.Random;

public class Obstacle {
    private int x;
    private int y;
    private double speedFactor = 1;
    Image obstacleIcon;

    /**
     *
     * @param x รับค่าแกน x ของ  วัตถุ
     * @param y รับค่าแกน y ของ  วัตถุ
     * สร้าง ArrayDict เพื่อ เก็บข้อมูลของ รูปภาพของวัตถุ และ กำหนด id
     * จากนั้นใช้ function random เพื่อ สุ่มรูปที่จะส่งออกไปแสดงผล
     */
    public Obstacle(int x, int y) {

        this.x = x;
        this.y = y;
        Hashtable<Integer, String> imageDict = new Hashtable<Integer, String>();

        imageDict.put(0, "image/motorbike.png");
        imageDict.put(1, "image/car.png");
        imageDict.put(2, "image/semi.png");

        Random rand = new Random();

        ImageIcon icon = new ImageIcon(imageDict.get(rand.nextInt(imageDict.size())));
        obstacleIcon = icon.getImage();
    }

    public Obstacle(int x,int y,double speedFactor){
        this(x, y);
        this.speedFactor = speedFactor;
    }

    /**
     * obstacles -=15 จาก 860 -= 15 จนถึง 110 จะหายไป and * speedFactor
     */
    public void move() {
        x -= 15 * this.speedFactor;
    }

    /**
     * ส่ง รูปภาพของวัตกุออกไปแสดงผล โดยกำหนด ขนาดของ รูป คือ 60x60
     * @param g
     */
    public void draw(Graphics g) {
        g.drawImage(obstacleIcon, x, y, 60, 60, null);
    }

    /**
     * get position x form Obstacle
     * @return position x
     */
    public int getX() {
        return x;
    }

    /**
     * get position y form Obstacle
     * @return position y
     */
    public int getY() {
        return y;
    }

    /**
     * @PARAM x set position x to Obstacle
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     *
     * @PARAM Y set position y to Obstacle
     */
    public void setY(int y) {
        this.y = y;
    }

    public void setSpeedFactor(double speedFactor) {
        this.speedFactor = speedFactor;
    }

}