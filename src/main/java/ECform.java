import java.util.Scanner;
import java.util.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

class ECform {

  private String ECtext;
  private static final int maxAttempts = 5;

  public ECform(String ECtext) {

    this.ECtext = ECtext;
  }

  public String getECstatement() {

    return ECtext;
  }

  public void setECstatement(String ECtext) {

    this.ECtext = ECtext;
  }

  public static void ECwrite(String module, String option, String studID) {

    Scanner sc = new Scanner(System.in);
    int attemptsLeft = maxAttempts;

    while (attemptsLeft != 0) {

      System.out.println("Write the full EC in great detail for your chosen module " + module + " between 500 words and 1000 words!");
      String ECtext = sc.nextLine();

      String[] rawWords = ECtext.trim().split("\\s+");
      int wordCount = 0;
      for (String w : rawWords) {
        if (!w.isEmpty()) wordCount++;
      }

      if (wordCount >= 500 && wordCount <= 1000) {

        System.out.println("Word count: " + wordCount);
        System.out.println("Valid amount of words!");
        Submit.confirmSubmission(studID, option, module, ECtext);
        Submit.displayApplication(studID, option, module, ECtext);
        break;

      } else {

        attemptsLeft -= 1;
        if (attemptsLeft == 0) {
          System.out.println("Sorry, too many attempts. Please try again later!");
          break;
        }

        System.out.println("Sorry, invalid input. Try again!");
        System.out.println("You have " + attemptsLeft + " attempts left");

      }
    }

  }
}
