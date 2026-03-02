import interfaces.SignUp;
import java.util.Scanner;
import java.util.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

class StudentSignUp extends SignUpRole implements SignUp {

  static String studentName = "";
  static String studentID = "";
  private String newStudentID;
  protected int currentIndex;

  public StudentSignUp(String newStudentID, int currentIndex) {

    this.newStudentID = newStudentID;
    this.currentIndex = currentIndex;

  }

  public String getNewStudentID() {

    return newStudentID;
  }

  public void setNewStudentID(String newStudentID) {

    this.newStudentID = newStudentID;
  }

  public static void newStudentID() {

    Scanner sc = new Scanner(System.in);
    int maxAttempts = 5;

    while (maxAttempts != 0) {

      System.out.println("Enter your 8-digit student ID set by the university?");
      String newStudentID = sc.nextLine();

      try (BufferedReader reader = new BufferedReader(new FileReader("students.txt"))) {

        String line;
        boolean found = false;

        while ((line = reader.readLine()) != null) {

          String[] parts = line.split(",");
          String studentName = parts[0].trim();
          String studentID = parts[1].trim();

          if (studentID.equals(newStudentID.trim())) {

            if (StudentData.registeredStudents.containsKey(studentID)) {

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

            System.out.println("Welcome " + studentName);
            StudentSignUp.studentName = studentName;
            StudentSignUp.studentID = studentID;
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

          System.out.println("You have " + maxAttempts + " attempts left");
          System.out.println("Student ID not found.");
        } else if (StudentSignUp.studentID.equals(newStudentID.trim())) {
          break;
        }

      } catch (IOException e) {

        System.out.println("Error reading file: " + e.getMessage());

      }
    }
  }

  public static String newStudentPassword() {

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

  public static void confirmNewStudentPassword(String password, String studentName, String newStudentID) {

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

      StudentData.registeredStudents.put(newStudentID, password);
      StudentPassword.studentPasswords.add(password);

      System.out.println("Sign up successful for " + studentName + "!");
      System.out.println("Number of registered students in the app: " + StudentData.registeredStudents.size());
      System.out.println("Have a good day!");
      break;
    }
  }
}
