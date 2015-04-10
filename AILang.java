package AI;

import java.io.File;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class AILang throws Exception {
    
    String files[];
    
    public static void Modules() {
        String workingDir = System.getProperty("user.dir");
        File folder = new File(workingDir + "/modules/");
        File[] listOfFiles = folder.listFiles();
        files = new String[listOfFiles.length] ;
        int count = 0;
        for(File junk : listOfFiles) {
            files[count] = junk;
            count++;
         }
	//list of modules now in files[]
	//open files
	//parse files
	//keep checking for files
	//parsing should have support for variables, sockets, files, and functions
    }
    
}
