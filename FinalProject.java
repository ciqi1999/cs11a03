import java.io.*;
import java.util.*;

public class FinalProject {
  public static void main(String[] args)throws FileNotFoundException{
    Scanner input1 = new Scanner(new File("dictionary.txt"));
    Scanner input2 = new Scanner(new File("dictionary.txt"));
    Scanner userInput = new Scanner(System.in);
    Random r = new Random();
    boolean playGame = true;


    while(playGame){
      // Input Grace's methods.
      String[] words = GetDictionary(input1, input2);
      Shuffle(words, r);

      System.out.println("Welcome to our Word Guessing Game!");
      //input Grace's method
        //need to put letters of the word into wordArray array


      //input Sindy's method
      //userGuesses(<letterArray>, <blankArray>);

      //input Cindy's method
      System.out.println("Do you want to play again? Please enter \"yes\" or \"no\"");
      if (userInput.nextLine().equals("no")){
        System.out.println("Goodbye");
        playGame = false;
      }
    }
  }
//Grace's Code

// Method purpose: get words in the dictionary and put them into an array
// Input: two Scanner
// Output: array words[] which contains all the words in the dictionary.
public static String[] GetDictionary(Scanner input1, Scanner input2){
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
    System.out.println(words[i]);
  }
  //return the array.
  return words;
}

// Method purpose: shuffle the array words[]
// Input: array words[], int count, and random number generator r.
// Output: nothing
public static void Shuffle(String[] words, Random r){
   for(int i = 0; i < words.length; i++){
      int j = r.nextInt(words.length);
      String temp = words[i];
      words[i] = words[j];
      words[j] = temp;
   }
}

// Methods Purpose: read an element from the array and
//                 put letters of that words into an array of chars;
// Input: array words[], int index
// Output: array letters[]
public static char[] CreateLetters(String[] words, int index){
  int l = words[index].length();
  char[] letters = new char[l];
  for(int i = 0; i < (l - 1); i++){
    letters[i] = words[index].charAt(i);
  }
  return letters;
}

// Method Purpose: create an array of underscores with the number of
//                 underscores equal to the number of letters in the
//                 word chosen above.
// Input: array words[], int index
// Output: array blanks[]
public static char[] CreateBlanks(String[] words, int index){
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
public static void PrintBlanks(char[] blanks){
  int l = blanks.length;
  for(int i = 0; i < l; i++){
    System.out.printf("%c ", blanks[i]);
  }
  System.out.println();
}

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
}
