package game;

import javax.swing.*;
import java.io.IOException;

public class Project {
    public static Project project;
    public Game game = new Game();
    public final static int WIDTH = 860;
    public final static int HEIGHT = 480;

    public Project() throws IOException {
        JFrame window = new JFrame();
        window.setTitle("REAL BANGKOK");
        window.setUndecorated(true);
        window.add(game);
        window.setSize(WIDTH, HEIGHT);
        window.setResizable(false);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setVisible(true);
        window.setLocationRelativeTo(null);
    }

    public static void main(String[] args) throws IOException {
        project = new Project();
        System.out.println("StartGame !");
    }
}
