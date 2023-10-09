package com.crio.jukebox.Repositories;

import java.util.List;
import java.util.Optional;
import com.crio.jukebox.Entities.playlist;

public interface IPlaylistRepository {
    public playlist save(playlist entity);
    public List<playlist> findAll();
    public Optional<playlist> findById(String id);
    public boolean existsById(String id);
    public void deleteById(String id);
}
