
import java.io.File;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class AI implements Runnable {

    public static String fromBeginningPart[] = new String[10];
    public static List<String> allParts = new ArrayList<String>();
    public static int chars;
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

	public static String[] removeNull(String[] a) {
		ArrayList<String> notNull = new ArrayList<String>();
   		for (String str : a)
      		if (str != null)
         		notNull.add(str);
   		return notNull.toArray(new String[0]);
	}

    public static int getCharsInStringArr(String[] stringArr) {
    	stringArr =  removeNull(stringArr);
        int j = 0;
        for (int i = 0; i < stringArr.length; i++) {
            System.out.println("In loop");
            j += stringArr[i].length();
            System.out.println("Got length");
        }
        return j;
    }

    public static void parse(String line) {
        //usandfriends
        //scan sentence for words and replace with simpler words
        //update synonym system with loops and a List of synonyms to be replaced with simpler words and then parsed below instead of 1mil ifs checks
        line = line.toLowerCase();
        if (line.contains("hello")) {
            line = line.replaceAll("hello", "Hi");
        }
        if (line.contains("hi")) {
        	line = line.replaceAll("hi", "Hi");
        }
        //...
        //below method won't guarantee proper sentence structure, break along the spaces and do per word, not per first items in list
/*
        for (int i = 0; i + 1 <= knownWords.size(); i++) {
            if (line.contains(knownWords.get(i))) {
                if (funcs4Words.get(i) == "greeting") {
                    greeting();
                }
                if (funcs4Words.get(i) == "other") {
                    //other();
                }
            }
        }
*/
        //correct method
        while (line != "") {
            int count = line.length() - line.replace(" ", "").length();
            fromBeginningPart = line.split(" ", count);
            for (int i = 0; i < fromBeginningPart.length; i++) {
                allParts.add(fromBeginningPart[i]);
            }
            System.out.println("Broken up... getting chars");
            chars += getCharsInStringArr(fromBeginningPart);
            System.out.println("Got quantity: " + chars);
            //Fix
            if ((line.length() - line.replace(" ", "").length()) >= 1) {
                line = line.replaceAll(line.substring(0, chars + ((line.split(" ").length -1))), "");
            }
            if ((line.split(" ").length - 1) < 1) {
            	line = line.replaceAll(line.substring(0, (chars - 1)), "");
            }
            //
            for (int i = 0; i < fromBeginningPart.length; i++) {
                fromBeginningPart[i] = "";
            }
            System.out.println("Deleted beginning of String");
            for (int i = 0; i < allParts.size(); i++) {
                for (int z = 0; z < knownWords.size(); z++) {
                    if (knownWords.get(z).equals(allParts.get(i))) {
                        switch (funcs4Words.get(z)) {
                            case "greeting":
                                greeting();
                                break;
                            case "farewell":
                                break;
                        }
                    }
                }
            }
        }
        //
        allParts.clear();
        while(allParts.remove(null));
        System.out.println(output);
        output = "";
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
                    input = "";
                }
            } catch (IOException i) {
                System.err.println("Error:" + i);
            }
        }
    }

    public static void respond() {
        //usandfriends:
        while (true) {
            while (numToRespond == -1) {
                try {
                    Thread.sleep(500);
                } catch (Exception e) {

                }
            }
            if (numToRespond >= 0) {
                for (int i = -1; i < numToRespond; numToRespond--) {
                    parse(toRespond[numToRespond]);
                }
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
            respond();
        }
        if (threadcount == 0) {
            threadcount++;
            try {
                modules();
            } catch (Exception e) {

            }
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
