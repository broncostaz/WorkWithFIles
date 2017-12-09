/*************************************
* Programmer: Alec Stasinski
* classID: astasi0611
* Lab5: WMU GPA-O-Tron
* CIS 2600: Business Application Programming
* Fall 2017
* Due date: 12/08/17
* Date completed: 12/08/17
**************************************
* Program Explanation
* 
* The code starts off with the utilzation of two paths to two separatae
* text files studentAcademicProbation and StudentsGoodStanding. Multiple
* variable of different primitive types are initialized than to help
* with the writing and reading of text to these two text files. a outputstream
* and bufferedwriter are initialzed to write to a text a file. This is done
* twice to write to both files. The user is than asked to input a firstName
* , a last Name, a win Number, and a gpa. There are try catch and while
* loops used to prevent certain input to make sure the program runs accordngly.
* All four variables have a specific method that gives them value. A 
* if else statement is used to separate the students that are written to the
* two file, since if a student is below a 2.0 he or she records belong
* in the academic probation text file otherwise they belong in the studentGood
* standing text file. The user can iterate as many records as he or she wants
* since the code is looped to allow multiple entries in either text file. these
* entries will continuousluy be updated as well since the bufferwriters have
* append connected to it. If exceptions occur such as ioexceptiona or 
* fileNotFound exceptions they are caught cause the code is in a try block.
* Next bufferedreader and inputstreams are iniialized to allow reading from
* the text files since the readers and inputsreams are connected to the 
* tet files (there are two of each). Each line is read until the reader
* finds there is no string on a line (its null). each line is put into
* a string array and split by the delimeter. each individual part is 
* than assigned to its appropiate primitve value. A display method is used
* to output the code. The readers are than closed. Catchs are used to 
* once again prvent any ioexceptions or other exceptions casued by the 
* inputstreams or bufferedreaders.
* 
**************************************/

/*
Document your code.

Discussing how it accomplishes what it does 
is as important as noting what it does.

Make sure to explain the entire file interaction process in detail.

##Remember to use these paths:##

Paths.get("StudentsGoodStanding.txt");
Paths.get("StudentsAcademicProbation.txt");

There are text files in your NetBean folder.

*/ 

//=============================Watch the line==================================>

package lab5;

//i,ports the inputMirmatchexception class
import java.util.InputMismatchException;
//imports are the java io files
import java.io.*;
//import are the java nio files 
import java.nio.file.*;
//importts all the java nio file standardOpenOption classes
import static java.nio.file.StandardOpenOption.*;
//imports the scanner class
import java.util.Scanner;

/**
 *
 * @author astasi0611
 * 
 */

public class StudentLists {


