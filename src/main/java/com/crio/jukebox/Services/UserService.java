package com.crio.jukebox.Services;


import com.crio.jukebox.Entities.playlist;
import com.crio.jukebox.Entities.user;
import com.crio.jukebox.Repositories.IPlaylistRepository;
import com.crio.jukebox.Repositories.IUserRepository;

public class UserService implements IUserService{

    private final IUserRepository userRepository;
    private final IPlaylistRepository playlistRepository;

    public UserService(IUserRepository userRepository, IPlaylistRepository playlistRepository){
        this.userRepository = userRepository;
        this.playlistRepository = playlistRepository;
    }

    @Override
    public user create(String name) {
        user user = new user(name);
        return userRepository.save(user);
    }

    @Override
    public void addPlaylist(String userId, String playlistId) {
        user user = userRepository.findById(userId).get();
        playlist playlist = playlistRepository.findById(playlistId).get();
        user.addPlaylist(playlist);
        userRepository.save(user);
    }

    @Override
    public String deletePlaylist(String userId, String playlistId) {
        user user = userRepository.findById(userId).get();
        //add exception here
        playlist playlist = playlistRepository.findById(playlistId).get();
        user.deletePlaylist(playlist);
        userRepository.save(user);
        return "Delete Successful";
    }

    
    
}
