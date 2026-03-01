import java.util.Scanner;
import java.util.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

class Submit {

  public static void confirmSubmission(String studID, String option, String module, String ECtext) {

    Scanner sc = new Scanner(System.in);
    int maxAttempts = 5;

    while (maxAttempts != 0) {

      System.out.println("Are you sure you are ready to submit?");
      System.out.println("Type just yes or no?");
      String confirm = sc.nextLine();

      if (confirm.equalsIgnoreCase("Yes")) {

        ECApplication.Application.add(Arrays.asList(studID, option, module, ECtext));
        System.out.println("Your EC application has been submitted successfully!");
        break;

      } else if (confirm.equalsIgnoreCase("No")) {

        System.out.println("Submission cancelled. You may rewrite your EC.");
        return;

      } else {

        maxAttempts -= 1;
        if (maxAttempts == 0) {
          System.out.println("Sorry, too many attempts. Please try again later!");
          break;
        }

        System.out.println("Sorry, invalid input. Try again!");
        System.out.println("You have " + maxAttempts + " attempts left");

      }
    }
  }

  public static void displayApplication(String studID, String option, String module, String ECtext) {

    if (ECApplication.Application.isEmpty()) {
      System.out.println("No application to display (submission was not confirmed).");
      return;
    }

    List<String> lastEntry = new ArrayList<>(ECApplication.Application).get(ECApplication.Application.size() - 1);

    System.out.println("------YOUR EC APPLICATION------");
    for (String value : lastEntry) {
      System.out.println(value);
    }
  }
}
