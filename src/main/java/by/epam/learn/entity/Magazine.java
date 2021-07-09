package by.epam.learn.entity;

import java.time.LocalDate;

public class Magazine extends Paper{
	private String subscriptIndex;
	
	public Magazine() {
		super();
	}

	public Magazine(String id, String theme, String title, LocalDate date, boolean monthly, boolean color,
			int numberPages,  boolean glossy, String subscriptIndex) {
		super(id, theme, title, date, monthly, color, numberPages, glossy);
		this.subscriptIndex = subscriptIndex;
	}

	public String getSubscriptIndex() {
		return subscriptIndex;
	}

	public void setSubscriptIndex(String subscriptIndex) {
		this.subscriptIndex = subscriptIndex;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((subscriptIndex == null) ? 0 : subscriptIndex.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Magazine other = (Magazine) obj;
		if (subscriptIndex == null) {
			if (other.subscriptIndex != null)
				return false;
		} else if (!subscriptIndex.equals(other.subscriptIndex))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(super.toString());
		sb.append("\nSubscript index: ").append(subscriptIndex);
		return sb.toString();
	}
}
