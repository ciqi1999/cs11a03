import java.util.*;

public class FinalProject {
  public static void main(String[] args){
  	File f = new File("dictionary.txt");
  	Scanner input = new Scanner(f);
  	String word = input.nextLine();
  	System.out.println(word);
    System.out.println("Welcome to our Word Guessing Game!");
    
  }
}
