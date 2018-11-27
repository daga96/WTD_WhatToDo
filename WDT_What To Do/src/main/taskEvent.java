package main;

public class taskEvent {
	String title,description,priority;
	Object date;


	public taskEvent(Object date, String title, String description, String priority) {

		this.date = date;
		this.title = title;
		this.description = description;
		this.priority=priority;
	}

	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
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
	
	
}


