package by.epam.learn.handler;

public enum PaperXmlTag {
	PAPERS("papers"),
	ID("id"),
	THEME("theme"),
	NEWSPAPER("newspaper"),
	MAGAZINE("magazine"),
	BOOKLET("booklet"),
	TITLE("title"),
	DATE("date"),
	IS_MONTHLY("is-monthly"),
	IS_COLOR("is-color"),
	NUMBER_PAGES("number-pages"),
	IS_GLOSSY("is-glossy"),
	SUBSCRIPT_INDEX("subscript-index");
	
	private String value;
	
	PaperXmlTag(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
}
