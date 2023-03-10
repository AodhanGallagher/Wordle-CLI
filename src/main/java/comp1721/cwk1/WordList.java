package comp1721.cwk1;

// Importing relevant packages to be used in the WordList class.
import java.io.IOException;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.nio.file.Files;
import java.nio.file.Paths;

public class WordList {
  private List<String> words = new ArrayList<>(); // ArrayList used to store the words to be used.

  // Constructor that reads in a file containing the words to be used and appends those words to the "words" ArrayList.
  public WordList(String filename) throws IOException {
    try (Scanner input = new Scanner(Paths.get(filename))) {

      while (input.hasNextLine()) {
        words.add(input.nextLine());
      }
    }
  }

  // The size() method returns the number of words contained in the "words" ArrayList.
  public int size() {
    return words.size();
  }

  /* The getWord() method checks to see if the gameNumber parameter is valid and then returns the corresponding word in the list.
     If gameNumber is invalid, an exception is thrown. */
  public String getWord(int n) {
    if ((n < 0) || (n >= words.size())) {
      throw new GameException("The value of gameNumber is invalid");
    }
    else {
      return words.get(n);
    }
  }
}
