package test.epam.learn.builder;

import static org.testng.Assert.assertEquals;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import by.epam.learn.builder.AbstractPapersBuilder;
import by.epam.learn.builder.PaperBuilderFactory;
import by.epam.learn.entity.Booklet;
import by.epam.learn.entity.Magazine;
import by.epam.learn.entity.Newspaper;
import by.epam.learn.entity.Paper;
import by.epam.learn.exception.XmlException;

public class PapersBuilderTest {
	private static final String TEST_FILE = "data_xml/papers_test.xml";
	AbstractPapersBuilder builderSax;
	AbstractPapersBuilder builderDom;
	AbstractPapersBuilder builderStax;
	Set<Paper> expectedPapers;

	@BeforeClass
	public void beforeClass() throws XmlException {
		expectedPapers = new HashSet<Paper>();
		expectedPapers.add(new Newspaper("p001", "news", "Советская Белоруссия", LocalDate.parse("2021-01-30"), true, true, 30, false, "00001"));
		expectedPapers.add(new Magazine("p555", "news", "Time", LocalDate.parse("2021-01-29"), false, true, 100, true, "555055"));
		expectedPapers.add(new Booklet("p666", "nature", "Stop pollution", LocalDate.parse("2021-01-15"), false, true, 2, true));	
		expectedPapers.add(new Newspaper("p069", "news", "Die Zeit", LocalDate.parse("2021-01-30"), true, true, 26, false, "69001"));
		expectedPapers.add(new Magazine("p557", "people", "Forbes", LocalDate.parse("2021-01-25"), false, true, 150, true, "777077"));
		}
	
	@AfterClass
	public void afterClass() {
		builderSax = null;
		builderDom = null;
		builderStax = null;
		expectedPapers = null;
	}
	
	@Test()
	public void testPaperDomBuilder() throws XmlException {
		builderDom = PaperBuilderFactory.createPaperBuilder("dom");
		builderDom.buildSetPapers(TEST_FILE);
		Set<Paper> actualPapers = builderDom.getPapers();
		assertEquals(actualPapers, expectedPapers);		
	}
	
	@Test()
	public void testPaperSaxBuilder() throws XmlException {
		builderSax = PaperBuilderFactory.createPaperBuilder("sax");
		builderSax.buildSetPapers(TEST_FILE);
		Set<Paper> actualPapers = builderSax.getPapers();
		assertEquals(actualPapers, expectedPapers);
	}
	
	@Test()
	public void testPaperStaxBuilder() throws XmlException {
		builderStax = PaperBuilderFactory.createPaperBuilder("stax");
		builderStax.buildSetPapers(TEST_FILE);
		Set<Paper> actualPapers = builderStax.getPapers();
		assertEquals(actualPapers, expectedPapers);
	}
}
