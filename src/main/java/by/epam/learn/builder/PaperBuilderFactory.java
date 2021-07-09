package by.epam.learn.builder;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.learn.exception.XmlException;

public class PaperBuilderFactory {
	public static Logger log = LogManager.getLogger();
	
	private enum TypeParser {
		SAX, STAX, DOM
		}

	public PaperBuilderFactory() {
	}

	public static AbstractPapersBuilder createPaperBuilder(String typeParser) throws XmlException {
		TypeParser type = TypeParser.valueOf(typeParser.toUpperCase());
		AbstractPapersBuilder builder = null;
		switch (type) {
		case DOM:
			builder = new PaperDomBuilder();
			break;
		case STAX:
			builder = new PaperStaxBuilder();
			break;
		case SAX:
			builder = new PaperSaxBuilder();
			break;
		default:
			throw new XmlException("parser " + typeParser + " is not found");
		}
		log.info(builder.getClass().getName() + " will be used");
		return builder;
	}
}
