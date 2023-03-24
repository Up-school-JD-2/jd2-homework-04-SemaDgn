package MusicStore;

import java.time.Year;

public class Album {
    private String albumName;
    private String singerName;
    private Year releaseDate;
    private double price;

    public String getAlbumName() {
        return albumName;
    }

    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }

    public String getSingerName() {
        return singerName;
    }

    public void setSingerName(String singerName) {
        this.singerName = singerName;
    }

    public Year getReleaseDate() {
        return releaseDate;
    }


    public void setReleaseDate(Year releaseDate) {
        this.releaseDate = releaseDate;
    }
    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Album(String albumName, String singerName, Year releaseDate, double price) {
        this.albumName = albumName;
        this.singerName = singerName;
        this.releaseDate = releaseDate;
        this.price = price;
    }

}
