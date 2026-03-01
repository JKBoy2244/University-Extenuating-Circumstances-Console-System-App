import java.io.*;
import java.nio.charset.StandardCharsets;

public class TestUtils {
  public static void setInput(String data) {
    System.setIn(new ByteArrayInputStream(data.getBytes(StandardCharsets.UTF_8)));
  }

  public static ByteArrayOutputStream captureOutput() {
    ByteArrayOutputStream out = new ByteArrayOutputStream();
    System.setOut(new PrintStream(out));
    return out;
  }

  public static String words(int n) {
    // generate n simple words: "w w w ..."
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < n; i++) {
      if (i > 0) sb.append(' ');
      sb.append("w");
    }
    return sb.toString();
  }
}
