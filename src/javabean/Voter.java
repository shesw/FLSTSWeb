package javabean;

public class Voter {

	private String name;
	private String[] days;
	public Voter(String name, String[] days) {
		// TODO Auto-generated constructor stub
		this.name = name;
		this.days = days;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String[] getDays() {
		return days;
	}
	public void setDays(String[] days) {
		this.days = days;
	}
	
}
