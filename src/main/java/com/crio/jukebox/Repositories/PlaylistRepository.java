package com.crio.jukebox.Repositories;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import com.crio.jukebox.Entities.playlist;

public class PlaylistRepository implements IPlaylistRepository{
    private final Map<String, playlist> playlistMap;
    private Integer autoIncrement = 0;

    public PlaylistRepository(){
        this.playlistMap = new HashMap<String, playlist>(); 
    }
    public PlaylistRepository(Map<String, playlist> playlistMap){
        this.playlistMap = playlistMap;
        this.autoIncrement = playlistMap.size();
    }

    @Override
    public playlist save(playlist entity) {
        if(entity.getId() == null){
            autoIncrement++;
            playlist p = new playlist(autoIncrement.toString(), entity.getName(), entity.getSongs(), entity.getCreator(), entity.getPlaylistStatus());
            playlistMap.put(p.getId(), p);
            return p;
        }
        playlistMap.put(entity.getId(), entity);
        return entity;
    }

    @Override
    public List<playlist> findAll() {
        return playlistMap.values().stream().collect(Collectors.toList());
    }

    @Override
    public Optional<playlist> findById(String id) {
        return Optional.ofNullable(playlistMap.get(id));
    }

    @Override
    public boolean existsById(String id) {
        if(playlistMap.containsKey(id)){
            return true;
        }
        return false;
    }

    @Override
    public void deleteById(String id) {
        if(playlistMap.containsKey(id)){
            playlistMap.remove(id);
        }
    }
    
}
