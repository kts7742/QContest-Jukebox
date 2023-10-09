package com.crio.jukebox.Commands;

import java.util.List;
import com.crio.jukebox.Services.IUserService;

public class DeletePlaylistCommand implements ICommand{

    private final IUserService userService;

    public DeletePlaylistCommand(IUserService userService){
        this.userService = userService;
    }

    @Override
    public void execute(List<String> tokens) {
        String userId = tokens.get(1);
        String playlistId = tokens.get(2);

        userService.deletePlaylist(userId, playlistId);
        System.out.println("Delete Successful");
    }
    
}
