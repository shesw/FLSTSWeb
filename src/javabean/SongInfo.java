package javabean;

public class SongInfo{
	private String name;
	private String by;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getBy() {
		return by;
	}
	public void setBy(String by) {
		this.by = by;
	}
	public SongInfo(String name, String by) {
		super();
		this.name = name;
		this.by = by;
	}
	
}