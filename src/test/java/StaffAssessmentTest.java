import org.junit.jupiter.api.*;
import java.io.ByteArrayOutputStream;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class StaffAssessmentTest {

  @BeforeEach
  void resetStaticState() {
    ECApplicationOutcomes.Outcomes.clear();
  }

  @Test
  void assessment_accepts_valid_result_and_500_word_report_adds_outcome() {
    // Arrange: a fake application entry (whatever structure your app uses)
    List<String> application = new ArrayList<>();
    application.add("12345678");            // student ID at index 0
    application.add("Some module");
    application.add("Some reason");

    // Input flow in StaffAssessment.Assessment:
    // 1) result (Accepted/Rejected)
    // 2) report (must be 500-1000 words)
    String input =
        "Accepted\n" +
        TestUtils.words(500) + "\n";

    TestUtils.setInput(input);
    ByteArrayOutputStream out = TestUtils.captureOutput();

    // Act
    StaffAssessment.Assessment(application);

    // Assert: outcome set has exactly one entry, containing app fields + result + report
    assertEquals(1, ECApplicationOutcomes.Outcomes.size());

    List<String> stored = ECApplicationOutcomes.Outcomes.iterator().next();
    assertEquals("12345678", stored.get(0));
    assertTrue(stored.contains("Accepted"));       // result appended
    assertTrue(stored.get(stored.size() - 1).split("\\s+").length >= 500); // report appended

    // Optional: assert it printed something
    String printed = out.toString();
    assertTrue(printed.contains("verdict"));
  }

  @Test
  void assessment_rejects_report_under_500_words_and_does_not_add_outcome() {
    List<String> application = List.of("12345678", "X", "Y");

    // result ok, report too short, then keep failing until attempts run out
    // We must provide enough lines for multiple loops.
    String input =
        "Rejected\n" + TestUtils.words(10) + "\n" +
        "Rejected\n" + TestUtils.words(10) + "\n" +
        "Rejected\n" + TestUtils.words(10) + "\n" +
        "Rejected\n" + TestUtils.words(10) + "\n" +
        "Rejected\n" + TestUtils.words(10) + "\n";

    TestUtils.setInput(input);
    TestUtils.captureOutput();

    StaffAssessment.Assessment(application);

    assertEquals(0, ECApplicationOutcomes.Outcomes.size());
  }
}
