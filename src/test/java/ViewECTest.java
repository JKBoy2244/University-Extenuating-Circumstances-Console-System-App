import org.junit.jupiter.api.*;
import java.io.ByteArrayOutputStream;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class ViewECTest {

  @BeforeEach
  void resetStaticState() {
    ECApplication.Application.clear();
    ECApplicationOutcomes.Outcomes.clear();
  }

  @Test
  void viewEC_blocks_if_no_application_exists_for_id() {
    // No applications added.

    // User types ID 5 times -> hits max attempts
    String input =
        "12345678\n12345678\n12345678\n12345678\n12345678\n";
    TestUtils.setInput(input);
    ByteArrayOutputStream out = TestUtils.captureOutput();

    ViewEC.checkECOutcome();

    String printed = out.toString();
    assertTrue(printed.contains("didn't create an EC application"));
    assertTrue(printed.contains("too many attempts"));
  }

  @Test
  void viewEC_shows_outcome_if_application_and_outcome_exist() {
    // Application exists
    ECApplication.Application.add(List.of("12345678", "AppField1", "AppField2"));

    // Outcome exists (must have same ID at index 0)
    List<String> outcome = new ArrayList<>();
    outcome.add("12345678");
    outcome.add("AppField1");
    outcome.add("AppField2");
    outcome.add("Accepted");
    outcome.add("report here");
    ECApplicationOutcomes.Outcomes.add(outcome);

    String input = "12345678\n";
    TestUtils.setInput(input);
    ByteArrayOutputStream out = TestUtils.captureOutput();

    ViewEC.checkECOutcome();

    String printed = out.toString();
    assertTrue(printed.contains("Accepted"));
    assertTrue(printed.contains("report here"));
  }

  @Test
  void viewEC_says_under_consideration_if_application_exists_but_no_outcome_yet() {
    ECApplication.Application.add(List.of("12345678", "A", "B"));

    String input = "12345678\n";
    TestUtils.setInput(input);
    ByteArrayOutputStream out = TestUtils.captureOutput();

    ViewEC.checkECOutcome();

    String printed = out.toString();
    assertTrue(printed.contains("under consideration"));
  }
}
