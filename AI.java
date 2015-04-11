import java.io.File;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class AI implements Runnable {

    public static BufferedImage image1;
    public static int threadcount = 0;
    public static String input = "";
    public static String output = "Loading A I....";
    public static File face;
    public static String toRespond[] = new String[6];
    public static int numToRespond = -1;
    public static List<String> knownWords = new ArrayList<String>();
    public static List<String> funcs4Words = new ArrayList<String>();

    public static void greeting() {
        Random r = new Random();
        int responseToUse = r.nextInt(5 - 1) + 1;
        switch (responseToUse) {
            case 1:
                output = "Hi" + output;
                break;
            case 2:
                output = "Hello" + output;
                break;
            case 3:
                output = "Salutations" + output;
                break;
            case 4:
                output = "Greetings" + output;
                break;
            case 5:
                output = "Hola" + output;
                break;
        }
    }

    public static void generateWords() {
        //example for usandfriends
        knownWords.add("Hi");
        funcs4Words.add("greeting");
    }

    public static void parse(String line) {
        //usandfriends
        //scan sentence for words and replace with simpler words
        System.out.println("Parsing");
        if (line.contains("Hello")) { 
            line.replace("Hello", "Hi");
        }
        //...
        for (int i = 0; i < knownWords.size(); i++) {
            if (line.contains(knownWords.get(i))) {
                if (funcs4Words.get(i)=="greeting") {
                    greeting();
                }  
                if (funcs4Words.get(i)=="other") {
                	//other();
                }
                }
            }
        }

    public static void input() {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	System.out.println("Ready for Input.");
        while (input.length() == 0) {
            try {
                input = br.readLine();
                if (input.length() > 0) {
                    numToRespond++;
                    toRespond[numToRespond] = input;
                    System.out.println("Inputted");
                    input = "";
                }
            } catch (IOException i) {
                System.err.println("Error:" + i);
            }
        }
    }

    public static void respond() {
        //usandfriends:
        while (numToRespond == -1) {
        	try {
        		Thread.sleep(500);
        	}
        	catch (Exception e) {
        		
        	}
        }
        if (numToRespond >= 0) {
            for (int i=-1; i < numToRespond; numToRespond--) {
                parse(toRespond[i]);
            }
        }
    }

    public static void modules() throws Exception {
        AILang.Modules();
    }

    @Override
    public void run() {
        if (threadcount == 1) {
            threadcount++;
            input();
        }

        if (threadcount == 2) {
        	threadcount++;
            	System.out.println("Ready to Respond");
            	respond();
        }
        if (threadcount == 0) {
            threadcount++;
		try {
           	 	modules();
		} catch (Exception e) {

		}
	    System.out.println("Modules Loaded");
        }
    }

    public static void main(String[] args) {
        generateWords();
        System.out.println(output);
        output = "";
        (new Thread(new AI())).start();
        (new Thread(new AI())).start();
        (new Thread(new AI())).start();
        //face recognition
        //get start from webcam
        //train
        while (true) {
            //get photo
            //save photo
	    /*            
	   face = new File("~/new.png");
            try {
                ImageIO.write(image1, "png", face);
            } catch (IOException a) {
                System.out.println("Error");
            }
	    */
            //recognize
            //check to see if person was always there
            //say a response if ^ = false
        }
    }
}
