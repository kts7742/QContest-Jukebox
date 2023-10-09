package com.crio.jukebox.Commands;

import java.util.List;
import com.crio.jukebox.Entities.playlist;
import com.crio.jukebox.Services.IPlaylistService;

public class ModifyPlaylistCommand implements ICommand{

    private final IPlaylistService playlistService;

    public ModifyPlaylistCommand(IPlaylistService playlistService){
        this.playlistService = playlistService;
    }

    @Override
    public void execute(List<String> tokens) {
        String modifyCommand = tokens.get(1);
        String userId = tokens.get(2);
        String playlistId = tokens.get(3);
        String[] songIds = new String[tokens.size() - 4];
        int k = 0;
        for(int i = 4; i < tokens.size(); i++){
            songIds[k++] = tokens.get(i);  
        }

        if(modifyCommand.equals("ADD-SONG")){
            playlist playlist = playlistService.addSong(userId, playlistId, songIds);
            System.out.println(playlist);
        }else{
            playlist playlist = playlistService.deleteSong(userId, playlistId, songIds);
            System.out.println(playlist);
        }
    }
    
}
