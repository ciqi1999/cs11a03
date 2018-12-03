import java.io.*;
import java.util.*;

public class FinalProject {
  public static void main(String[] args) throws FileNotFoundException{
    Scanner userInput = new Scanner(System.in);
    boolean playGame = true;
    int index = 0;

    String[] words = getDictionary("dictionary.txt");
    shuffle(words);
    System.out.printf("%nWelcome to our Word Guessing Game!%n%n");
    System.out.println("Let's start the game.");
    System.out.println("Please enter your guess in lowercase.");
    while(playGame){
      //input Grace's method
      char[] letters = createLetters(words, index);
      char[] blanks = createBlanks(words, index);
      printBlanks(blanks);
      System.out.println();

      //input Sindy's method
      userGuesses(letters, blanks, words[index]);

      //input Cindy's method
      System.out.println("You win! Do you want to play again? Please enter \"yes\" or \"no\":");
      if (userInput.nextLine().equals("no")){
        System.out.println("Goodbye");
        playGame = false;
      } else {
        index++;
      }
    }
  }
//Grace's Code

// Method purpose: get words in the dictionary and put them into an array
// Input: two Scanner
// Output: array words[] which contains all the words in the dictionary.
  public static String[] getDictionary(String file) throws FileNotFoundException{
    Scanner input1 = new Scanner(new File(file));
    Scanner input2 = new Scanner(new File(file));
    //count the number of words in the dictionary.
    int count = 0;
    while (input1.hasNext()){
      String word = input1.next();
      count++;
    }
    //put these words into an array called words[]
    String[] words = new String[count];
    for(int i = 0; i < count; i++){
      words[i] = input2.next();
    }
    //return the array.
    return words;
  }

  // Method purpose: shuffle the array words[]
  // Input: array words[], int count, and random number generator r.
  // Output: nothing
  public static void shuffle(String[] words){
    Random r = new Random();
    for(int i = 0; i < words.length; i++){
      int j = r.nextInt(words.length);
      String temp = words[i];
      words[i] = words[j];
      words[j] = temp;
    }
  }

  // Methods Purpose: read an element from the array and
  //                  put letters of that words into an array of chars;
  // Input: array words[], int index
  // Output: array letters[]
  public static char[] createLetters(String[] words, int index){
    int l = words[index].length();
    char[] letters = new char[l];
    for(int i = 0; i < l; i++){
      letters[i] = words[index].charAt(i);
    }
    return letters;
  }

  // Method Purpose: create an array of underscores with the number of
  //                 underscores equal to the number of letters in the
  //                 word chosen above.
  // Input: array words[], int index
  // Output: array blanks[]
  public static char[] createBlanks(String[] words, int index){
    int l = words[index].length();
    char[] blanks = new char[l];
    for(int i = 0; i < l; i++){
      blanks[i] = '_';
    }
    return blanks;
  }

  // Method Purpose: Print out letters in array blanks[]
  // Input: array blanks[]
  // Output: nothing
  public static void printBlanks(char[] blanks){
    int l = blanks.length;
    for(int i = 0; i < l; i++){
      System.out.printf("%c ", blanks[i]);
    }
    System.out.println();
  }

  //Sindy's Code
  public static void userGuesses(char[] letterArray, char[] blankArray, String word){
    Scanner input = new Scanner (System.in);
    int preCount = blankArray.length; //Program uses this to penalize the user for incorrect guesses
    int postCount= preCount;
    int blank; // Program uses this to see if more guesses can be made
    int lives = 10;

    //Prompt user to enter a letter
    do {
      blank= 0;
      preCount= postCount;
      System.out.print("Guess a letter: ");
      char letter = input.next().charAt(0);

      //Program checks if the word has that letter
      for (int index = 0; index < blankArray.length; index++){
        //If Yes,
        if (letterArray[index]==letter){
          //Prints that letter onto the place where the underscore has the location
          blankArray[index] = letter;
          postCount--;
        } else if (blankArray[index]=='_'){
          blank++;
        }
      }

      //If No,
      if (postCount==preCount){
        //Takes point away
        lives--;
        if (lives!=0){
          System.out.printf("Nope, no \"%c\".%n", letter);
        } else {
          System.out.printf("Nope, no \"%c\". %n", letter);
          System.out.printf("The word was \"%s\".", word);
          System.out.printf("%nGame over.%n");

          System.exit(1);
        }
      }
      System.out.printf("Lives: %d %n",lives);
      printBlanks(blankArray);
      System.out.println();
    //Program checks if there are any more blank spaces. If yes, prompt user for another guess.
    } while (blank!=0);
  }
}
