package dsa.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import dsa.design.Codec;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.Before;
import org.junit.Test;

public class CodecTest {

  private Codec codec;

  @Before
  public void setup() {
    codec = new Codec();
  }

  @Test
  public void testEncodeAndDecode() {
    List<String> original = Arrays.asList("hello", "world");
    String encoded = codec.encode(original);
    List<String> decoded = codec.decode(encoded);
    assertEquals(original, decoded);
  }

  @Test
  public void testEmptyList() {
    List<String> original = new ArrayList<>();
    String encoded = codec.encode(original);
    List<String> decoded = codec.decode(encoded);
    assertEquals(original, decoded);
  }

  @Test
  public void testEmptyString() {
    List<String> original = Arrays.asList("");
    String encoded = codec.encode(original);
    List<String> decoded = codec.decode(encoded);
    assertEquals(original, decoded);
  }

  @Test
  public void testSpecialCharacters() {
    List<String> original = Arrays.asList("he#llo", "wor#ld");
    String encoded = codec.encode(original);
    List<String> decoded = codec.decode(encoded);
    assertEquals(original, decoded);
  }

  @Test
  public void testInvalidFormatDecoding() {
    assertThrows(IllegalArgumentException.class, () -> {
      codec.decode("invalidformat");
    });
  }
}