    public static void main(String[] args) 
    {
        //a path to the file StudentsGoodStanding.txt is created
        Path fileGoodStanding = Paths.get("StudentsGoodStanding.txt");
        //a path to the file StudentsAcademicProbation.txt is creates
        Path fileBadStanding = Paths.get("StudentsAcademicProbation.txt");
        
        //other variables that need to be initialized
        String delimiter = ",";
        String record = "";
        String repeat = "";
        String firstName = "";
        String lastName = "";
        String conversion = "";
        String[] studentRecords = new String[3]; //string array contains 4 
        //elements
        int winNumber = 0;
        double GPA = 0.0;
        final double ACADEMIC_PROBATION_TARGET = 2.0; //a constant
        final double HIGHEST_POSSIBLE_GPA = 4.0; //a constant
        final double LOWEST_POSSIBLE_GPA = 0.0; //a constant
        boolean truth = true; //boolean value set to true        
        
        //initializes a scanner
        Scanner scan = new Scanner(System.in);
        
        //a try is used for the block of code to stop any exceptions from
        //occuring rather its an IOException, fileNotFoundException or something
        //else. These exceptions that are caught in the try by the catch blocks
        //that follow.
        try
        {//start of try
            //an output stream output is created that writes to the output
            //newly created output stream, which is the path file. The file
            //is StudentsGoodStanding.txt. The create.append is used to so that 
            //if no txt file is present its created and that the text file
            //is appended if a text file is there.
            OutputStream outputToGood = new BufferedOutputStream
            (Files.newOutputStream(fileGoodStanding, CREATE.APPEND));
            
            //a buffered writer is created so that input can be written to 
            //to the outPutStreamWriter output. This than cause the
            //user imput to be outputed in the txt file
            BufferedWriter writerToGood = new BufferedWriter
            (new OutputStreamWriter(outputToGood));
            
            //an output stream output is created that writes to the output
            //newly created output stream, which is the path file. The file
            //is StudentsAcademicProbation.txt. The create.append is used to so  
            //that if no txt file is present its created and that the text file
            //is appended if a text file is there.
            OutputStream outputToBad = new BufferedOutputStream
            (Files.newOutputStream(fileBadStanding, CREATE.APPEND));
            
            //a buffered writer is created so that input can be written to 
            //to the outPutStreamWriter output. This than cause the
            //user imput to be outputed in the txt file
            BufferedWriter writerToBad = new BufferedWriter
            (new OutputStreamWriter(outputToBad));
            
            //a do while loop is used to repeat all the whole block of 
            //if the user chooses do repeat by answering yes. If the user
            //imputs no the user will exit the loop and will not be able to
            //write anymore to the file
            do{//start of do
            //the getFirstName method is called to give value to firstName
            //two parameters are used the scanner and stirng firstName
            firstName = getFirstName(scan, firstName);
            //the getLastName method is called to give value to lastName
            //three parameters are used the scanner, string firstName and 
            //string lastname
            lastName = getLastName(scan, firstName, lastName);
            //the winNumber method is called to give value to winNumber.
            //The parameters are scanner scan, int winNumber, 
            //string firstName, string lastName, string conversion, and
            //boolean truth
            winNumber = getWinNumber(scan, winNumber, firstName, lastName, 
                    conversion, truth);
            //the getGPA method is called to give value to GPA. The paramater
            //are a scanner scan, a double gpa, int winnumber, boolean truth,
            //and two int constants 
            GPA = getGPA(scan, GPA, winNumber, truth, HIGHEST_POSSIBLE_GPA,
                    LOWEST_POSSIBLE_GPA);
            //the string record takes all the user input and puts it in 
            //a string where each variable is separated by a delimiter
            //string which is a comma
            record = winNumber + delimiter + firstName + delimiter + lastName + 
                    delimiter + GPA;
            //the if statement runs if the user inputted gpa for the student
            //is belown the constant of 2.0 to indicate the student is 
            //academic probation and to make sure the student records are
            //written to the studentacademicprobation.txt file
            if(GPA < ACADEMIC_PROBATION_TARGET)
            {//start of if
                //the record that was just created is written to the 
                //studentAcademicProbation text file starting from the first
                //character of the string to the last
                writerToBad.write(record, 0, record.length());
                //the writer goes to the next line in the text file
                writerToBad.newLine();
            }//end of if
            //if the user inputted gps exceeds or equals to 2.00 than the
            //the else is ran. The records that are written here go to
            //the studentGoodStanding file because there gpas range
            //from 2.0 to 4.0
            else
            {//start of else
                 //the record that was just created is written to the 
                //studentGoodStanding text file starting from the first
                //character of the string to the last
                writerToGood.write(record, 0, record.length());
                //the writer goes to the next line in the text file
                writerToGood.newLine();
            }//end of else
            
            //relads the buffer
            scan.nextLine();
            
            //the user is asked to go again which refers to the do while
            //loop initiated earlier. if the user enter yes than the loop
            //will reiterate but if he doesnt enter no than the user will
            //be stuck in a while loop until he or she inputs yes or no
            //to the question.
            System.out.print("Do you want to go again? Yes or No >>> ");
            repeat = scan.nextLine();
            while(!repeat.equalsIgnoreCase("yes") && 
                    !repeat.equalsIgnoreCase("no"))
                {//start of while
                    System.out.print("It must be yes or no! Do you want to go "
                            + "again? Yes or No >>> ");
                    repeat = scan.nextLine();
                }//end of while
            }while(repeat.equalsIgnoreCase("yes"));//end of do while
            
            writerToGood.close();//closes the writerToGood
            writerToBad.close();//close the writerToBad
        }//end of try
        //the catch is iterated if there is no file 
        catch(NoSuchFileException e)
        {//start of catch
            System.out.println("No such file or directory");
        }//end of catch
        //the catch is iterated if an ioexception occurs from the buffered
        //writer and outputstream
        catch(IOException e)
        {//start of catch
            System.out.println("IO exception");
        }//end of catch
        //a try is used to make sure that no ioexceptions or other
        //exceptions occur due to the utilization of inputstreams and
        //buffered readers
        try
        {//start of try
            //an input stream is created that is connected to the path file
            //fileGoodStanding which is the txt file StudeentGoodStanding
            InputStream inputForGood = new BufferedInputStream
            (Files.newInputStream(fileGoodStanding));
            //a buffered reader is initalized in cohesion with the inputstream
            //to read from the txt file studentGoodStanding
            BufferedReader readerForGood = new BufferedReader(new
            InputStreamReader(inputForGood));
            //an input stream is created that is connected to the path file
            //fileGoodStanding which is the txt file StudeentAcademicProbation
            InputStream inputForBad = new BufferedInputStream
            (Files.newInputStream(fileBadStanding));
            //a buffered reader is initalized in cohesion with the inputstream
            //to read from the txt file studentAcademicprobatiom
            BufferedReader readerForBad = new BufferedReader(new
            InputStreamReader(inputForBad));
            
            //statements are printed out from println to give a header
            //and subtitle
            System.out.println("============================================="
                + "=====");
            System.out.println("******Western Michigan University******");
            
            System.out.println("============================================="
                + "=====");
            System.out.println("******Probatinary Standing Students******");
            System.out.println("============================================="
                + "=====");
            //some space
            //grab first line
            record = readerForBad.readLine();
            //the while loops keeps reading from the file until the
            //reader doesnt read a line that has a string, meaning
            //its null. The loop ends when the reader reaches null
            while(record != null)
                {//start of while
                    //the string record is spit into 4 elements by the delimiter
                    //that go into the array
                    studentRecords = record.split(delimiter);
                    //winNumber is created through parseInt Integer wraper class
                    //method to turn the string back to a int
                    winNumber = Integer.parseInt(studentRecords[0]);
                    //firstName is the second element
                    firstName = studentRecords[1];
                    //lastName is the thirs element
                    lastName = studentRecords[2];
                    //GPA is created through parseDouble Double wrapper class
                    //method to turn the string back to double
                    GPA = Double.parseDouble(studentRecords[3]);
                    //a display method is called to display the four variables
                    display(winNumber, firstName, lastName, GPA);
                    //the next line is attempted to be read
                    record = readerForBad.readLine();
                }//end of while
            readerForBad.close(); //the the readForBad is closed
            //another subtitle is printed
            System.out.println("============================================="
                + "=====");
            System.out.println("******Good Standing Students******");
            System.out.println("============================================="
                + "=====");
            //reads the first line from the txt file
            record = readerForGood.readLine();
            //the while loops keeps reading from the file until the
            //reader doesnt read a line that has a string, meaning
            //its null. The loop ends when the reader reaches null
            while(record != null)
                {//start of while
                      //the string record is spit into 4 elements by the delimiter
                    //that go into the array
                    studentRecords = record.split(delimiter);
                    //winNumber is created through parseInt Integer wraper class
                    //method to turn the string back to a int
                    winNumber = Integer.parseInt(studentRecords[0]);
                    //firstName is the second element
                    firstName = studentRecords[1];
                    //lastName is the thirs element
                    lastName = studentRecords[2];
                    //GPA is created through parseDouble Double wrapper class
                    //method to turn the string back to double
                    GPA = Double.parseDouble(studentRecords[3]);
                    //a display method is called to display the four variables
                    display(winNumber, firstName, lastName, GPA);
                    //the next line is attempted to be read
                    record = readerForGood.readLine();
                }//end of while
            readerForGood.close(); //reader for good id closed
        }//end of try
        //if ioeception are created from the bufferedreader or inputstream
        //the ioexception is caught an the user is presneetd a message
        //about the exception
        catch(IOException e)
        {//start of catch
            System.out.println("IO exception");
        }//end of catch
        //any other exceptions that can occur are caught
        catch(Exception e)
        {//start of catch
            System.out.println("Exception was found " + e.getMessage());
        }//end of catch
    }//end of method
    
