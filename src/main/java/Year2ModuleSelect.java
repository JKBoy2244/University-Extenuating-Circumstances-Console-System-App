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

class Year2ModuleSelect extends YearGroup implements Year {

  private String module;

  public Year2ModuleSelect(String option, String module) {

    super(option);
    this.module = module;
  }

  public String getModule() {

    return module;
  }

  public void setModule(String module) {

    this.module = module;
  }

  public static void selectModule(String studID, String option) {

    Scanner sc = new Scanner(System.in);
    int maxAttempts = 5;

    while (maxAttempts != 0) {

      System.out.println("Enter your chosen 2nd year module in the year2Modules.txt to write your EC about!");
      String module = sc.nextLine();

      boolean found = false;

      try {

        File file = new File("year2Modules.txt");
        Scanner scanner = new Scanner(file);

        while (scanner.hasNextLine()) {
          String line = scanner.nextLine();

          if (line.contains(module.trim())) {
            found = true;
            break;
          }
        }

        scanner.close();

        if (found) {
          System.out.println("Found");
          ECform.ECwrite(module, option, studID);
          break;
        } else {

          maxAttempts -= 1;
          if (maxAttempts == 0) {
            System.out.println("Sorry, too many attempts. Please try again later!");
            break;
          }

          System.out.println("Sorry, invalid input. Try again!");
          System.out.println("You have " + maxAttempts + " attempts left");
          System.out.println("Not found");
        }

      } catch (FileNotFoundException e) {
        System.out.println("File not found.");
        break;
      }

    }

  }
}
