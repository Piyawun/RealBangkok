package game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Score implements MouseListener {

    private Game IGame;

    /**
     *
     * @param game set state form class game
     */
    public Score(Game game) {
        IGame = game;
    }

    /**
     * สร้าง BufferReader ชื่อ reader และ ดึงข้อมูลไฟล์ score จาก  score.txt โดยใช้ FileReader เข้าถึง path ที่กำหนด
     * @param g
     * @throws IOException check error for read file
     *  ใช้ g graphic เพื่อ แสดงตัวอีกษรโดยใช้ method drawString แสดงขึ้นมา
     */
    public void draw(Graphics g) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("score.txt"));
        String line = reader.readLine();

        ImageIcon imageIcon = new ImageIcon("image/Score.png");
        Image image = imageIcon.getImage();
        g.drawImage(image,0,0,Project.WIDTH,Project.HEIGHT,null);
        g.setFont(new Font("Roboto Slab", Font.CENTER_BASELINE, 50));
        g.drawString(line,390,251);
    }



    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    /**
     *
     * @param e Mouse click Event
     *  ใช้ IGame ใช้ method setState และส่งค่า enum.STATE.MENU เพื่อทำการเปลี่ยนหน้าแสดงผล
     */
    @Override
    public void mouseReleased(MouseEvent e) {
        int mouseX = e.getX();
        int mouseY = e.getY();


        if(Game.state == Game.STATE.SCORE){
//        Button Play
            if(mouseX >= 681  && mouseY >= 379 && mouseX <= 836 && mouseY <= 448){
                IGame.setState(Game.STATE.MENU);
            }
        }

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}