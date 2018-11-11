import java.io.*;
import java.util.*;

public class FinalProject {
  public static void main(String[] args)throws FileNotFoundException{
  	File f = new File("dictionary.txt");
  	Scanner input = new Scanner(f);
  	String word = input.nextLine();

    System.out.println("Welcome to our Word Guessing Game!");
    //input Grace's method
      //need to put letters of the word into wordArray array

    //input Sindy's method
    //userGuesses(<letterArray>, <blankArray>);

    //input Cindy's method
  }
  //Grace's Code

  //Sindy's Code
  public static void userGuesses(char [] letterArray, char [] blankArray){ //verify "char [] letterArray"
    Scanner input = new Scanner (System.in);
    int leftover = blankArray.length; //Program uses this to penalize the user for incorrect guesses
    int blank; // Program uses this to see if more guesses can be made
    int lives = 5;
    //Prompt user to enter a letter
    do {
      blank= 0;
      System.out.print("Guess a letter: ");
      char letter = input.next().charAt(0);

      //Program checks if the word has that letter
      for (int index = 0; index < blankArray.length; index++){
        //If Yes,
        if (letterArray[index]==letter){
          //Prints that letter onto the place where the underscore has the location
          blankArray[index] = letter;
          leftover=blankArray.length-1;
        } else {
          blank++;
        }
      }

      //If No,
      if (blank==leftover){
        //Takes point away
        lives--;
        if (lives!=0){
          System.out.printf("Nope, no %c. Lives: %d.%n", letter, lives);
        } else {
          System.out.printf("Nope, no %c. Lives: %d. Game over.%n", letter, lives);
          System.exit(1);
        }
      } else {
        int trash=0; //I just had to put an else statement
      }

      System.out.println(blankArray);
    //Program checks if there are any more blank spaces. If yes, prompt user for another guess.
    } while (blank!=0);
  }

  //Cindy's Code
}
