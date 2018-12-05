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
      char[] letters = createLetters(words, index);
      char[] blanks = createBlanks(words, index);
      printBlanks(blanks);
      System.out.println();

      userGuesses(letters, blanks, words[index]);

      System.out.println("You win! Do you want to play again? Please enter \"yes\" or \"no\":");
      if (userInput.nextLine().equals("no")){
        System.out.println("Goodbye");
        playGame = false;
      } else {
        index++;
      }
    }
  }

  /**
   * reads words from the file and puts them into an array
   * @param file: the file containing the list of mystery words
   * @return array words[] which contains all the mystery words
   */
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
    return words;
  }

  /**
   * shuffles the values within the array of mystery words
   * @param words: array of mystery words
   */
  public static void shuffle(String[] words){
    Random r = new Random();
    for(int i = 0; i < words.length; i++){
      int j = r.nextInt(words.length);
      String temp = words[i];
      words[i] = words[j];
      words[j] = temp;
    }
  }

  /**
   * puts the letters (of the word at array[index]) into an array of chars
   * @param words: array of mystery words
   * @param index: index of array
   * @return array letters[], containing the mystery word's letters
   */
  public static char[] createLetters(String[] words, int index){
    int l = words[index].length();
    char[] letters = new char[l];
    for(int i = 0; i < l; i++){
      letters[i] = words[index].charAt(i);
    }
    return letters;
  }

  /**
   * creates an array of underscores, with the number of underscores equal to the number of letters in the mystery word
   * @param words: array of mystery words
   * @param index: index of array
   * @return array blanks[], containing the underscores
   */
  public static char[] createBlanks(String[] words, int index){
    int l = words[index].length();
    char[] blanks = new char[l];
    for(int i = 0; i < l; i++){
      blanks[i] = '_';
    }
    return blanks;
  }

  /**
   * prints the mystery word's letters into the array of underscores
   * @param blanks: array of underscores
   */
  public static void printBlanks(char[] blanks){
    int l = blanks.length;
    for(int i = 0; i < l; i++){
      System.out.printf("%c ", blanks[i]);
    }
    System.out.println();
  }

  /**
   * prompts and checks if the user's guess is in the mystery word
   * @param letterArray: array of mystery word's letters
   * @param blankArray: array of underscores
   * @param word: mystery word being guessed
   */
  public static void userGuesses(char[] letterArray, char[] blankArray, String word){
    Scanner input = new Scanner (System.in);
    int preCount = blankArray.length; //Program uses this to penalize the user for incorrect guesses
    int postCount= preCount;
    int blank; //Program uses this to see if more guesses can be made
    int lives = 10;

    do {
      blank= 0;
      preCount= postCount;
      System.out.print("Guess a letter: ");
      char letter = input.next().charAt(0);

      for (int index = 0; index < blankArray.length; index++){ //Program checks if the word has that letter
        if (letterArray[index]==letter){  //If Yes,
          blankArray[index] = letter;     //print that letter in place of the underscore
          postCount--;
        } else if (blankArray[index]=='_'){
          blank++;
        }
      }
      lives= wrongGuess(word, letter, lives, preCount, postCount); //If No, take away a life
      printBlanks(blankArray);
      System.out.println();
    } while (blank!=0); //If there are more blank spaces, prompt user for another guess.
  }

  /**
   * returns the numbers of lives the user has after making an incorrect guess
   * @param word: mystery word being guessed
   * @param letter: the letter the user enters as their guess
   * @param lives: the number of lives the user has
   * @param preCount: number of blank spaces leftover before the user enters their guess
   * @param postCount: number of blank spaces leftover after the user enters their guess
   * @return the number of lives leftover
   */
  public static int wrongGuess(String word, char letter, int lives, int preCount, int postCount){
    if (postCount==preCount){
      lives--;
      if (lives!=0){
        System.out.printf("Nope, no \"%c\".%n", letter);
      } else {
        System.out.printf("Nope, no \"%c\". %n", letter);
        System.out.printf("The word was \"%s\".%n", word);
        System.out.printf("Game over.%n");
        System.exit(1);
      }
    }
    System.out.printf("Lives: %d %n",lives);
    return lives;
  }
}
