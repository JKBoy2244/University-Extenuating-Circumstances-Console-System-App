import java.util.Scanner;
import java.util.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;


class StaffAssessment extends StaffDashBoard {

  private String result;
  private String report;

  public StaffAssessment(String result, String report) {

    this.result = result;
    this.report = report;
  }

  public String getResult() {

    return result;
  }

  public String getReport() {

    return report;
  }

  public void setReport(String report) {

    this.report = report;
  }

  public static void Assessment(List<String> application) {

    for (String value : application) {
      System.out.println(value);
    }

    Scanner sc = new Scanner(System.in);
    int maxAttempts = 5;

    while (maxAttempts != 0) {

      System.out.println("Read the application thoroughly and tell me your verdict!");
      System.out.println("Type either: accepted or rejected!");
      String result = sc.nextLine();

      if (result.equalsIgnoreCase("Rejected") || result.equalsIgnoreCase("Accepted")) {

        System.out.println("Write a detailed report between 500 and 1000 words long explaining why you came to that conclusion?");
        String report = sc.nextLine();

        String[] rawWords = report.trim().split("\\s+");
        int reportCount = 0;
        for (String w : rawWords) {
          if (!w.isEmpty()) 
            reportCount++;
        }

        if (reportCount >= 500 && reportCount <= 1000) {

          List<String> outcomeEntry = new ArrayList<>(application);
          outcomeEntry.add(result);
          outcomeEntry.add(report);
          ECApplicationOutcomes.Outcomes.add(outcomeEntry);
          break;

        } else {

          maxAttempts -= 1;
          if (maxAttempts == 0) {
            System.out.println("Sorry, too many attempts. Please try again later!");
            break;
          }

          System.out.println("Sorry, invalid input. Try again!");
          System.out.println("You have " + maxAttempts + " attempts left");

        }


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
}
