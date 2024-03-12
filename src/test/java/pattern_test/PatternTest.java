package pattern_test;

import org.junit.jupiter.api.Test;

import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.*;

public class PatternTest {
    @Test
    public void patternTest() {
        assertTrue(Pattern.compile("^(.+)@(\\S+)$").matcher("jandylokc@gmail.com").matches());
        assertTrue(Pattern.compile("^(.+)@(\\S+)$").matcher("sho@gmail.com").matches());
        assertTrue(Pattern.compile("^(.+)@(\\S+)$").matcher("nastya@aga.com").matches());
        assertFalse(Pattern.compile("^(.+)@(\\S+)$").matcher("aboba").matches());
        assertFalse(Pattern.compile("^(.+)@(\\S+)$").matcher("adpkjfkasdjf").matches());
    }
}
