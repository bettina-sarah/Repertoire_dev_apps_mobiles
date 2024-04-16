package com.example.atelier4_exoplayer;

public class Chanson {


    private String id;
    private String title;
    private String album;
    private String artist;
    private String genre;
    private String source;
    private String image;
    private int trackNumber;
    private int totalTrackCount;
    private int duration;
    private String site;


    public Chanson(String id, String title, String album, String artist, String genre, String source, String image, int trackNumber, int totalTrackCount, int duration, String site) {
        this.id = id;
        this.title = title;
        this.album = album;
        this.artist = artist;
        this.genre = genre;
        this.source = source;
        this.image = image;
        this.trackNumber = trackNumber;
        this.totalTrackCount = totalTrackCount;
        this.duration = duration;
        this.site = site;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getTrackNumber() {
        return trackNumber;
    }

    public void setTrackNumber(int trackNumber) {
        this.trackNumber = trackNumber;
    }

    public int getTotalTrackCount() {
        return totalTrackCount;
    }

    public void setTotalTrackCount(int totalTrackCount) {
        this.totalTrackCount = totalTrackCount;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }
}