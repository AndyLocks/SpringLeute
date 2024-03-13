package hash_test;

import org.apache.commons.codec.digest.DigestUtils;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class HashTest {
    @Test
    public void hashTest() {
        System.out.println(DigestUtils.sha1Hex("aboba"));
    }
}
