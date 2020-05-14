package game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.ImageObserver;

public class StartMenu implements MouseListener {

    private Game IGame;

    public StartMenu(Game game) {
        IGame = game;
    }


    public void draw(Graphics g) {
        ImageIcon imageIcon = new ImageIcon("image/Menu.png");
        Image image = imageIcon.getImage();
        g.drawImage(image,0,0,Project.WIDTH,Project.HEIGHT,null);
    }



    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        int mouseX = e.getX();
        int mouseY = e.getY();


    if(Game.state == Game.STATE.MENU){
//        Button Play
        if(mouseX >= 358 && mouseY >= 330 && mouseX <= 505 && mouseY <= 410){
            IGame.setState(Game.STATE.GAME);


        }
//        Button Score
        if(mouseX >= 85 && mouseY >= 330 && mouseX <= 235 && mouseY <= 410){
            IGame.setState(Game.STATE.SCORE);

        }
        //        Button Exit
        if(mouseX >= 633  && mouseY >= 330 && mouseX <= 788 && mouseY <= 410){
            IGame.setState(Game.STATE.EXIT);

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