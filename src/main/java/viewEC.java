import java.util.Scanner;
import java.util.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

class ViewEC {

  private static final int maxAttempts = 5;

  public static void checkECOutcome() {

    Scanner sc = new Scanner(System.in);
    int Attempts = 0;

    while (Attempts != maxAttempts) {

      System.out.println("Type your 8 digit ID!");
      String ID = sc.nextLine();

      boolean hasApplication = false;
      for (List<String> entry : ECApplication.Application) {
        if (ID.equals(entry.get(0))) {
          hasApplication = true;
          break;
        }
      }

      if (!hasApplication) {

        Attempts += 1;
        if (Attempts == maxAttempts) {
          System.out.println("Sorry, too many attempts. Please try again later!");
          break;
        }

        System.out.println("Sorry, you didn't create an EC application so you can't view the outcome!");
        System.out.println("You have " + (maxAttempts - Attempts) + " attempts left");

      } else {

        boolean hasOutcome = false;
        for (List<String> outcome : ECApplicationOutcomes.Outcomes) {
          if (ID.equals(outcome.get(0))) {
            hasOutcome = true;
            for (String value : outcome) {
              System.out.println(value);
            }
            break;
          }
        }

        if (!hasOutcome) {
          System.out.println("Your response is under consideration right now!");
        }
        break;
      }
    }
  }
}
