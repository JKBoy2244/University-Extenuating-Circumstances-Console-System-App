import java.util.Scanner;
import java.util.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {
  public static void main(String[] args) {

    int maxAttempts = 5;

    Scanner sc = new Scanner(System.in);

    while (maxAttempts != 0) {

      System.out.println("1). Sign Up");
      System.out.println("2). Log in");
      System.out.println("Type either 1 or 2");

      String response = sc.nextLine();

      if (response.trim().equals("1")) {

        SignUpRole signUp = new SignUpRole();
        signUp.StudentOrStaff();
        break;

      } else if (response.trim().equals("2")) {

        if (StudentData.registeredStudents.isEmpty() && StaffData.registeredStaff.isEmpty()) {

          maxAttempts -= 1;

          if (maxAttempts == 0) {

            System.out.println("Sorry, too many attempts. Please try again later!");
            break;
          }

          System.out.println("No one is registered at the moment so not possible. You have " + maxAttempts + " attempts left");
          System.out.println("Number of registered students in the app: " + StudentData.registeredStudents.size());
          System.out.println("Number of registered staff in the app: " + StaffData.registeredStaff.size());
          System.out.println("No accounts registered yet. Please sign up first!");
          continue;
        }

        LogInRole logIn = new LogInRole();
        logIn.StudentOrStaff();
        break;

      } else {

        maxAttempts -= 1;

        if (maxAttempts == 0) {

          System.out.println("Sorry, too many attempts. Please try again later!");
          break;
        }

        System.out.println("Invalid input. You have " + maxAttempts + " attempts left");
        continue;
      }
    }
  }
}
