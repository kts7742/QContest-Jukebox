package com.crio.jukebox.Entities;

import java.util.ArrayList;
import java.util.List;

public class user {
    private  String id;
    private final String name;
    private List<playlist> playlist;

    public user(user user){
        this(user.id, user.name, user.playlist);
    }
    public user(String id, String name, List<playlist> playlist){
        this(id, name);
        // this.id = id;
        this.playlist = playlist;
    }
    public user(String id, String name){
        this(name);
        this.id = id;
        this.playlist = new ArrayList<>();
    }
    public user(String name){
        this.name = name;
        // this.playlist = new ArrayList<>();
    }

    public String getId(){
        return this.id;
    }
    public String getName(){
        return this.name;
    }
    public List<playlist> getPlaylist(){
        return this.playlist;
    }
    public void addPlaylist(playlist playlist){
        this.playlist.add(playlist);
    }
    public void deletePlaylist(playlist playlist){
        this.playlist.removeIf(c -> c.getId() == playlist.getId());
    }
    public boolean checkIfPlaylistExist(playlist playlist){
        if(this.playlist.contains(playlist)){
            return true;
        }
        return false;
    }
    @Override
    public String toString() {
        return id + " " + name ;
    }
}
