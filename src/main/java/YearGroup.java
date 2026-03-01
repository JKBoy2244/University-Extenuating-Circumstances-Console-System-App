import interfaces.Year;
import java.util.Scanner;
import java.util.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

class YearGroup implements Year {

  protected String option;

  public YearGroup(String option) {

    this.option = option;
  }

  public String getOption() {

    return option;
  }

  public void setOption(String option) {

    this.option = option;
  }

  public void yearGroupSelection(String studID) {

    Scanner sc = new Scanner(System.in);
    int maxAttempts = 5;

    while (maxAttempts != 0) {
      System.out.println("Welcome to the University Extenuating Circumstances App. Please select your current year group first: either 1st, 2nd or 3rd?");
      System.out.println("For clear clarification, please type 1, 2 or 3 " + studID + " ?");
      option = sc.nextLine();

      if (option.equals("1")) {

        try (BufferedReader reader = new BufferedReader(new FileReader("studentsYrGrp.txt"))) {

          String line;
          boolean found = false;

          while ((line = reader.readLine()) != null) {

            String[] parts = line.split(",");
            String studentID = parts[0].trim();
            String studentYrGrp = parts[1].trim();

            if (studentID.equals(studID) && studentYrGrp.equals("1")) {

              found = true;
              Year1ModuleSelect.selectModule(studID, option);
              break;
            }
          }

          if (!found) {

            maxAttempts -= 1;
            if (maxAttempts == 0) {
              System.out.println("Sorry, too many attempts. Please try again later!");
              break;
            }

            System.out.println("You have " + maxAttempts + " attempts left");
            System.out.println("That's not your correct year group.");
          } else {
            break;
          }

        } catch (IOException e) {

          System.out.println("Error reading file: " + e.getMessage());
          break;

        }

      } else if (option.equals("2")) {

        try (BufferedReader reader = new BufferedReader(new FileReader("studentsYrGrp.txt"))) {

          String line;
          boolean found = false;

          while ((line = reader.readLine()) != null) {

            String[] parts = line.split(",");
            String studentID = parts[0].trim();
            String studentYrGrp = parts[1].trim();

            if (studentID.equals(studID) && studentYrGrp.equals("2")) {

              found = true;
              Year2ModuleSelect.selectModule(studID, option);
              break;
            }
          }

          if (!found) {

            maxAttempts -= 1;
            if (maxAttempts == 0) {
              System.out.println("Sorry, too many attempts. Please try again later!");
              break;
            }

            System.out.println("You have " + maxAttempts + " attempts left");
            System.out.println("That's not your correct year group.");
          } else {
            break;
          }

        } catch (IOException e) {

          System.out.println("Error reading file: " + e.getMessage());
          break;

        }

      } else if (option.equals("3")) {

        try (BufferedReader reader = new BufferedReader(new FileReader("studentsYrGrp.txt"))) {

          String line;
          boolean found = false;

          while ((line = reader.readLine()) != null) {

            String[] parts = line.split(",");
            String studentID = parts[0].trim();
            String studentYrGrp = parts[1].trim();

            if (studentID.equals(studID) && studentYrGrp.equals("3")) {

              found = true;
              Year3ModuleSelect.selectModule(studID, option);
              break;
            }
          }

          if (!found) {

            maxAttempts -= 1;
            if (maxAttempts == 0) {
              System.out.println("Sorry, too many attempts. Please try again later!");
              break;
            }

            System.out.println("You have " + maxAttempts + " attempts left");
            System.out.println("That's not your correct year group.");
          } else {
            break;
          }

        } catch (IOException e) {

          System.out.println("Error reading file: " + e.getMessage());
          break;

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
