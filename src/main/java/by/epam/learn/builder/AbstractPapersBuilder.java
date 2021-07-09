package by.epam.learn.builder;

import java.util.HashSet;
import java.util.Set;

import by.epam.learn.entity.Paper;
import by.epam.learn.exception.XmlException;

public abstract class AbstractPapersBuilder {
	protected Set<Paper> papers;
	
	public AbstractPapersBuilder() {
		papers = new HashSet<Paper>();
	}
	
	public AbstractPapersBuilder(Set<Paper> papers) {
		this.papers = papers;
	}
	
	public Set<Paper> getPapers() {
		return papers;
	}
	
	public abstract void buildSetPapers(String filename) throws XmlException;
}
