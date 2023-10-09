package com.crio.jukebox.Services;


import java.util.ArrayList;
import java.util.List;
import com.crio.jukebox.Entities.playlist;
import com.crio.jukebox.Entities.playlistStatus;
import com.crio.jukebox.Entities.song;
import com.crio.jukebox.Entities.user;
import com.crio.jukebox.Repositories.IPlaylistRepository;
import com.crio.jukebox.Repositories.ISongRepository;
import com.crio.jukebox.Repositories.IUserRepository;
import com.crio.jukebox.Repositories.UserRepository;

public class PlaylistService implements IPlaylistService{
    private final IPlaylistRepository playlistRepository;
    private final ISongRepository songRepository;
    private final IUserRepository userRepository;
    private final IUserService userService;

    public PlaylistService(IPlaylistRepository playlistRepository, ISongRepository songRepository, IUserRepository userRepository, IUserService userService){
        this.playlistRepository = playlistRepository;
        this.songRepository = songRepository;
        this.userRepository = userRepository;
        this.userService = userService;
    }

    @Override
    public playlist create(String creatorId, String playlistName, String[] songId) {
        final user user = userRepository.findById(creatorId).get();
        final List<song> songs = new ArrayList<>();
        for(String id : songId){
            //add exception here
            song ps = songRepository.findById(id).get();
            songs.add(ps);
        }
        playlist playlist = playlistRepository.save(new playlist(playlistName, songs, user, playlistStatus.NOT_PLAYING));
        userService.addPlaylist(user.getId(), playlist.getId());
        return playlist;
    }

    @Override
    public playlist addSong(String creatorId, String playlistId, String[] songId) {
        playlist playlist = playlistRepository.findById(playlistId).get();
        List<song> songs = playlist.getSongs();

        for(String id : songId){
            song ns = songRepository.findById(id).get();
            //add exception here
            if(!songs.contains(ns)){
                songs.add(ns);
            }
        }
        playlist.modifySongs(songs);
        playlistRepository.save(playlist);
        return playlist;
    }

    @Override
    public playlist deleteSong(String creatorId, String playlistId, String[] songId) {
        playlist playlist = playlistRepository.findById(playlistId).get();
        List<song> songs = playlist.getSongs();

        for(String id : songId){
            song ns = songRepository.findById(id).get();
            //add exception here
            if(songs.contains(ns)){
                songs.remove(ns);
            }
        }
        playlist.modifySongs(songs);
        playlistRepository.save(playlist); 
        return playlist;
    }

    @Override
    public song playPlaylist(String creatorId, String playListId) {
        playlist playlist = playlistRepository.findById(playListId).get();
        List<song> songs = playlist.getSongs(); 
        //add exception here
        playlist.playPlaylist();
        return songs.get(0);
    }
    
}
