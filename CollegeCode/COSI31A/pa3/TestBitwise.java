package pa3;

import java.io.*;
import java.util.*;
import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.experimental.runners.Enclosed;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.*;

/**
 * Test the interface of Bitwise. Note that toString uses isset, so
 * testing the toString and set methods is pretty good coverage.
 *
 * To run, enter this at the command line:
 *
 * <pre>
 * javac TestBitwise.java && java org.junit.runner.JUnitCore TestBitwise
 * </pre>
 */
public class TestBitwise {
    @Test
    public void testToString() {
        assertEquals("11111111", Bitwise.toString((byte)0xff));
        assertEquals("00000000", Bitwise.toString((byte)0x00));
        assertEquals("00000001", Bitwise.toString((byte)0x01));
        assertEquals("00010000", Bitwise.toString((byte)0x10));
        assertEquals("10000000", Bitwise.toString((byte)0x80));
        assertEquals("10001000", Bitwise.toString((byte)0x88));
    }

    @Test
    public void testMultiByteToString() {
        byte bytes1[] = {(byte)0x00, (byte)0xff};
        assertEquals("00000000 11111111", Bitwise.toString(bytes1, " "));
        
        byte bytes2[] = {(byte)0x88, (byte)0x01};
        assertEquals("10001000 00000001", Bitwise.toString(bytes2, " "));
    }

    @Test
    public void testSet() {
        byte b = 0x00;
        b = Bitwise.set(0, b);
        assertEquals("10000000", Bitwise.toString(b));
    }

    @Test
    public void testMultiByteSet() {
        byte bytes[] = {(byte)0x00, (byte)0x00};
        Bitwise.set(8, bytes);
        assertEquals("00000000 10000000", Bitwise.toString(bytes, " "));
        Bitwise.set(7, bytes);
        Bitwise.set(0, bytes);
        assertEquals("10000001 10000000", Bitwise.toString(bytes, " "));
        Bitwise.set(15, bytes);
        assertEquals("10000001 10000001", Bitwise.toString(bytes, " "));
    }

    @Test
    public void testClearAll() {
        byte bytes[] = {(byte)0xff, (byte)0x01};
        assertEquals("11111111 00000001", Bitwise.toString(bytes, " "));
        Bitwise.clearAll(bytes);
        assertEquals("00000000 00000000", Bitwise.toString(bytes, " "));
    }
}
