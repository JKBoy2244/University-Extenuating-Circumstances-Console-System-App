import org.junit.jupiter.api.*;
import java.io.ByteArrayOutputStream;

import static org.junit.jupiter.api.Assertions.*;

public class StudentLogInTest {

  @BeforeEach
  void resetStudents() {
    StudentData.registeredStudents.clear();
  }

  @Test
  void studentID_returns_id_if_exists_in_registeredStudents() {
    StudentData.registeredStudents.put("11112222", "pass123");

    TestUtils.setInput("11112222\n");
    TestUtils.captureOutput();

    String id = StudentLogin.studentID();
    assertEquals("11112222", id);
  }

  @Test
  void studentID_returns_null_after_5_invalid_attempts() {
    TestUtils.setInput("x\nx\nx\nx\nx\n");
    ByteArrayOutputStream out = TestUtils.captureOutput();

    String id = StudentLogin.studentID();
    assertNull(id);

    String printed = out.toString();
    assertTrue(printed.contains("too many attempts"));
  }

  @Test
  void studentPassword_prints_login_successful_when_password_matches() {
    StudentData.registeredStudents.put("11112222", "pass123");

    // After password succeeds, it goes to menu and asks "1 or 2"
    // Choose 1, then ViewEC.checkECOutcome() will run and ask for ID.
    // Provide one more line so it doesn't hang (it will likely say no application).
    String input =
        "pass123\n" +
        "1\n" +
        "11112222\n"; // for ViewEC prompt

    TestUtils.setInput(input);
    ByteArrayOutputStream out = TestUtils.captureOutput();

    StudentLogin.studentPassword("11112222");

    String printed = out.toString();
    assertTrue(printed.contains("Login successful!"));
  }
}
