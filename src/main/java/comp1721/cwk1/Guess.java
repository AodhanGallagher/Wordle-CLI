package comp1721.cwk1;

import java.util.Scanner; // importing the scanner to read in user input.

public class Guess {
  private int guessNumber;
  private String chosenWord;

  // Used to get player input in readFromPlayer().
  private static final Scanner INPUT = new Scanner(System.in);

  /* Constructor that checks if the "num" parameter is valid and then initializes guessNumber to that value.
     Throws an exception if "num" is invalid. */
  public Guess(int num) {
    if ((num < 1) || (num > 6)) {
      throw new GameException("The guessNumber parameter is invalid and must be between 1-6");
    }
    
    guessNumber = num;
  }

  /* Constructor that checks if "num" and "word" are valid and then initializes guessNumber and chosenWord to those values, respectively.
     An exeption is thrown if either of the parameters are invalid. */
  public Guess(int num, String word) {
    if ((num < 1) || (num > 6)) {
      throw new GameException("The guessNumber parameter is invalid and must be between 1-6");
    }
    
    guessNumber = num;

    if (word.length() != 5) {
      throw new GameException("chosenWord needs to be 5 characters long");
    }

    /* The for loop iterates through each character in the string and checks to see if that character is a letter or not.
       An exception is thrown if the string contains a character that is not a letter. */
    for (int i = 0; i < word.length(); i++) {
      char c = word.charAt(i);
      if (!(c >= 'A' && c <= 'Z') && !(c >= 'a' && c <= 'z')) {
        throw new GameException("chosenWord can only contain letters");
      }
    }

    chosenWord = word.toUpperCase();
  }

  // Method to return the value of guessNumber.
  public int getGuessNumber() {
    return guessNumber;
  }

  // Method to return the value of chosenWord.
  public String getChosenWord() {
    return chosenWord;
  }

  /* A method that asks for user input for chosenWord if it has not been previously initialized and checks to see
     if the entered string is valid. */
  public void readFromPlayer() {
    String playerWord = INPUT.next();

    if (playerWord.length() != 5) {
      throw new GameException("chosenWord needs to be 5 characters long");
    }

    /* The for loop iterates through each character in the string and checks to see if that charcater is a letter or not.
       An exception is thrown if the string contains a character that is not a letter. */
    for (int i = 0; i < playerWord.length(); i++) {
      char c = playerWord.charAt(i);
      if (!(c >= 'A' && c <= 'Z') && !(c >= 'a' && c <= 'z')) {
        throw new GameException("chosenWord can only contain letters");
      }
    }

      chosenWord = playerWord.toUpperCase();
  }

  // Method that compares the chosenWord string to the "target" string and uses ANSI escape codes to colour code each letter appropriately
  public String compareWith(String target) {
    StringBuilder returnedWord = new StringBuilder();

    // This loop is used to iterate through each charcter in the 5 letter strings
    for (int i = 0; i < target.length(); i++) {

      // White if the character is not in the word
      if (target.contains(chosenWord.valueOf(chosenWord.charAt(i))) == false) {
        returnedWord.append("\033[30;107m " + chosenWord.valueOf(chosenWord.charAt(i)) + " \033[0m");
        continue;
      }

      // Green if the character is in the word and in the correct location
      if (target.charAt(i) == chosenWord.charAt(i)) {
        returnedWord.append("\033[30;102m " + chosenWord.valueOf(chosenWord.charAt(i)) + " \033[0m");
        continue;
      }
      
      // Yellow if the character is in the word but not in the correct location
      if (target.contains(chosenWord.valueOf(chosenWord.charAt(i))) == true) {
        returnedWord.append("\033[30;103m " + chosenWord.valueOf(chosenWord.charAt(i)) + " \033[0m");
        continue;
      }
    }
    
    return returnedWord.toString();
  }

  // A method that compares the chosenWord string to the "target" string and returns true if they match and false if they differ.
  public boolean matches(String target) {
    if (chosenWord.equals(target)) {
      return true;
    }
    else {
      return false;
    }
  }
}