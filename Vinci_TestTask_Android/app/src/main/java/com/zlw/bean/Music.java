package com.zlw.bean;

public class Music {

	private String song;
	private String singer;

	public Music() {
	}

	public String getSong() {
		return song;
	}

	public void setSong(String song) {
		this.song = song;
	}

	public String getSinger() {
		return singer;
	}

	public void setSinger(String singer) {
		this.singer = singer;
	}

	public Music(String song, String singer) {
		super();
		this.song = song;
		this.singer = singer;
	}

}
