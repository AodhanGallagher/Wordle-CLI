package comp1721.cwk1;

// Importing relevant packages
import java.io.IOException;

import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.time.LocalDate;

import java.util.List;
import java.util.ArrayList;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.PrintWriter;

public class Game {
  private int gameNumber;
  private String target;
  private List<Guess> guesses = new ArrayList<>();

  /* Constructor that creates a game of wordle by creating a WordList object, calculating the current day's game of wordle
     and using that number in the getWord() method to get the day's word. */
  public Game(String filename) throws IOException {
    WordList wordList = new WordList(filename);

    LocalDate firstWordleDate = LocalDate.of(2021, 06, 19);
    LocalDate dateToday = LocalDate.now();
    gameNumber = (int)ChronoUnit.DAYS.between(firstWordleDate, dateToday); // Calculates days between excluding the two inputted dates.
    gameNumber = gameNumber + 2; // Adds 2 days to the number of days previously calculated to get the correct value.

    target = wordList.getWord(gameNumber);
  }

  /* Constructor that creates a game of wordle by creating a WordList object and sets the values of target and gameNumber
     to the parameter values. */
  public Game(int num, String filename) throws IOException {
    WordList wordList = new WordList(filename);

    target = wordList.getWord(num);

    gameNumber = num;
  }

  // The play() method plays a single game of wordle (with up to 6 guesses).
  public void play() {
    int i = 0;

    System.out.printf("WORDLE %d\n", gameNumber);
    
    for (i = 0; i <= 6; i++) { // Loop through each guess.
      // If the player runs out of guesses, break the loop.
      if (i == 6) {
        break;
      }

      // Create a new guess object and read in the player's guess.
      guesses.add(new Guess(i+1));
      System.out.printf("\nEnter guess (" + guesses.get(i).getGuessNumber() + "/6): ");
      guesses.get(i).readFromPlayer();

      System.out.printf(guesses.get(i).compareWith(target)); // Prints the players guess compared to the target word.

      if (guesses.get(i).matches(target) == true) {
        break;
      }
    }

    /* A series of messages that get printed at the end of the game, depending on how many guesses the player made. */
    if ((i+1) == 1) {
      System.out.printf("\nSuperb - Got it in one!\n");
    }

    if (((i+1) >= 2) && ((i+1) <= 5)) {
      System.out.printf("\nWell done!\n");
    }

    if ((i+1) == 6) {
      System.out.printf("\nThat was a close call!\n");
    }

    if ((i+1) == 7) {
      System.out.printf("\nNope - Better luck next time!\n");
      System.out.printf(target + "\n");
    }
  }

  // The save() method saves a game summary to a text file with the given filename.
  public void save(String filename) throws IOException {
    Path path = Paths.get(filename);

    /* This loops through each of the elements in the guesses ArrayList, calling the compareWith() method for each element
       and it prints the results to the text file. */
    try (PrintWriter out = new PrintWriter(Files.newBufferedWriter(path))) {
      for (int i = 0; i < guesses.size(); i++) {
        out.println(guesses.get(i).compareWith(target));
      }
    }
  }
}