package com.crio.jukebox.Services;

import com.crio.jukebox.Entities.user;

public interface IUserService {
    public user create(String name);
    public void addPlaylist(String userId, String playlistId);
    public String deletePlaylist(String userId, String playlistId);
}
