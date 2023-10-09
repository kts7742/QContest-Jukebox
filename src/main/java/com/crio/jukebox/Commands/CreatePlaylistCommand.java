package com.crio.jukebox.Commands;

import java.util.List;
import com.crio.jukebox.Entities.playlist;
import com.crio.jukebox.Services.IPlaylistService;

public class CreatePlaylistCommand implements ICommand{

    private final IPlaylistService playlistService;

    public CreatePlaylistCommand(IPlaylistService playlistService){
        this.playlistService = playlistService;
    }



    @Override
    public void execute(List<String> tokens) {
        String userId = tokens.get(1);
        String playlistName = tokens.get(2);
        String[] songIds = new String[tokens.size() - 3];
        int k = 0;
        for(int i = 3; i < tokens.size(); i++){
            songIds[k++] = tokens.get(i);  
        }
        playlist playlist = playlistService.create(userId, playlistName, songIds);
        System.out.println("Playlist ID - " + playlist.getId());
    }
    
}
