package javabean;

import java.util.ArrayList;
import java.util.List;

public class AlbumInfo {
	
	private String intro;
	private String logoSrc;
	private List<SongInfo> songList;
	
	public String getIntro() {
		return intro;
	}
	public void setIntro(String intro) {
		this.intro = intro;
	}
	public String getLogoSrc() {
		return logoSrc;
	}
	public void setLogoSrc(String logoSrc) {
		this.logoSrc = logoSrc;
	}
	public List<SongInfo> getSongList() {
		return songList;
	}
	public void setSongList(ArrayList<SongInfo> songList) {
		this.songList = songList;
	}
	public AlbumInfo(String intro, String logoSrc, List<SongInfo> songList) {
		super();
		this.intro = intro;
		this.logoSrc = logoSrc;
		this.songList = songList;
	}
	
}

