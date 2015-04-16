
import java.io.File;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.Arrays.*;

public class AI implements Runnable {

	public static List<ArrayList<String>> synonyms = new ArrayList<>();
	public static String oldLine;
    public static List<String> allParts = new ArrayList<String>();
    public static int chars;
    public static BufferedImage image1;
    public static int threadcount = 0;
    public static String input = "";
    public static String output = "Loading A I....";
    public static File face;
    public static List<String> toRespond = new ArrayList<String>();
    public static int numToRespond = -1;
    public static List<String> knownWords = new ArrayList<String>();
    public static List<String> funcs4Words = new ArrayList<String>();

    public static void greeting() {
        Random r = new Random();
        int responseToUse = r.nextInt(5 - 1) + 1;
	int response2 = r.nextInt(12-1) + 2;
	if (response2 > 5) {
		response2 = response2/2;
		responseToUse += response2;
		responseToUse = responseToUse/2;		
	}
        switch (responseToUse) {
            case 1:
                output = "Hi, " + output;
                break;
            case 2:
                output = "Hello, " + output;
                break;
            case 3:
                output = "Salutations, " + output;
                break;
            case 4:
                output = "Greetings, " + output;
                break;
            case 5:
                output = "Hola, " + output;
                break;
        }
    }

    public static void generateWords() {
        //still need past and future tense
        knownWords.add("Hi");
	synonyms.add(new ArrayList<String>(Arrays.asList("Hello", "hi", "Salutations", "Greetings", "Hola", "Yo", "yo", "hola", "greetings", "salutations", "hello")));
        funcs4Words.add("greeting");

	knownWords.add("is");
	synonyms.add(new ArrayList<String>(Arrays.asList("am", "are")));
	funcs4Words.add("am");

	knownWords.add("W...");
	synonyms.add(new ArrayList<String>(Arrays.asList("What", "Who", "When", "Where", "Why")));
	funcs4Words.add("past_data");

	knownWords.add("Can");
	synonyms.add(new ArrayList<String>(Arrays.asList("May", "can")));
	funcs4Words.add("able");

	knownWords.add("Bye");
	synonyms.add(new ArrayList<String>(Arrays.asList("Farewell", "bye", "Goodbye", "Seeya")));
	funcs4Words.add("farewell");

	knownWords.add("have");
	synonyms.add(new ArrayList<String>(Arrays.asList("owns", "has")));
	funcs4Words.add("possess");

	knownWords.add("want");
	synonyms.add(new ArrayList<String>(Arrays.asList("desire")));
	funcs4Words.add("want");

	knownWords.add("need");
	synonyms.add(new ArrayList<String>(Arrays.asList("require")));
	funcs4Words.add("need");

	knownWords.add("doing");
	synonyms.add(new ArrayList<String>(Arrays.asList("completing")));
	funcs4Words.add("action");
	
	knownWords.add("data");
	//need bigger numbers
	//edit number time and name to actually be those things, not just words
	synonyms.add(new ArrayList<String>(Arrays.asList("number", "time", "name", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "ten", "zero")));
	funcs4Words.add("log");
	
	knownWords.add("a");
	synonyms.add(new ArrayList<String>(Arrays.asList("an", "the")));
	funcs4Words.add("article");

	knownWords.add("some");
	synonyms.add(new ArrayList<String>(Arrays.asList("few", "couple", "bunch")));
	funcs4Words.add("few");

	knownWords.add("it");
	synonyms.add(new ArrayList<String>(Arrays.asList("him", "he", "she", "her")));
	funcs4Words.add("singularPronoun");

	knownWords.add("them");
	synonyms.add(new ArrayList<String>(Arrays.asList("they", "boys", "girls")));
	funcs4Words.add("multiplePronoun");
	//fix... throws Index out of bound
	//removes repeats and all allows lower case words
	/*
	for (int i = synonyms.size(); i > 1; i--) {
		for (int z = synonyms.get(i).size(); z > 1; z--) { 
			synonyms.get(i).add(synonyms.get(i).get(z).toLowerCase()); 
		}
		for (int z = synonyms.get(i).size(); z > 1; z--) {
			for (int x = synonyms.get(i).size(); x > 1; x--) { 
				if (synonyms.get(i).get(z).equals(synonyms.get(i).get(x))) { 
					synonyms.get(i).remove(x); } 
			}
		}
	}
	*/
    }
    
    public static void parse(String line) {
        //scan sentence for words and replace with simpler words
        //update synonym system with loops
        //oldLine is so instead of seeing data, the program will see data and then go back and see what the data is
        oldLine = line;
        line = line.toLowerCase();
	//THIS needs to be replaced with loops
        if (line.contains("hello")) {
            line = line.replaceAll("hello", "Hi");
        }
        if (line.contains("hi")) {
        	line = line.replaceAll("hi", "Hi");
        }
            //load from beginning into list
            allParts = Arrays.asList(line.split(" "));
            for (int i = 0; i < allParts.size(); i++) {
                for (int z = 0; z < knownWords.size(); z++) {
                    if (knownWords.get(z).equals(allParts.get(i))) {
                        switch (funcs4Words.get(z)) {
                            case "greeting":
                                greeting();
                                break;
                                //rest of functions
                        }
                    }
                }
        }
        //clear vars and output
        oldLine = "";
        for(int i = 0; i < allParts.size(); i++) { allParts.set(i, null); }
        System.out.println(output);
        output = "";
        toRespond.remove(numToRespond);
    }

    public static void input() {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Ready for Input.");
        while (input.length() == 0) {
            try {
                input = br.readLine();
                if (input.length() > 0) {
                    numToRespond++;
                    toRespond.add(input);
                    input = "";
                }
            } catch (IOException i) {
                System.err.println("Error:" + i);
            }
        }
    }

    public static void respond() {
        while (true) {
            while (numToRespond == -1) {
                try {
                    Thread.sleep(500);
                } catch (Exception e) {

                }
            }
            if (numToRespond >= 0) {
                    parse(toRespond.get(0));
                    numToRespond--;
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
