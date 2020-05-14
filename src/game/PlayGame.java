package game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import java.util.Random;

public class PlayGame{
    private boolean play;
    private Player player;
    private Obstacle obstacle;
    private Random rand = new Random();


    public void draw(Graphics g) {
        ImageIcon imageIcon = new ImageIcon("image/Play.png");
        Image image = imageIcon.getImage();
        g.drawImage(image,0,0,Project.WIDTH,Project.HEIGHT,null);
        g.setFont(new Font("Roboto Slab", Font.PLAIN, 20));
        g.drawString(Integer.toString(Game.sumScore),724,56);
    }




}