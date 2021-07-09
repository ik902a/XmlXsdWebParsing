package by.epam.learn.handler;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXParseException;

public class PaperErrorHandler implements ErrorHandler {
	private static Logger log = LogManager.getLogger();

	public void warning(SAXParseException e) {
		log.warn(getLineColumnNumber(e) + "-" + e.getMessage());
	}

	public void error(SAXParseException e) {
		log.error(getLineColumnNumber(e) + " - " + e.getMessage());
	}

	public void fatalError(SAXParseException e) {
		log.fatal(getLineColumnNumber(e) + " - " + e.getMessage());
	}

	private String getLineColumnNumber(SAXParseException e) {
		return e.getLineNumber() + " : " + e.getColumnNumber();
	}
}
