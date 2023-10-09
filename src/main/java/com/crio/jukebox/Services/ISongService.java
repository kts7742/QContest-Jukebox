package com.crio.jukebox.Services;

import com.crio.jukebox.Entities.song;

public interface ISongService {
    public void loadData(String file);
    public song playById(String userId, String songId);
    public song playNext(String userId);
    public song playPrevious(String userId);
}
