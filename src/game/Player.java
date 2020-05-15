package game;

import javax.swing.*;
import java.awt.*;


public class Player {
    private int x , y;
    private double speedY = 0;
    private double gravity = 1.50; // Can tune
    private int canJump = 2;
    private boolean canFall = false;
    private Image playerImage;


    /**
     * set position x , y to player
     * @param x
     * @param y
     */
    public Player(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * อัพเดท แกน Y และเช็คความสูงของการกระโดด โดย  กำหนดให้ canFall เป็น true
     * HEIGHT / 2 + 100 = 340
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
     * Method jump จะกำหนด speedY ความสูงของการกระโดด ที่ -22 และ ลบ canJump - 1
                    */
    public void jump() {
        if(canJump > 0){
            speedY = -22;
            canJump--;
        }
        fall();

    }

    /**
     * Method fall
     * เพื่อทำให้ SpeedY เพิ่มขึ้นเลื่อยๆโดยเพิ่มเรื่อยๆทีละ 1.50 ทำให้กระโดดมีความสมูทมากขึ้น โดยเอา -22 += 1.5
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
    public int isJump() {
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
     * @param canJump รับค่าที่เป็น int และนำค่า int ที่ได้รับไปเซตค่าการกระโดดของวัตถุ
     */
    public void setJump(int canJump) {
        this.canJump = canJump;
    }


}