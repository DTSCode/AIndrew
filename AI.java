package ai;

import java.io.File;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class AI implements Runnable {

    public static BufferedImage image1;
    public static int threadcount = 0;
    public static String input = "";
    public static String output = "Loading A I....";
    public static File face;
    public static String toRespond[] = new String[6];
    public static int numToRespond = -1;

    public static void input() {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while (input.length() == 0) {
            try {
                input = br.readLine();
                if (input.length() > 0) {
                    numToRespond++;
                    toRespond[numToRespond] = input;
                    input = "";
                }
            } catch (IOException i) {
                System.err.println("Error:" + i);
            }
        }
    }

    public static void respond() {

    }

    public static void modules() {
        AILang.Modules();
    }

    @Override
    public void run() {
        if (threadcount == 1) {
            input();
            threadcount++;
        }

        if (threadcount == 2) {
            respond();
            threadcount++;
        }
        if (threadcount == 0) {
            modules();
            threadcount++;
        }
    }

    public static void main(String[] args) {
        System.out.println(output);
        (new Thread(new AI())).start();
        (new Thread(new AI())).start();
        (new Thread(new AI())).start();
        //face recognition
        //get start from webcam
        //train
        while (true) {
            //get photo
            //save photo
            face = new File("~/new.png");
            try {
                ImageIO.write(image1, "png", face);
            } catch (IOException a) {
                System.out.println("Error");
            }
        //recognize
            //check to see if person was always there
            //say a response if ^ = false
        }
    }
}
