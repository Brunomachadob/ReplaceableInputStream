package com.brunomb.io;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

import org.junit.Test;

public class ReplaceableInputStreamTest {

	@Test
	public void testReplaceDeStreamComArquivo() throws Exception {
		ClassLoader cl = ReplaceableInputStreamTest.class.getClassLoader();

		InputStream is = cl.getResourceAsStream("inputFile.txt");
		InputStream isExpected = cl.getResourceAsStream("expected.txt");

		is = new ReplaceableInputStream(is, "&amp;", "&");
		is = new ReplaceableInputStream(is, "strings", "strings_2");
		is = new ReplaceableInputStream(is, "possamos", "POSSAMOS");
		is = new ReplaceableInputStream(is, "&apos;", "'");

		String actual = readInputStream(is);
		String expected = readInputStream(isExpected);

		assertEquals(expected, actual);
	}

	private static String readInputStream(InputStream is) throws Exception {
		ByteArrayOutputStream result = new ByteArrayOutputStream();
		byte[] buffer = new byte[1024];
		int length;

		while ((length = is.read(buffer)) != -1) {
			result.write(buffer, 0, length);
		}

		return result.toString("UTF-8");
	}
}
