package com.crio.jukebox.Entities;

public class song {
    private String id;
    private final String name;
    private final String genre;
    private final String albumName;
    private final String albumArtist;
    private final String[] artists;

    public song(song song){
        this(song.id, song.name, song.genre, song.albumName, song.albumArtist, song.artists);
    }
    public song(String id, String name, String genre, String albumName, String albumArtist, String[] artists){
        this(name,genre,albumName,albumArtist,artists);
        this.id = id;
        
    }

    public song(String name, String genre, String albumName, String albumArtist, String[] artists){
        this.name = name;
        this.genre = genre;
        this.albumName = albumName;
        this.albumArtist = albumArtist;
        this.artists = artists;
    }

    public String getId(){
        return this.id;
    }
    public String getName(){
        return this.name;
    }
    public String getGenre(){
        return this.genre;
    }
    public String getAlbumName(){
        return this.albumName;
    }
    public String getAlbumArtist(){
        return this.albumArtist;
    }
    public String[] getArtists(){
        return this.artists;
    }
    @Override
    public String toString() {
        String listArtists = "";
        for(String str : artists){
            if(str.equals(artists[artists.length-1])){
                listArtists += str;
                continue;
            }
            listArtists += str + ",";
        }
        return "Current Song Playing" + "\n" + "Song - " + name + "\n" + "Album - " + albumName + "\n" + "Artists - " + listArtists;
    }
}
