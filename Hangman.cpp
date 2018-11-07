#include <iostream>
#include <fstream>
#include <string>
#include "print.h"
#include <stdio.h>
#include <ctype.h>
#include <cstdlib>
using namespace std;

// Function prototypes
char print_question();
char play_again();

int main ()
{ 
// open dictionary.txt 
  ifstream infile;
  infile.open("dictionary.txt");
  if (!infile.is_open())
  {
  	cout << "Error, file not open.\n";
  	return 0;
  } 
 
// count the number of words in the file
  int count = 0;
  while (!infile.eof()) 
  {
     string word;
     infile >> word;
     if (word != "-1")
     {
     	count++;
     }
  }
  infile.close();
// populate the dictionary array with
// words in dictionary.txt
  infile.open("dictionary.txt");
  if (!infile.is_open())
  {
  	cout << "Error, file not open.\n";
  	return 0;
  }
  string *dictionary;
  dictionary = new string[count];
  for (int i = 0; i < count; i++)
  {
  	string word;
  	infile >> word;
  	if (word != "-1")
  	{
  	  dictionary[i] = word;
  	}
  }
  infile.close();

// shuffle the elements in the dictionary array
  for (int i = 0; i < count; i++)
  {
   	int r = rand() % count;
    string temp;
   	temp = dictionary[i];
  	dictionary[i] = dictionary[r];
  	dictionary[r] = temp;
  }

// pick the words in order and start the game 
  int correct_word = 0;
  for (int i = 0; i < (count); i++)
  {
      int hang = 0;
      // print out the "hangman", noose first
      print_body_parts(hang);
  
      // store the picked word in a char array
      // and print "blanks" to indicate the letters number
      int length;
      length = dictionary[i].length();
      char letter[length];
      char letter_copy[length];
      for (int j = 0; j < length; j++)
      {
        letter[j] = dictionary[i][j];
        letter_copy[j] = '_';
        cout << letter_copy[j] << " ";
      }
      cout << endl;
     
      bool exit_y = false;
      while (!exit_y)
      {
        // prompt the user to guess a letter
        // and validate the input
        char guess = print_question();
  
        // change user's input to uppercase letter
        guess = (toupper(guess));
        
        // check if the guess is correct or not
        int correct_letter = 0;
        for (int j = 0; j < length; j++)
        {
          if (letter[j] == guess)
          {
            letter_copy[j] = guess;
            correct_letter++;
          }
        } 
        // print out the result  
        if (correct_letter == 0)
        {
          hang++;
          print_body_parts(hang);
          cout << endl;
          cout << guess << " is not in the word\n";        
        }
        cout << "You have guessed " << hang
             << " letters incorrectly\n";
        for (int j = 0; j < length; j++)
        {
          cout << letter_copy[j] << " ";
        }
        cout << endl << endl;

        // check how many blanks are left
        // to decide if the user has won
        int blank = 0;
        for (int j = 0; j < length; j++)
        {
          if (letter_copy[j] == '_')
           blank++;
        }  

        if (blank == 0)
        {
          cout << endl;
          cout << "Yes, you win!\n";
          correct_word++;
          cout << "You have guessed " << correct_word
               << " words correctly.\n" << endl;
          char answer = play_again();
          if (answer == 'Y')
            exit_y = true;
          if (answer == 'N')
          {
            exit_y = true;
            i = count;
          }
        }  

        if (hang >= 8)
        {
          cout << endl;
          cout << "You lose.\n";
          cout << "The word was " << dictionary[i]
               << endl;
          cout << "You have guessed " << correct_word
               << " words correctly.\n" << endl;
          char answer = play_again();
          if (answer == 'Y')
            exit_y = true;
          if (answer == 'N')
          { 
            exit_y = true;
            i = count;
          }
        }
  }
    
  }
  // deallocate the array
  delete []dictionary;

  cout << endl;
  cout << "Thanks for playing!\n"
       << endl;

	return 0;
}

// Function: print the guess question
// Input: nothing
// Return: the letter user guess
// Does: prompt the user to guess a letter
//       and return the letter
char print_question()
{
  char guess;
  do
  {
    cout << "Enter a letter to guess:\n";
    cin >> guess;
  }
  while (!isalpha(guess));

  return guess;
}

// Function: print the play-again question
// Input: nothing
// Return: Y or N
// Does: ask the user if they want to play 
//       again and return their answer
char play_again()
{
  char answer;
  do 
  {
    cout << "Play again?\n";
    cin >> answer;
  }
  while ((answer != 'Y') && (answer != 'N'));

  return answer;
}