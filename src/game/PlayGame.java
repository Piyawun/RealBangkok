package game;

import javax.swing.*;
import java.awt.*;



public class PlayGame{



    public void draw(Graphics g) {
        ImageIcon imageIcon = new ImageIcon("image/Play.png");
        Image image = imageIcon.getImage();
        g.drawImage(image,0,0,Project.WIDTH,Project.HEIGHT,null);
        g.setFont(new Font("TimesRoman", Font.PLAIN, 20));
        g.drawString(Integer.toString(Game.sumScore),724,56);
    }




}