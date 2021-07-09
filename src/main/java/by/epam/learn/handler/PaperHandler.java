package by.epam.learn.handler;

import java.util.EnumSet;
import java.util.HashSet;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import by.epam.learn.entity.Booklet;
import by.epam.learn.entity.Magazine;
import by.epam.learn.entity.Newspaper;
import by.epam.learn.entity.Paper;

public class PaperHandler extends DefaultHandler {
	public static Logger log = LogManager.getLogger();
	private Set<Paper> papers;
	private Paper current;
	private PaperXmlTag currentXmlTag;
	private EnumSet<PaperXmlTag> withText;
	
	private static final String ELEMENT_NEWSPAPER = "newspaper";
	private static final String ELEMENT_MAGAZINE = "magazine";
	private static final String ELEMENT_BOOKLET = "booklet";
	private static final String ATTRIBUTE_ID = "id";
	private static final String ATTRIBUTE_THEME = "theme";
	private static final String DEFAULT_ATTRIBUTE_THEME = "news";
	private static final char HYPHEN = '-';
	private static final char UNDERSCORE = '_';
	
	public PaperHandler() {
		papers = new HashSet<Paper>();
		withText = EnumSet.range(PaperXmlTag.TITLE, PaperXmlTag.SUBSCRIPT_INDEX);
	}

	public Set<Paper> getPapers() {
		return papers;
	}
	
	public void startElement(String uri, String localName, String qName, Attributes attrs) {
		switch (qName) {
		case ELEMENT_NEWSPAPER:
			current = new Newspaper();
			setElementAttributes(current, attrs);
			break;
		case ELEMENT_MAGAZINE:
			current = new Magazine();
			setElementAttributes(current, attrs);
			break;
		case ELEMENT_BOOKLET:
			current = new Booklet();
			setElementAttributes(current, attrs);
		default:
			PaperXmlTag temp = PaperXmlTag.valueOf(qName.replace(HYPHEN, UNDERSCORE).toUpperCase());
			if (withText.contains(temp)) {
				currentXmlTag = temp;
			}
		}
	}
	
	private void setElementAttributes(Paper current, Attributes attrs) {
		current.setId(attrs.getValue(attrs.getIndex(ATTRIBUTE_ID)));
		current.setTheme(attrs.getLength() == 2 ? attrs.getValue(attrs.getIndex(ATTRIBUTE_THEME))
												: DEFAULT_ATTRIBUTE_THEME);
	}

	public void endElement(String uri, String localName, String qName) {		
		if (ELEMENT_NEWSPAPER.equals(qName) 
			|| ELEMENT_MAGAZINE.equals(qName) 
			|| ELEMENT_BOOKLET.equals(qName)) {
			papers.add(current);
		}
	}
	
	public void characters(char[] ch, int start, int length) {
		String data = new String(ch, start, length).strip();
		if (currentXmlTag != null) {
			switch (currentXmlTag) {
			case TITLE:
				current.setTitle(data);
				break;
			case DATE:
				current.setDate(data);
				break;
			case IS_MONTHLY:
				current.setMonthly(data);
				break;
			case IS_COLOR:
				current.setColor(data);
				break;
			case NUMBER_PAGES:
				current.setNumberPages(data);
				break;
			case IS_GLOSSY:
				current.setGlossy(data);
				break;
			case SUBSCRIPT_INDEX:
				if (current instanceof Newspaper) {
					((Newspaper) current).setSubscriptIndex(data);
				}
				if (current instanceof Magazine) {
					((Magazine) current).setSubscriptIndex(data);
				}
				break;
			default:
				log.error("enum constant not present");
			}
		}
		currentXmlTag = null;
	}
}
