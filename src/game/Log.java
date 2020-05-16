package game;

import javax.swing.*;
import java.io.*;
import java.net.InetAddress;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Log<log> {

    private String startTime;
    private Date today;

    public void writeLogfile() {
        String line = "";
        File inFile = new File("log.txt"); //สร้าง logfile ขึ้นมา
        File writeFile;
        SimpleDateFormat timeToString = new SimpleDateFormat("dd/MM/yy H:m:s"); //สร้าง Format ในการบันทึกข้อมูล
        today = new Date(); // new Date


        try {
            if (inFile.exists()) { // check File ว่า มีอยู่หรือไม่ ค่าจะออกมาเป็น true or false ถ้าเป็น false จะไป create new File

                Scanner sc1 = new Scanner(inFile); //new scanner ที่ชื่อ sc1

                while (sc1.hasNextLine() || inFile.length() == 0) {
                    if (inFile.length() != 0) {  //check ข้อมุลภายในไฟล์ ถ้า ไม่เท่ากับ 0 จะสร้างขึ้นมาใหม่
                        line = line + sc1.nextLine() + "\r\n"; // ขึ้นบรรทัดใหม่
                    }

                    writeFile = new File("log.txt");
                    FileOutputStream outFS = new FileOutputStream(writeFile);
                    PrintWriter outS = new PrintWriter(outFS);
                    outS.print(line + "Start game at " + timeToString.format(today)); //write content
                    outS.close();

                }

            } else {

                writeFile = new File("log.txt");
                FileOutputStream outFS = new FileOutputStream(writeFile);
                PrintWriter outS = new PrintWriter(outFS);
                outS.println("Start game at " + timeToString.format(today)); //waite content
                outS.close();

            }
        } catch (FileNotFoundException e) {
            System.out.println("Error !! : " + e.getMessage());
        }

    }

    /**
     * saveHeighScore
     * @param score
     * @throws IOException
     */
    public static void saveHeighScore(int score) throws IOException {
        File inFile = new File("score.txt");
        File writeFile;
        boolean done = false;
        try {

            if (inFile.exists()) { //check status file return true or false

                BufferedReader reader = new BufferedReader(new FileReader(inFile)); //อ่านไฟล์ โดย BufferedReader และ ใส่ paremeter new cobject FileReader ใส่ parameter เป็น path ของ ไฟล์
                String line = reader.readLine();
//

                if (line != null) {
                    int oldScore = Integer.parseInt(line);
                    if (oldScore < score) { // check score ถ้า oldScore < score จะใช้ class saveNameHighScore เพื่อเก็บข้อมูลผู้เล่น

                        saveNameHighScore(score);
                        writeFile = new File("score.txt");
                        FileOutputStream outFS = new FileOutputStream(writeFile);
                        PrintWriter outS = new PrintWriter(outFS);
                        outS.println(score); //Write Score
                        outS.close();
                    }
                }
            } else {

//                First time to play create this loop
                writeFile = new File("score.txt");
                inFile.createNewFile();
                FileOutputStream outFS = new FileOutputStream(writeFile);
                PrintWriter outS = new PrintWriter(outFS);
                saveNameHighScore(score);
                outS.println(score);//Write Score
                outS.close();

            }
        } catch (FileNotFoundException e) {
            System.out.println("Error !! : " + e.getMessage());
        }
    }

    /**
     *  SaveNameHighScore
     * @param score
     */
    public static void saveNameHighScore(int score) {
        String line = "";
        File inFile = new File("saveTopScore.txt");
        File writeFile;
        String name;

        try {

            name = JOptionPane.showInputDialog(null, "Top score \n Your score is "+ score + "\nPlease Enter your name"); //รับค่าชื่อของผู้เล่น

            while (name == null || name.length() <= 1){ // check value name is null or length of name <= 1 เพื่อรับข้อมูลอีกรอบโดยใช้ while loop
                JOptionPane.showMessageDialog(null,"Please enter your name","Alert",JOptionPane.WARNING_MESSAGE);
                name = JOptionPane.showInputDialog(null, "Top score \n Your score is "+ score + "\nPlease Enter your name");
            }
            byte[] bytes = name.getBytes(StandardCharsets.UTF_8);
            String nameEncode = new String(bytes, StandardCharsets.UTF_8);

            InetAddress ip = InetAddress.getLocalHost(); //getDetail form computer

            LineNotify lineNotify = new LineNotify(); //create object lineNotify
            lineNotify.callEvent(line + "\nName : " + nameEncode + "\nScore : " + score + "\nForm : " + ip); //lineNotify ใช้ method callEvent เพื่อส่งข้อมูลผ่าน LineNotify

            try {
                if (inFile.exists()) { //check status file

                    Scanner sc1 = new Scanner(inFile); //create object Scanner


                    while (sc1.hasNextLine() || inFile.length() == 0) {
                        if (inFile.length() != 0) {  // Edit here insert new if statement for file empty will execute
                            line = line + sc1.nextLine() + "\r\n";
                        }

                        writeFile = new File("saveTopScore.txt");
                        FileOutputStream outFS = new FileOutputStream(writeFile);
                        PrintWriter outS = new PrintWriter(outFS);
                        outS.print(line + "Name : " + nameEncode + " Score : " + score); //Edit here
                        outS.close();

                    }

                } else {
                    writeFile = new File("saveTopScore.txt");
                    FileOutputStream outFS = new FileOutputStream(writeFile);
                    PrintWriter outS = new PrintWriter(outFS);

                    outS.print(line + "\nName : " + nameEncode + " Score : " + score); //Edit here
                    outS.close();
                }
            } catch (FileNotFoundException e) {
                System.out.println("Error !! : " + e.getMessage());
            }

        } catch (Exception e) {
            System.out.println("Error !! : " + e.getMessage());

        }


    }



}
