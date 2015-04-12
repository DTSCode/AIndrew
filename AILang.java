
import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;

public class AILang implements Runnable {

    public static String files[];
    public static int i;
    public static BufferedReader r;

    public static void parse(String code) throws Exception {
        int coloncount = 0;
        for (int f = 0; f < code.length(); f++) {
            char test = code.charAt(f);
            if (test == ';') {
                coloncount++;
            }
        }
        String[] lines = new String[coloncount];
        lines = code.split(";");
        for (int f = 0; f < lines.length; f++) {

        }
    }

    public void run() {
        //open and parse files
        //parsing should have support for vars, sockets, files, functions, loops, checks, and math operations. Each file should have a list of tags for related words
        try {
            r = new BufferedReader(new FileReader(new File(files[i])));
            String s = r.readLine();
            while (s != null) {
                parse(s);
                s = r.readLine();
            }
            r.close();
        } catch (Exception z) {

        }
    }

    public static void Modules() throws Exception {
        System.out.println("Loading Modules");
        String workingDir = System.getProperty("user.dir");
        File folder = new File(workingDir + "/modules/");
        File[] listOfFiles = folder.listFiles();
        files = new String[listOfFiles.length];
        int count = 0;
        for (File junk : listOfFiles) {
            files[count] = junk.getAbsolutePath();
            count++;
        }
        //list of modules now in files[]
        for (i = 0; i < files.length; i++) {
            (new Thread(new AILang())).start();
            Thread.sleep(50);
        }
        Thread.sleep(10000);
        System.out.println("Done loading modules");
        //modules require reboot to load again as to save memory of infinite arrays... a list could be used.... to lazy right now :p
    }

}
