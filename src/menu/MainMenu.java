package menu;

import java.io.*;
import java.util.Scanner;

public class MainMenu {
    static String fileName = null;
    static Collection collection = new Collection();
    static Scanner scan = new Scanner(System.in);
    static Boolean running = true;

    public static void main(String[] args) {
        while (running) { // that is, while our application  is running
            System.out.println("/nEnter 0 for loading the library."
                    + "/nEnter1 to save and quit."
                    + "/nEnter3 for adding the book to the library.");

            int clientResponse = scan.nextInt();

            switch(clientResponse) {
                case 0:
                    System.out.println("Enter the file name to load.");
                    fileName = scan.next();

                    FileInputStream fis = null;
                    ObjectInputStream scan = null;

                    File file = new File (fileName);
                    if(file.exists()) {
                        try {
                            fis = new FileInputStream(file);
                            scan = new ObjectInputStream(fis);
                            collection = (Collection) scan.readObject();
                            // CONTINUE HERE
                        } catch (IOException e) {
                            e.printStackTrace();
                        } catch (ClassNotFoundException e) {
                            e.printStackTrace();
                        }
                    }else {

                    }
                    break;

                case 1:
                    // ......
                    break;

                case 2:
                    // ......
                    break;

                case 3:
                    // ......
                    break;


            }


        }

    }
}