package com.crio.jukebox.Commands;

import java.util.List;
import com.crio.jukebox.Services.ISongService;

public class LoadDataCommand implements ICommand{

    private final ISongService songService;

    public LoadDataCommand(ISongService songService){
        this.songService = songService;
    }

    @Override
    public void execute(List<String> tokens) {
        String fileName = tokens.get(1);
        String filePath = "/home/crio-user/workspace/kartikeysoni7742-ME_OBJECT_MODELING_V2/" + fileName;

        try{
            songService.loadData(filePath);
            System.out.println("Songs Loaded successfully");
        }catch(RuntimeException e){
            System.out.println(e.getMessage());
        }

    }
    
}
