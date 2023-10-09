package com.crio.jukebox.Repositories;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import com.crio.jukebox.Entities.song;

public class SongRepository implements ISongRepository{
    private final Map<String, song> songMap;
    private Integer autoIncrement = 0;

    public SongRepository(){
        this.songMap = new HashMap<String, song>();
    }

    public SongRepository(Map<String, song> songMap){
        this.songMap = songMap;
        this.autoIncrement = songMap.size(); 
    }

    @Override
    public song save(song entity) {
        if(entity.getId() == null){
            autoIncrement++;
            song s = new song(autoIncrement.toString(), entity.getName(), entity.getGenre(), 
            entity.getAlbumName(), entity.getAlbumArtist(), entity.getArtists());
            songMap.put(s.getId(), s);
            return s;
        }
        songMap.put(entity.getId(), entity);
        return entity;
    }

    @Override
    public List<song> findAll() {
        return songMap.values().stream().collect(Collectors.toList());
    }

    @Override
    public Optional<song> findById(String id) {
        return Optional.ofNullable(songMap.get(id));
    }

    @Override
    public boolean existsById(String id) {
        if(songMap.get(id) == null){
            return false;
        }
        return true;
    }
    
}
