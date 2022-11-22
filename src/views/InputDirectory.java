package views;

import java.io.File;
import java.util.Scanner;

public class InputDirectory {

    public String getDirectory() {
        System.out.print( "Path: ");
        Scanner scan = new Scanner(System.in);
        String file = scan.nextLine();
        return file;
    }

    public boolean validator(String dirPath) {
        File file = new File(dirPath);
        return file.exists() && file.isDirectory();
    }
}
