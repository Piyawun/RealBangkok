package game;

import javax.swing.*;
import javax.swing.Timer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.*;

import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

public class Game extends JPanel implements ActionListener, KeyListener {
    private static final long serialVersionUID = 1L;

    private Score scoreMenu;
    private StartMenu startMenu;
    private GameOver gameOverMenu;
    private Score scorePanel;
    private PlayGame playGamePanel;


    private Player player;
    private List<Obstacle> obstacles = new ArrayList<Obstacle>(); // create List object เพื่อ ใช้งาน List ที่ชื่อว่า obstacles

    private int rand;
    private int score;
    private boolean play;
    private int tickCount = 0;
    static int sumScore = 0;
    private int speed = 50;

    /**
     * สร้าง enum เป็น ค่าคงที่เพื่อ นำไปใช้ ในการเลือก STATE
     */
    enum STATE {
        MENU,
        GAME,
        GAME_OVER,
        SCORE,
        EXIT
    }

    ;

    Timer timer = new Timer(20, this);

    //กำหนด STATE = MENU เพื่อ กำหนดว่า STATE แรกเมื่อกดเข้ามาจะดึงหน้า  MENU มาใช้
    static STATE state = STATE.MENU;

    /**
     * สร้าง Object ขึ้นมาใหม่ และภายใน จะเป็นการ init object ของแต่ละClass ด้วย
     */
    public Game() {

        startMenu = new StartMenu(this);
        scoreMenu = new Score(this);
        gameOverMenu = new GameOver(this);
        scorePanel = new Score(this);
        playGamePanel = new PlayGame();

        Log log = new Log();
        log.writeLogfile();


        timer.start();

        player = new Player(150, 360);

        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        addMouseListener(startMenu);
        addMouseListener(scoreMenu);
        addMouseListener(gameOverMenu);
        setState(STATE.MENU);
    }


    /**
     * set state
     * @param newstate
     * รับค่า newstate เข้ามาที่เป็น STATE และ นำ state เลือก condition โดยการใช้ switch เพื่อเลือก ตัวเลือกของ STATE
     */
    public void setState(STATE newstate) {
        System.out.println("Change new state form " + state + " to " + newstate);
        state = newstate;
        switch (state) {
            case MENU:
                play = true;
                break;
            case GAME:
                obstacles.clear();
                tickCount = 0;
                score = 0;
                sumScore = 0;
                this.speed = 50;
                break;
            case GAME_OVER:
                break;
            case EXIT:
                System.exit(0);
                break;
        }
    }

    public int setSpeed(int min, int max){

        if (min > max || (max - min + 1 > Integer.MAX_VALUE)) {
            throw new IllegalArgumentException("Invalid range");
        }
        rand = new Random().nextInt(max - min + 1) + min;
        return rand;
    }

    /**
     * create pain class for state choose STATE
     * @param g
     */
    @Override
    public void paint(Graphics g) {
        super.paint(g);

        switch (state) {
            case MENU:
                startMenu.draw(g); //สั่ง class startMenu เพื่อสั่งวาดหน้าเจอแสดงผล และ ต้องส่ง parameter เพื่อกสั่งให้ทำงาน
                play = true;
                break;
            case GAME:



                if (tickCount % speed == 0) { //แบ่งการ new object

                    if(sumScore > 70){
                        speed = setSpeed(10,50);
                        obstacles.add(new Obstacle(860, 360, 1.2)); // List obstacles เพื่อ add class object Obstacle เข้าไปใน List
                    }else if(sumScore > 500){
                        speed = setSpeed(5,10);
                        obstacles.add(new Obstacle(860, 360,1.5)); // List obstacles เพื่อ add class object Obstacle เข้าไปใน List
                    }else{
                        obstacles.add(new Obstacle(860, 360)); // List obstacles เพื่อ add class object Obstacle เข้าไปใน List
                    }

                    tickCount = 0;

                }


                playGamePanel.draw(g); //สั่ง class playGamePanel เพื่อสั่งวาดหน้าเจอแสดงผล และ ต้องส่ง parameter เพื่อกสั่งให้ทำงาน

                player.draw(g); //สั่ง player วาดตัวละครขค้นมา

                //ใช้ forEach เพื่อเข้าถึงข้อมูลภายใน List obstacles
                obstacles.forEach(obstacle -> {
                    obstacle.move(); //obstacle  ใช้ method move เพื่อทำให้ obstacle เคลื่อนไปทางซ้าย
                    obstacle.draw(g); //obstacle ใช้ draw และส่ง parameter g เพื่อให้ทำงาน


                    if (120 <= obstacle.getX() && 180 >= obstacle.getX() && player.getY() >= 300) { //เช็ค การกระทบกันระหว่าง 2 Object ของ Player และ Obstacle
                        setState(STATE.GAME_OVER); // set state to GAME_OVER
                        try {
                            Log.saveHeighScore(sumScore); // insert score to logfile
                        } catch (IOException e) {
                            System.out.println("Error !! : " + e.getMessage());
                        }

                    }
                });

                obstacles = obstacles.stream().filter(obstacle -> obstacle.getX() >= -20).collect(Collectors.toList()); //จะเก็บข้อมูล obstacle >= -60 ถ้า ไม่ได้อยู่ในเงื่อนไขนี้จะถูกลบออก

                break;
            case SCORE:
                try {
                    scorePanel.draw(g); // ScorePanel  ใช้ Method draw ส่ง parameter G เพื่อแสดงผลขึ้นหน้าจอ
                } catch (IOException e) {
                    System.out.println("Error !! :" + e.getMessage());
                }
                break;
            case GAME_OVER:
                gameOverMenu.draw(g); // GameOverMenu ใช้ Method draw ส่ง parameter G เพื่อแสดงผลขึ้นหน้าจอ
                break;
            default:
        }
        tickCount++;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (state) {
            case GAME:
                if (play) { //ถ้า play check condition เป็น true  จะสั่งให่เกมส์ เริ่ม นับคะแนน และ ทำงาน
                    score = score + 2;
                    sumScore = score / 20;
                    player.update(); //update การขยับของ object player
                    if (player.isFall() && player.getY() >= 360) { // check การกระโดด และเช็ค player.getY ถ้า ค่า Y ของ Player >= 360 จะสามารถกระโดดได้
                        player.setY(360);
                        player.setSpeedY(0);
                        player.setFall(false);
                        player.setJump(2);
                    }
                }
            default:
                repaint(); // repaint component
        }
    }


    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override

    public void keyPressed(KeyEvent e) {
        // Jump
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            player.jump();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }


}