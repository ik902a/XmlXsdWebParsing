package test.epam.learn.validator;

import static org.testng.Assert.assertTrue;

import org.testng.annotations.Test;

import by.epam.learn.validator.XmlValidator;

public class XmlValidatorTest {
	private static final String TEST_FILE = "data_xml/papers.xml";
	private static final String SCHEMA_FILE = "data_xml/papers.xsd";
	
	@Test
	public void  isCorrectXmlTest() {		
		boolean value = XmlValidator.isCorrectXml(TEST_FILE, SCHEMA_FILE);
		assertTrue(value);
	}
}
