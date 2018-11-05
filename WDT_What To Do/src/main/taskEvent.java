package main;

public class taskEvent {
	String title,description;
	Object date;

	public taskEvent(Object date, String title, String description) {

		this.date = date;
		this.title = title;
		this.description = description;
		
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title=title;
	}


	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Object getDate() {
		return date;
	}

	public void setDate(Object date) {
		this.date = date;
	}
	
	
	public String toString()
	{
		return this.getDate()+" "+this.getTitle()+" "+this.getDescription();
	}

}


