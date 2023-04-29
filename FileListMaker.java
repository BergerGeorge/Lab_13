import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import static java.util.Collections.list;
import java.util.Scanner;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class FileListMaker
{

    static ArrayList<String> list = new ArrayList<>();

    public static void main(String[] args)

    {
        Scanner console = new Scanner(System.in);

        final String menu = "O - Open S - Save A - Add D - Delete C - Clear V- View Q - Quit";
        boolean done = false;
        String cmd = "";
        String item = "";
        boolean quit = false;
        int itemNumber = 0;
        boolean clear = false;
        boolean needsToBeSaved = false;
        boolean save = false;
        do
        {
            //display the list
            displayList();
            //display the menu options
            // get a menu choice
            cmd = SafeInput.getRegExString(console, menu, "[OoSsAaDdCcVvQq]");
            cmd = cmd.toUpperCase();
            //execute the choice
            switch(cmd)
            {
                case "O":
                    // Prompt the user which file they want to open
                    //Display the file chooser
                    //Read in the file they select
                    PrintWriter out; // write to the file
                    Scanner in; // in is the file we are reading
                    Scanner input = new Scanner(System.in); // read from the keyboard
                    File selectedFile;
                    JFileChooser chooser = new JFileChooser();
                    String line;
                    String outFileName = "";
                    String defaultFileName = "default.txt";
                    int lineCount = 0;

                    try
                    {
                        // Display FileChooser Wizard for user to select a file to open
                        // check to make sure user actually picked a file to open
                        // if they did, go ahead and read it and dump it to the screen

                        // Set the JFileChooser to use the Netbeans projcet folder
                        File workingDirectory = new File(System.getProperty("user.dir"));
                        chooser.setCurrentDirectory(workingDirectory);

                        if(chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION)
                        {
                            selectedFile = chooser.getSelectedFile();  // this is a File object not a String filename
                            in = new Scanner(selectedFile);  // Open the file for reading
                            //  sets the scanner to read from the file NOT the console

                            while(in.hasNextLine())
                            {
                                line = in.nextLine();
                                lineCount++;

                                // Show the file on the console
                                System.out.printf("\nLine %3d: %-30s", lineCount, line);
                                // But write the numbered line to the output file

                            }
                            System.out.println("");
                            // Don't forget to close the file

                            in.close();

                        }
                        else  // they didn't select a file so exit...
                        {
                            JOptionPane.showMessageDialog(null, "Cancelled by User.  Exiting...");
                            System.exit(0);
                        }
                    }
                    catch (FileNotFoundException ex)
                    {
                        System.out.println("File not found error!");
                    }
                    catch (IOException ex)
                    {
                        ex.printStackTrace();
                    }
                case "A":
                    // Prompt the user for a list item
                    //make sure that it is not an empty string

                    item = SafeInput.getNonZeroLenString(console, "Enter the item you would like to add");

                    // Add it to the list
                    list.add(item);
                    break;
                case "D":
                    // Prompt the User for the number of the item to delete
                    itemNumber = SafeInput.getInt(console, "Enter the number of the item you would like to delete");
                    // Translate the number to an index by subtracting one
                    itemNumber = itemNumber -1;
                    System.out.println("item number is " +itemNumber);
                    //remove the item from the list
                    list.remove(itemNumber);
                    break;
                case "C":
                    // clear all elements from the list
                    clear = SafeInput.getYNConfirm(console, "Are you sure you want to clear all items?");
                    while (!clear);
                    list.clear();

                case "S":
                    // Determine if the file is dirty
                    // If yes, we want to overwrite the old file with the new file
                    save = SafeInput.getYNConfirm(console, "Do you want to save your file?");
                    while (!save);
                    //save the file




                case "V":
                    displayList();
                    break;
                case "Q":
                    // Is the file dirty?
                    // If the file is dirty, prompt if they would like to save before exiting
                    needsToBeSaved = SafeInput.getYNConfirm(console, "Do you want to save before quitting?");
                    while (!needsToBeSaved);
                    // save the file

                    quit = SafeInput.getYNConfirm(console, "Are you sure you want to quit?");
                    while (!quit);
                    System.exit(0);
                    break;
            }
            System.out.println("The command you entered is: "+cmd);


        }
        while(!done);



    }

    private static void displayList()
    {
        System.out.println("\n+++++++++++++++++++++++++++++++++++++++++++++++++");
        if(list.size() != 0 )
        {
            for(int i = 0; i < list.size(); i++)
            {
                System.out.printf("\n%3d%35s", i+1, list.get(i) );
            }

        }
        else
            System.out.println("+++               List is Empty               +++");
        System.out.println("\n+++++++++++++++++++++++++++++++++++++++++++++++++");
    }


}

