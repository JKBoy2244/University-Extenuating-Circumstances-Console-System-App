import interfaces.LogIn;
import java.util.Scanner;
import java.util.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

class LogInRole implements LogIn {

  public void StudentOrStaff() {

    Scanner sc = new Scanner(System.in);
    int maxAttempts = 5;

    while (maxAttempts != 0) {
      System.out.println("------LOG IN------");
      System.out.println("1). Student");
      System.out.println("2). Staff");
      System.out.println("For clarification, please type either 1 or 2?");
      String option = sc.nextLine();

      if (option.trim().equals("1")) {

        if (StudentData.registeredStudents.isEmpty()) {

          maxAttempts -= 1;
          if (maxAttempts == 0) {

            System.out.println("Sorry, too many attempts. Please try again later!");
            break;
          }

          System.out.println("No students are registered. You have " + maxAttempts + " attempts left");
          System.out.println("Number of registered students in the app: " + StudentData.registeredStudents.size());
          continue;

        }

        String studID = StudentLogin.studentID();
        if (studID != null) {
          StudentLogin.studentPassword(studID);
        }
        break;

      } else if (option.trim().equals("2")) {

        if (StaffData.registeredStaff.isEmpty()) {

          maxAttempts -= 1;
          if (maxAttempts == 0) {

            System.out.println("Sorry, too many attempts. Please try again later!");
            break;
          }

          System.out.println("No staff are registered. You have " + maxAttempts + " attempts left");
          System.out.println("Number of registered staff in the app: " + StaffData.registeredStaff.size());
          continue;

        }

        String staffID = StaffLogin.staffID();
        if (staffID != null) {
          StaffLogin.staffPassword(staffID);
        }
        break;

      } else {

        maxAttempts -= 1;
        if (maxAttempts == 0) {

          System.out.println("Sorry, too many attempts. Please try again later!");
          break;
        }

        System.out.println("Invalid input. You have " + maxAttempts + " attempts left");

      }
    }
  }
}
