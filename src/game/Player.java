package game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Player {
    private int x;
    private int y;
    private double speedY = 0;
    private double gravity = 1.55; // Can tune
    private boolean canJump = true;
    private boolean canFall = false;
    private Image playerImage;


    /**
     * get position x , y form player
     * @param x ค่าแนวแกน X
     * @param y ค่าแนวแกน Y
     */
    public Player(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * อัพเดท แกน Y และเช็คความสูงของการกระโดด โดย  กำหนดให้ canFall เป็น true
     */
    public void update() {
        if (y < Project.HEIGHT / 2 + 100) {
            canFall = true;
            fall();
        }

        y += speedY;
    }

    /**
     * สร้าง ImageIcon โดยใช้ชื่อว่า icon และเข้าถึง path รูปของตัวละคร
     * สั่ง Graphics drawImage และรับค่าของ playerImage  x y  และ ขนาดของรูปที่กำหนด
     * @param g
     */
    public void draw(Graphics g) {

        ImageIcon icon = new ImageIcon("image/human.gif");
        playerImage = icon.getImage();
        g.drawImage(playerImage, x, y, 60, 60, null);
    }

    /**
     * Method jump จะกำหนด speed Y ที่ -22 ค่าความสูงจะติดลบ และถ้า
     */
    public void jump() {
        if (canJump) {
            speedY = -22;
            canJump = false;
        }
    }

    /**
     * Method fall
     */
    public void fall() {
        if (canFall) {
            speedY += gravity;
        }
    }

    /**
     *  getPositon x
     * @return position x
     */
    public int getX() {
        return x;
    }

    /**
     * getPosition Y
     * @return position y
     */
    public int getY() {
        return y;
    }

    /**
     * get isFall
     * @return boolean canFall
     */
    public boolean isFall() {
        return canFall;
    }

    /**
     * get isJump
     * @return canJump
     */
    public boolean isJump() {
        return canJump;
    }

    /**
     * setPosition y
     * @param  รับ ค่า แกน Y ความสูง และนำไปเซทค่า y
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * setSpeed ของแกน Y ในการกระโดด
     * @param รับค่า speed  และเซทค่า Limit ของการกระโดด
     */
    public void setSpeedY(double speedY) {
        this.speedY = speedY;
    }

    /**
     * setFall
     * @param canFall รับค่าที่เป็น boolean และนำค่า boolean ที่ได้รับไปเซตค่า ของการตกลงของวัตถุ
     */
    public void setFall(boolean canFall) {
        this.canFall = canFall;
    }

    /**
     * setJump
     * @param canJump รับค่าที่เป็น boolean และนำค่า boolean ที่ได้รับไปเซตค่าการกระโดดของวัตถุ
     */
    public void setJump(boolean canJump) {
        this.canJump = canJump;
    }


}