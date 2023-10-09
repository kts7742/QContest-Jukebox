package com.crio.jukebox.Commands;

import java.util.List;
import com.crio.jukebox.Entities.song;
import com.crio.jukebox.Services.ISongService;

public class PlaySongCommand implements ICommand{

    private final ISongService songService;

    public PlaySongCommand(ISongService songService){
        this.songService = songService;
    }

    @Override
    public void execute(List<String> tokens) {
        String userId = tokens.get(1);
        String playCommand = tokens.get(2);

        song song = null;

        if(playCommand.equals("NEXT")){

             song = songService.playNext(userId);

        }else if(playCommand.equals("BACK")){

             song = songService.playPrevious(userId);

        }else{

             song = songService.playById(userId, playCommand);

        }

        System.out.println(song);
    }
    
    
}
