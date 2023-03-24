package MusicStore;

public class Song {
    private   String songName;
    private   String singerName;
    private Album album;
    private double longOfSing;

    public String getSongName() {
        return songName;
    }

    public void setSongName(String songName) {
        this.songName = songName;
    }

    public String getSingerName() {
        return singerName;
    }

    public void setSingerName(String singerName) {
        this.singerName = singerName;
    }

    public double getLongOfSing() {
        return longOfSing;
    }

    public void setLongOfSing(double longOfSing) {
        this.longOfSing = longOfSing;
    }

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }

    public Song(String songName, String singerName, Album album, double longOfSing) {
        this.songName = songName;
        this.singerName = singerName;
        this.album = album;
        this.longOfSing = longOfSing;
    }

}
