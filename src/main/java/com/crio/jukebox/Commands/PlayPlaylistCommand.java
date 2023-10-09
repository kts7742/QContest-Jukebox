package com.crio.jukebox.Commands;

import java.util.List;
import com.crio.jukebox.Entities.song;
import com.crio.jukebox.Services.IPlaylistService;

public class PlayPlaylistCommand implements ICommand{

    private final IPlaylistService playlistService;

    public PlayPlaylistCommand(IPlaylistService playlistService){
        this.playlistService = playlistService;
    }

    @Override
    public void execute(List<String> tokens) {
        String userId = tokens.get(1);
        String playlistId = tokens.get(2);

        song song = playlistService.playPlaylist(userId, playlistId);
        System.out.println(song);
    }
    
}
