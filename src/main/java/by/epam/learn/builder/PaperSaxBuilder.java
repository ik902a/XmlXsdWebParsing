package by.epam.learn.builder;

import java.io.IOException;
import java.util.Set;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import by.epam.learn.entity.Paper;
import by.epam.learn.exception.XmlException;
import by.epam.learn.handler.PaperErrorHandler;
import by.epam.learn.handler.PaperHandler;

public class PaperSaxBuilder extends AbstractPapersBuilder{
	public static Logger log = LogManager.getLogger();
	private Set<Paper> papers;
	private PaperHandler handler = new PaperHandler();
	private XMLReader reader;
	

	public PaperSaxBuilder() throws XmlException {
		SAXParserFactory factory = SAXParserFactory.newInstance();
		try {
			SAXParser saxParser = factory.newSAXParser();
			reader = saxParser.getXMLReader();
		} catch (ParserConfigurationException | SAXException e) {
			log.error("parser configuration error ", e);
			throw new XmlException("parser configuration error: ", e);
		}
		reader.setErrorHandler(new PaperErrorHandler());
		reader.setContentHandler(handler);
	}
	
	public PaperSaxBuilder(Set<Paper> papers) {
		super(papers);
	}
	
	public Set<Paper> getPapers() {
		return papers;
	}
	
	@Override
	public void  buildSetPapers(String filename) throws XmlException {
		try {
			reader.parse(filename);
		} catch (IOException | SAXException e) {
			log.error("parsing error: ", e);
			throw new XmlException("parsing error ", e);
		}
		papers = handler.getPapers();
		log.info("parsing result: " + getPapers());
	}
}
