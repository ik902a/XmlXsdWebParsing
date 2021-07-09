package by.epam.learn.validator;

import java.io.File;
import java.io.IOException;

import javax.xml.XMLConstants;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.SAXException;

import by.epam.learn.handler.PaperErrorHandler;

public class XmlValidator {
	static Logger log = LogManager.getLogger();
	
	public static boolean isCorrectXml(String fileName, String schemaName) {
		String language = XMLConstants.W3C_XML_SCHEMA_NS_URI;
		SchemaFactory factory = SchemaFactory.newInstance(language);
		File schemaLocation = new File(schemaName);
		try {
			Schema schema = factory.newSchema(schemaLocation);
			Validator validator = schema.newValidator();
			Source source = new StreamSource(fileName);
			validator.setErrorHandler(new PaperErrorHandler());
			validator.validate(source);
		} catch (SAXException | IOException e) {
			log.error(fileName + " is not correct or valid");
			return false;
		}
		return true;
	}
}
