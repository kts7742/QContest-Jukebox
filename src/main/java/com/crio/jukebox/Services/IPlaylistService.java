package com.crio.jukebox.Services;

import com.crio.jukebox.Entities.playlist;
import com.crio.jukebox.Entities.song;

public interface IPlaylistService {
    public playlist create(String creatorId, String playlistName, String[] songId);
    public playlist addSong(String creatorId, String playlistId, String[] songId);
    public playlist deleteSong(String creatorId, String playlistId, String[] songId);
    public song playPlaylist(String creatorId, String playListId);
}
