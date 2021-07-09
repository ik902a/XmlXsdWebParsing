package by.epam.learn.entity;

import java.time.LocalDate;

public class Booklet extends Paper {

	public Booklet() {
		super();
	}

	public Booklet(String id, String theme, String title, LocalDate date, boolean monthly, boolean color,
			int numberPages, boolean glossy) {
		super(id, theme, title, date, monthly, color, numberPages, glossy);
	}

	@Override
	public int hashCode() {
		return super.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(super.toString());
		return sb.toString();
	}
}