    //this method returns a double gpa. the method is in a do while loop so
    //it automatically iterartes atleast once. The user is asked to enter
    //a gpa the number entered isnt below 4.0 or above 0.0 than the user is
    //stuck in a while loop until he or she inputs a value in that range.
    //also if the user inputs a value thats a string than the user is 
    //told that a inputMisMatch exception occurs since it is caught and
    //the code is in atry block. The user is asked again for a gpa
    //because the loop goes by the boolean truth value when its not true
    //which only occurs if an exception is caught.
    public static double getGPA(Scanner scan, double GPA, int winNumber, 
            boolean truth, double HIGHEST_POSSIBLE_GPA, 
            double LOWEST_POSSIBLE_GPA)
    {
        do
        {//start of do while
            try
            {//start of try
                System.out.print("What is student #" + winNumber + " GPA >>> ");
                GPA = scan.nextDouble();
                while(GPA < LOWEST_POSSIBLE_GPA || GPA > HIGHEST_POSSIBLE_GPA)
                {//start of while
                    System.out.print("GPA can only be from 0.0 to 4.0!\n" +
                            "What is student #" + winNumber + " GPA >>> ");
                    GPA = scan.nextDouble();
                }//end of while
                truth = true;
            }//end of try
            catch(InputMismatchException e)
            {//start of catch
                System.out.println("The value entered for GPA was not a"
                    + " double. The value must be a double.");
                scan.nextLine();
                truth = false;
            }//end of catch
            catch(Exception e)
            {//start of catch
                System.out.println(e.getMessage());
                scan.nextLine();
                truth = false;
            }//end of catch
        }while(truth != true);//end of do while   
        
        return GPA;
    }
    //this method returns a double gpa. the method is in a do while loop so
    //it automatically iterartes atleast once. The user is asked to enter
    //a win number. If the win number isnt 9 digits in length than the user is
    //stuck in a while loop until he or she inputs a int with 9 digits.
    //also if the user inputs a value thats a string than the user is 
    //told that a inputMisMatch exception occurs since it is caught and
    //the code is in atry block. The user is asked again for a win number
    //because the loop goes by the boolean truth value when its not true
    //which only occurs if an exception is caught.
    public static int getWinNumber(Scanner scan, int winNumber, String fName,
        String lName, String conversion, boolean truth)
    {//start of method
        do
        {//start of while
            try
            {//start of try
                System.out.print("What is " + fName + " " + lName + "'s "
                        + "win number >>> ");
                winNumber = scan.nextInt();
                conversion = Integer.toString(winNumber);
                while(conversion.length() != 9)
                {
                    System.out.print("The winNumber you entered was not "
                            + "9 digits.\nWhat is " + fName + " " + lName
                            + "'s win number >>> ");
                    winNumber = scan.nextInt();
                    conversion = Integer.toString(winNumber);
                }
                truth = true;
            }//end of try
            catch(InputMismatchException e)
            {//start of catch
                System.out.println("The value entered for win number was not a"
                    + " int. The value must be a int.");
                scan.nextLine();
                truth = false;
            }//end of catch
            catch(Exception e)
            {
                System.out.println(e.getMessage());
                scan.nextLine();
                truth = false;
            }
         }while(truth != true);//end of while   
      
        return winNumber;
    }//end of method
    
