import java.util.Scanner;
import java.util.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

class StudentLogin {

  public static String studentID() {

    Scanner sc = new Scanner(System.in);
    int maxAttempts = 5;

    while (maxAttempts != 0) {

      System.out.println("Enter your student ID already registered in the app");
      String studID = sc.nextLine();

      if (StudentData.registeredStudents.containsKey(studID)) {
        return studID;
      }

      maxAttempts -= 1;
      if (maxAttempts == 0) {
        System.out.println("Sorry, too many attempts. Please try again later!");
        break;
      }

      System.out.println("Sorry, the student ID doesn't exist. Try again!");
      System.out.println("You have " + maxAttempts + " attempts left");

    }
    return null;
  }

  public static void studentPassword(String studID) {

    Scanner sc = new Scanner(System.in);
    int maxAttempts = 5;
    boolean loginSuccess = false;

    while (maxAttempts != 0) {
      System.out.println("Enter your password!");
      String studentPassword = sc.nextLine();

      String saved = StudentData.registeredStudents.get(studID);
      if (saved != null && saved.equals(studentPassword)) {
        System.out.println("Login successful!");
        loginSuccess = true;
        break;

      } else {

        maxAttempts -= 1;
        if (maxAttempts == 0) {
          System.out.println("Sorry, too many attempts. Please try again later!");
          break;
        }

        System.out.println("Sorry, the password is incorrect. Try again!");
        System.out.println("You have " + maxAttempts + " attempts left");

      }
    }

    if (loginSuccess) {

      int menuAttempts = 5;
      while (menuAttempts != 0) {

        System.out.println("1). View EC (outcome) 2). Submit EC");
        System.out.println("Type either 1 or 2!");
        String EC = sc.nextLine();

        if (EC.equals("1")) {

           ViewEC.checkECOutcome();
           break;

        } else if (EC.equals("2")) {
           YearGroup yearGroup = new YearGroup("");
           yearGroup.yearGroupSelection(studID);
           break;

        } else {

          menuAttempts -= 1;
          if (menuAttempts == 0) {
            System.out.println("Sorry, too many attempts. Please try again later!");
            break;
          }

          System.out.println("Sorry, invalid input. Try again!");
          System.out.println("You have " + menuAttempts + " attempts left");

        }
      }
    }
  }
}
