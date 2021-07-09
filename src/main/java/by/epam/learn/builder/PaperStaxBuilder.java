package by.epam.learn.builder;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Set;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.learn.entity.Booklet;
import by.epam.learn.entity.Magazine;
import by.epam.learn.entity.Newspaper;
import by.epam.learn.entity.Paper;
import by.epam.learn.exception.XmlException;
import by.epam.learn.handler.PaperXmlTag;

public class PaperStaxBuilder extends AbstractPapersBuilder {
	public static Logger log = LogManager.getLogger();
	private Set<Paper> papers;
	private XMLInputFactory inputFactory;
	private static final String ATTRIBUTE_ID = "id";
	private static final String ATTRIBUTE_THEME = "theme";
	private static final String DEFAULT_ATTRIBUTE_THEME = "news";
	private static final char HYPHEN = '-';
	private static final char UNDERSCORE = '_';

	public PaperStaxBuilder() {
		inputFactory = XMLInputFactory.newInstance();
	}

	public PaperStaxBuilder(Set<Paper> papers) {
		super(papers);
		inputFactory = XMLInputFactory.newInstance();
	}

	public Set<Paper> getPapers() {
		return papers;
	}

	@Override
	public void buildSetPapers(String filename) throws XmlException {
		XMLStreamReader reader;
		String name;
		try (FileInputStream inputStream = new FileInputStream(new File(filename))) {
			reader = inputFactory.createXMLStreamReader(inputStream);
			while (reader.hasNext()) {
				int type = reader.next();
				if (type == XMLStreamConstants.START_ELEMENT) {
					name = reader.getLocalName();
					PaperXmlTag tag = PaperXmlTag.valueOf(name.toUpperCase());
					switch (tag) {
					case NEWSPAPER:
						Paper paper = new Newspaper();
						buildPaper(reader, paper);
						papers.add(paper);
						break;
					case MAGAZINE:
						paper = new Magazine();
						buildPaper(reader, paper);
						papers.add(paper);
						break;
					case BOOKLET:
						paper = new Booklet();
						buildPaper(reader, paper);
						papers.add(paper);
						break;
					}
				}
			}
		} catch (XMLStreamException | FileNotFoundException e) {
			log.error("parsing error: ", e);
			throw new XmlException("parsing error", e);
		} catch (IOException e) {
			log.error(filename + " is not found", e);
			throw new XmlException(filename + " is not found", e);
		}
	}

	private Paper buildPaper(XMLStreamReader reader, Paper paper) throws XMLStreamException {
		paper.setId(reader.getAttributeValue(null, ATTRIBUTE_ID));
		paper.setTheme(reader.getAttributeValue(null, ATTRIBUTE_THEME).isEmpty() ? DEFAULT_ATTRIBUTE_THEME
				: reader.getAttributeValue(null, ATTRIBUTE_THEME));
		String name;
		while (reader.hasNext()) {
			int type = reader.next();
			switch (type) {
			case XMLStreamConstants.START_ELEMENT:
				name = reader.getLocalName();
				PaperXmlTag tag = PaperXmlTag.valueOf(name.toUpperCase().replace(HYPHEN, UNDERSCORE));
				switch (tag) {
				case TITLE:
					paper.setTitle(getXMLText(reader));
					break;
				case DATE:
					paper.setDate(getXMLText(reader));
					break;
				case IS_MONTHLY:
					paper.setMonthly(getXMLText(reader));
					break;
				case IS_COLOR:
					paper.setColor(getXMLText(reader));
					break;
				case NUMBER_PAGES:
					paper.setNumberPages(getXMLText(reader));
					break;
				case IS_GLOSSY:
					paper.setGlossy(getXMLText(reader));
					break;
				case SUBSCRIPT_INDEX:
					if (paper instanceof Newspaper) {
						((Newspaper) paper).setSubscriptIndex(getXMLText(reader));
					}
					if (paper instanceof Magazine) {
						((Magazine) paper).setSubscriptIndex(getXMLText(reader));
					}
					break;
				}
				break;
			case XMLStreamConstants.END_ELEMENT:
				name = reader.getLocalName();
				if (PaperXmlTag.valueOf(name.toUpperCase()) == PaperXmlTag.NEWSPAPER
						|| PaperXmlTag.valueOf(name.toUpperCase()) == PaperXmlTag.MAGAZINE
						|| PaperXmlTag.valueOf(name.toUpperCase()) == PaperXmlTag.BOOKLET) {
					return paper;
				}
				break;
			}
		}
		throw new XMLStreamException("Unknown element in tag Paper");
	}

	private String getXMLText(XMLStreamReader reader) throws XMLStreamException {
		String text = null;
		if (reader.hasNext()) {
			reader.next();
			text = reader.getText();
		}
		return text;
	}
}
