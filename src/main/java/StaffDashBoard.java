import java.util.Scanner;
import java.util.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

class StaffDashBoard {

  public static void staffdashboard() {

    System.out.println("Number of submitted EC applications currently: " + ECApplication.Application.size());

    if (ECApplication.Application.size() == 0) {

      System.out.println("There's nothing to review and judge right now!");
      return;
    }

    for (List<String> entry : ECApplication.Application) {
        for (String value : entry) {
            System.out.println(value);
        }
    }

    Scanner sc = new Scanner(System.in);
    int maxAttempts = 5;

    while (maxAttempts != 0) {

      System.out.println("Choose which student ID's EC you want to assess in the list?");
      String ID = sc.nextLine();
      boolean found = false;

      for (List<String> entry : ECApplication.Application) {
        if (ID.equals(entry.get(0))) {

          found = true;
          StaffAssessment.Assessment(entry);
          break;
        }  
      }

      if (!found) {

        maxAttempts -= 1;
        if (maxAttempts == 0) {
          System.out.println("Sorry, too many attempts. Please try again later!");
          break;
        }

        System.out.println("Sorry, the ID isn't in the database. Try again!");
        System.out.println("You have " + maxAttempts + " attempts left");
      } else {
        break;
      }

    }
  }
}
