package com.crio.jukebox.Repositories;

import java.util.List;
import java.util.Optional;
import com.crio.jukebox.Entities.song;

public interface ISongRepository {
    public song save(song entity);
    public List<song> findAll();
    public Optional<song> findById(String id);
    public boolean existsById(String id);
}
