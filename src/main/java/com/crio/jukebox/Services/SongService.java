package com.crio.jukebox.Services;


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import com.crio.jukebox.Entities.playlist;
import com.crio.jukebox.Entities.playlistStatus;
import com.crio.jukebox.Entities.song;
import com.crio.jukebox.Entities.user;
import com.crio.jukebox.Repositories.IPlaylistRepository;
import com.crio.jukebox.Repositories.ISongRepository;
import com.crio.jukebox.Repositories.IUserRepository;

public class SongService implements ISongService{
    private final IUserRepository userRepository;
    private final IPlaylistRepository playlistRepository;
    private final ISongRepository songRepository;

    public SongService(IUserRepository userRepository, IPlaylistRepository playlistRepository, ISongRepository songRepository){
        this.userRepository = userRepository;
        this.playlistRepository = playlistRepository;
        this.songRepository = songRepository;
    }

    @Override
    public void loadData(String file) {
        String line = "", delimeter = ",";

        try{
            BufferedReader reader = new BufferedReader(new FileReader(file));
            line = reader.readLine();
            while(line != null){
                String[] songData = line.split(delimeter);
            
                song song = new song(songData[0], songData[1], songData[2], songData[3], songData[4].split("#"));
                songRepository.save(song);
            }
            reader.close();
        }catch(FileNotFoundException e){
            e.printStackTrace();
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public song playById(String userId, String songId) {
        user user = userRepository.findById(userId).get();
        playlist activePlaylist = null;
        List<playlist> playlists = user.getPlaylist();
        for(playlist p : playlists){
            if(p.getPlaylistStatus().equals(playlistStatus.PLAYING)){
                activePlaylist = p;
                break;
            }
        }
        List<song> songs = activePlaylist.getSongs();
        song songToPlay = null;
        int idx = 0;
        for(song s : songs){
            if(s.getId().equals(songId)){
                activePlaylist.setNumOfSongPlaying(idx);
                songToPlay = s;
            }
            idx++;
        }
        //add exception here
        return songToPlay;
    }

    @Override
    public song playNext(String userId) {
       user user = userRepository.findById(userId).get();
       playlist activePlaylist = null;
        List<playlist> playlists = user.getPlaylist();
        for(playlist p : playlists){
            if(p.getPlaylistStatus().equals(playlistStatus.PLAYING)){
                activePlaylist = p;
                break;
            }
        }
        int numOfSongPlaying = activePlaylist.getNumOfSongPlaying();
        List<song> songs = activePlaylist.getSongs();
        song songToPlay = null;
        int idx = 0;
        for(song s : songs){
            if(idx == numOfSongPlaying){
                if(idx == songs.size()-1){
                    songToPlay = songs.get(0);
                    activePlaylist.setNumOfSongPlaying(0);
                }else{
                    activePlaylist.setNumOfSongPlaying(idx+1);
                    songToPlay = songs.get(idx+1);
                }
                break;
            }
            idx++;
        }
        return songToPlay;
    }

    @Override
    public song playPrevious(String userId) {
        user user = userRepository.findById(userId).get();
        playlist activePlaylist = null;
         List<playlist> playlists = user.getPlaylist();
         for(playlist p : playlists){
             if(p.getPlaylistStatus().equals(playlistStatus.PLAYING)){
                 activePlaylist = p;
                 break;
             }
         }
         int numOfSongPlaying = activePlaylist.getNumOfSongPlaying();
         List<song> songs = activePlaylist.getSongs();
         song songToPlay = null;
         int idx = 0;
         for(song s : songs){
             if(idx == numOfSongPlaying){
                 if(idx == 0){
                     songToPlay = songs.get(songs.size()-1);
                     activePlaylist.setNumOfSongPlaying(songs.size()-1);
                 }else{
                     activePlaylist.setNumOfSongPlaying(idx-1);
                     songToPlay = songs.get(idx-1);
                 }
                 break;
             }
             idx++;
         }
         return songToPlay;
    }
    
}
