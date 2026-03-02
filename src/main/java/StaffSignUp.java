import interfaces.SignSUp;
import java.util.Scanner;
import java.util.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

class StaffSignUp extends SignUpRole implements SignSUp {

  static String staffName = "";
  static String staffID = "";
  private String newStaffID;
  protected int currentIndex;

  public StaffSignUp(String newStaffID, int currentIndex) {

    this.newStaffID = newStaffID;
    this.currentIndex = currentIndex;

  }

  public String getNewStaffID() {

    return newStaffID;
  }

  public void setNewStaffID(String newStaffID) {

    this.newStaffID = newStaffID;
  }

  public static void newStaffID() {

    Scanner sc = new Scanner(System.in);
    int maxAttempts = 5;

    while (maxAttempts != 0) {
      System.out.println("Enter your 12-digit staff ID set by the university?");

      String newStaffID = sc.nextLine();

      try (BufferedReader reader = new BufferedReader(new FileReader("staff.txt"))) {

        String line;
        boolean found = false;

        while ((line = reader.readLine()) != null) {

          String[] parts = line.split(",");
          String staffName = parts[0].trim();
          String staffID = parts[1].trim();

          if (staffID.equals(newStaffID)) {

            if (StaffData.registeredStaff.containsKey(staffID)) {

              maxAttempts -= 1;
              if (maxAttempts == 0) {
                System.out.println("Sorry, too many attempts. Please try again later!");
                break;
              }

              System.out.println("Invalid input. You have " + maxAttempts + " attempts left");
              System.out.println("Sorry, the ID already exists in the app database. Try again!");
              found = true;
              break;
            }

            System.out.println("Welcome " + staffName);
            StaffSignUp.staffName = staffName;
            StaffSignUp.staffID = staffID;
            found = true;
            break;
          }
        }

        if (!found) {
          maxAttempts -= 1;
          if (maxAttempts == 0) {
            System.out.println("Sorry, too many attempts. Please try again later!");
            break;
          }

          System.out.println("Invalid input. You have " + maxAttempts + " attempts left");
          System.out.println("Staff ID not found.");

        } else if (StaffSignUp.staffID.equals(newStaffID.trim())) {
          break;
        }

      } catch (IOException e) {

        System.out.println("Error reading file: " + e.getMessage());

      }
    }
  }

  public static String newStaffPassword() {

    Scanner sc = new Scanner(System.in);
    int maxAttempts = 5;

    while (maxAttempts != 0) {
      System.out.println("-----PASSWORD REQUIREMENTS-----");
      System.out.println("12 to 20 characters long!");
      System.out.println("At least 1 lower case letter and 1 upper case letter");
      System.out.println("At least one special character");
      System.out.println("At least one digit");
      System.out.println("Enter password!");
      String password = sc.nextLine();

      if (password.trim().length() < 12 || password.trim().length() > 20 || !password.matches(".*[a-z].*")
          || !password.matches(".*[A-Z].*") || !password.matches(".*\\d.*") || !password.matches(".*[^a-zA-Z0-9].*")) {

        maxAttempts -= 1;
        if (maxAttempts == 0) {
          System.out.println("Sorry, too many attempts. Please try again later!");
          break;
        }

        System.out.println("Invalid input. You have " + maxAttempts + " attempts left");
        System.out.println("Sorry, the password does not meet requirements, try again!");
        continue;
      }
      return password;
    }
    return null;
  }

  public static void confirmNewStaffPassword(String password, String staffName, String newStaffID) {

    Scanner sc = new Scanner(System.in);
    int maxAttempts = 5;

    while (maxAttempts != 0) {
      System.out.println("Confirm your password!");
      String confirmPassword = sc.nextLine();

      if (!confirmPassword.trim().equals(password)) {

        maxAttempts -= 1;
        if (maxAttempts == 0) {
          System.out.println("Sorry, too many attempts. Please try again later!");
          break;
        }

        System.out.println("Invalid input. You have " + maxAttempts + " attempts left");
        System.out.println("Sorry, both passwords don't match. Try again!");
        continue;
      }

      StaffData.registeredStaff.put(newStaffID, password);
      StaffPassword.staffPasswords.add(password);

      System.out.println("Sign up successful for " + staffName + "!");
      System.out.println("Number of registered staff in the app: " + StaffData.registeredStaff.size());
      System.out.println("Have a good day!");
      break;
    }
  }
}