    //the methos returns a string. The user is asked to enter a string but
    //if the user enters anything that doesnt range from a-z regardless
    //if its uppercase or lowercase the user is stuck in while loop
    //until he or she enters a a name whithout digits or special characters.
    public static String getFirstName(Scanner scan, String fName)
    {//start of method
        System.out.print("What is the students first name >>> ");
        fName = scan.nextLine();
        while(!fName.matches("[a-zA-Z]+"))
        {
            System.out.print("The first name entered is not in letters "
                    + "only! \nWhat is the students first name >>>  ");
            fName = scan.nextLine();
        }
        return fName;
    }//end of method
    //the methos returns a string. The user is asked to enter a string but
    //if the user enters anything that doesnt range from a-z regardless
    //if its uppercase or lowercase the user is stuck in while loop
    //until he or she enters a a name whithout digits or special characters
    public static String getLastName(Scanner scan, String fName, String lName)
    {//start of method
        System.out.print("What is " + fName +"'s last name? ");
        lName = scan.nextLine();
        while(!lName.matches("[a-zA-Z]+"))
        {//start of while
            System.out.print("The last name entered is not in letters "
                    + "only! \nWhat is " + fName + "'s last name >>> ");
            lName = scan.nextLine();
        }//end of while
        return lName;
    }//end of method
    
    //this method doesnt return anything and is only used to display
    //the information for each record accordingly
    public static void display(int winNumber, String firstName, String lastName,
            double GPA)
    {//start of method
        System.out.println("Win Number: " + winNumber);
        System.out.println("First Name: " + firstName);
        System.out.println("Last Name " + lastName);
        System.out.printf("GPA: %.02f%n", GPA);
        System.out.println("-------------------");
    }//end of method
    
}//end of class
