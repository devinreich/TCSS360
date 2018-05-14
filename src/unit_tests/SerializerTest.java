package unit_tests;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;
import java.io.Serializable;

import org.junit.Rule;
import org.junit.jupiter.api.Test;
import org.junit.rules.ExpectedException;

import Controller.Serializer;

class SerializerTest {
	@Rule
    public ExpectedException thrown = ExpectedException.none();

	@Test
	void testDeserialize_withAValidPath() {
		Serializable obj = Serializer.deserialize("johnny");
		assertTrue(obj instanceof Serializable);
		assertNotEquals(obj, null);
	}
	
	@Test
	void testDeserialize_withAnInvalidPath() {
		Serializable obj = Serializer.deserialize("invalidpath");
		thrown.expect(FileNotFoundException.class);
	}

}
