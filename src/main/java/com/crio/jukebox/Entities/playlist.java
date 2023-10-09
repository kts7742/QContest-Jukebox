package com.crio.jukebox.Entities;

import java.util.List;

public class playlist {
    private String id;
    private final String name;
    private List<song> songs;
    private final user creator;
    private playlistStatus playlistStatus;
    private Integer numOfSongPlaying = null;

    public playlist(playlist playlist){
        this(playlist.id, playlist.name, playlist.songs, playlist.creator, playlist.playlistStatus);
    }

    public playlist(String id, String name, List<song> songs, user creator, playlistStatus playlistStatus){
        this(name, songs, creator, playlistStatus);
        this.id = id;
    }
    public playlist(String name, List<song> songs, user creator, playlistStatus playlistStatus){
        this.name = name;
        this.songs = songs;
        this.creator = creator;
        this.playlistStatus = playlistStatus;
    }

    public Integer getNumOfSongPlaying(){
        return this.numOfSongPlaying;
    }

    public void setNumOfSongPlaying(Integer numOfSongPlaying){
        this.numOfSongPlaying = numOfSongPlaying;
    }
    
    public String getId(){
        return this.id;
    }
    public String getName(){
        return this.name;
    }
    public List<song> getSongs(){
        return this.songs;
    }
    public void modifySongs(List<song> songs){
        this.songs = songs;
    }
    public user getCreator(){
        return this.creator;
    }
    public playlistStatus getPlaylistStatus() {
        return this.playlistStatus;
    }
    public void playPlaylist(){
        playlistStatus = com.crio.jukebox.Entities.playlistStatus.PLAYING;
        numOfSongPlaying = 0;
    }

    @Override
    public String toString() {
        String songIds = "";
        for(song s : songs){
            songIds += s.getId()+" ";
        }
        return "Playlist ID - " + id + "\n" + "Playlist Name - " + name + "\n" + "Song IDs - " + songIds;
    }

}
