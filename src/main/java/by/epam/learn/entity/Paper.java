package by.epam.learn.entity;

import java.time.LocalDate;

public abstract class Paper {
	private String id;
	private String theme;
	private String title;
	private LocalDate date;
	private boolean monthly;
	private boolean color;
	private int numberPages;
	private boolean glossy;

	public Paper() {
	}
	
	public Paper(String id, String theme, String title, LocalDate date, boolean monthly, boolean color,
			int numberPages, boolean glossy) {
		super();
		this.id = id;
		this.theme = theme;
		this.title = title;
		this.date = date;
		this.monthly = monthly;
		this.color = color;
		this.numberPages = numberPages;
		this.glossy = glossy;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTheme() {
		return theme;
	}

	public void setTheme(String theme) {
		this.theme = theme;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}
	
	public void setDate(String stringDate) {
		LocalDate date = LocalDate.parse(stringDate);
		this.date = date;
	}

	public boolean isMonthly() {
		return monthly;
	}

	public void setMonthly(boolean monthly) {
		this.monthly = monthly;
	}

	public void setMonthly(String stringMonthly) {
		boolean monthly = Boolean.parseBoolean(stringMonthly);
		this.monthly = monthly;
	}

	public boolean isColor() {
		return color;
	}

	public void setColor(boolean color) {
		this.color = color;
	}
	
	public void setColor(String stringColor) {
		boolean color = Boolean.parseBoolean(stringColor);
		this.monthly = color;
}

	public int getNumberPages() {
		return numberPages;
	}

	public void setNumberPages(int numberPages) {
		this.numberPages = numberPages;
	}
	
	public void setNumberPages(String stringNumberPages) {
		int numberPages = Integer.parseInt (stringNumberPages);
		this.numberPages = numberPages;
	}
	
	public boolean isGlossy() {
		return glossy;
	}

	public void setGlossy(boolean glossy) {
		this.glossy = glossy;
	}
	
	public void setGlossy(String stringGlossy) {
		boolean glossy = Boolean.parseBoolean(stringGlossy);
		this.glossy = glossy;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (color ? 1231 : 1237);
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + (glossy ? 1231 : 1237);
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + (monthly ? 1231 : 1237);
		result = prime * result + numberPages;
		result = prime * result + ((theme == null) ? 0 : theme.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Paper other = (Paper) obj;
		if (color != other.color)
			return false;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (glossy != other.glossy)
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (monthly != other.monthly)
			return false;
		if (numberPages != other.numberPages)
			return false;
		if (theme == null) {
			if (other.theme != null)
				return false;
		} else if (!theme.equals(other.theme))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append(getClass().getName());
		sb.append("\nID: ").append(id);
		sb.append("\nTheme: ").append(theme);
		sb.append("\nTitle: ").append(title);
		sb.append("\nDate: ").append(date);
		sb.append("\nIs monthly: ").append(monthly);
		sb.append("\nIs color: ").append(color);
		sb.append("\nNumber of pages: ").append(numberPages);
		sb.append("\nIs glossy: ").append(glossy);
		return sb.toString();
	}
}
