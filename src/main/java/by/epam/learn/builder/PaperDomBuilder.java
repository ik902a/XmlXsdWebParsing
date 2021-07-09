package by.epam.learn.builder;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import by.epam.learn.entity.Booklet;
import by.epam.learn.entity.Magazine;
import by.epam.learn.entity.Newspaper;
import by.epam.learn.entity.Paper;
import by.epam.learn.exception.XmlException;
import by.epam.learn.handler.PaperXmlTag;

public class PaperDomBuilder extends AbstractPapersBuilder {
	public static Logger log = LogManager.getLogger();
	private DocumentBuilder docBuilder;
	private Set<Paper> papers;
	private static final String ATTRIBUTE_THEME = "theme";
	private static final String DEFAULT_ATTRIBUTE_THEME = "news";

	public PaperDomBuilder() {
		papers = new HashSet<Paper>();
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		try {
			docBuilder = factory.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			log.error("parser configuration error: ", e);
		}
	}

	public PaperDomBuilder(Set<Paper> papers) {
		super(papers);
	}

	public Set<Paper> getPapers() {
		return papers;
	}

	@Override
	public void buildSetPapers(String filename) throws XmlException {
		Document doc;
		try {
			doc = docBuilder.parse(filename);
			Element root = doc.getDocumentElement();
			bildPapersSet(root, PaperXmlTag.NEWSPAPER);
			bildPapersSet(root, PaperXmlTag.MAGAZINE);
			bildPapersSet(root, PaperXmlTag.BOOKLET);
			log.info("parsing result: " + getPapers());
		} catch (IOException | SAXException e) {
			throw new XmlException("parsing error ", e);
		}
	}

	private void bildPapersSet(Element root, PaperXmlTag tag) {
		String value = tag.getValue();
		NodeList papersList = root.getElementsByTagName(value);
		for (int i = 0; i < papersList.getLength(); i++) {
			Element paperElement = (Element) papersList.item(i);
			Paper paper = buildPaper(paperElement, tag);
			papers.add(paper);
		}
	}

	private Paper buildPaper(Element paperElement, PaperXmlTag tag) {
		Paper paper = null;
		switch (tag) {
		case NEWSPAPER:
			paper = new Newspaper();
			break;
		case MAGAZINE:
			paper = new Magazine();
			break;
		case BOOKLET:
			paper = new Booklet();
			break;
		}
		paper.setId(paperElement.getAttribute(PaperXmlTag.ID.getValue()));
		paper.setTheme(paperElement.getAttribute(ATTRIBUTE_THEME).isEmpty() ? DEFAULT_ATTRIBUTE_THEME
															: paperElement.getAttribute(ATTRIBUTE_THEME));
		paper.setTitle(getElementTextContent(paperElement, PaperXmlTag.TITLE.getValue()));
		paper.setDate(getElementTextContent(paperElement, PaperXmlTag.DATE.getValue()));
		paper.setMonthly(getElementTextContent(paperElement, PaperXmlTag.IS_MONTHLY.getValue()));
		paper.setColor(getElementTextContent(paperElement, PaperXmlTag.IS_COLOR.getValue()));
		paper.setNumberPages(getElementTextContent(paperElement, PaperXmlTag.NUMBER_PAGES.getValue()));
		paper.setGlossy(getElementTextContent(paperElement, PaperXmlTag.IS_GLOSSY.getValue()));
		if (paper instanceof Newspaper) {
			((Newspaper) paper)
					.setSubscriptIndex(getElementTextContent(paperElement, PaperXmlTag.SUBSCRIPT_INDEX.getValue()));
		}
		if (paper instanceof Magazine) {
			((Magazine) paper)
					.setSubscriptIndex(getElementTextContent(paperElement, PaperXmlTag.SUBSCRIPT_INDEX.getValue()));
		}
		return paper;
	}
	
	private static String getElementTextContent(Element element, String elementName) {
		NodeList nList = element.getElementsByTagName(elementName);
		Node node = nList.item(0);
		String text = node.getTextContent();
		return text;
	}	
}
