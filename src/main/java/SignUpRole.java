import interfaces.signUp;
import java.util.Scanner;
import java.util.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

class SignUpRole implements signUp {

  public void StudentOrStaff() {

    Scanner sc = new Scanner(System.in);
    int maxAttempts = 5;

    while (maxAttempts != 0) {

      System.out.println("------SIGN UP------");
      System.out.println("1). Student");
      System.out.println("2). Staff");
      System.out.println("For clarification, please type either 1 or 2?");
      String option = sc.nextLine();

      if (option.trim().equals("1")) {

        StudentSignUp.newStudentID();
        String password = StudentSignUp.newStudentPassword();
        if (password != null) {
          StudentSignUp.confirmNewStudentPassword(password, StudentSignUp.studentName, StudentSignUp.studentID);
        }
        break;

      } else if (option.trim().equals("2")) {

        StaffSignUp.newStaffID();
        String password = StaffSignUp.newStaffPassword();
        if (password != null) {
          StaffSignUp.confirmNewStaffPassword(password, StaffSignUp.staffName, StaffSignUp.staffID);
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
