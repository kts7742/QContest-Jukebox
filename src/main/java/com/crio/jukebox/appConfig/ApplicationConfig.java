package com.crio.jukebox.appConfig;

import com.crio.jukebox.Commands.CommandInvoker;
import com.crio.jukebox.Commands.CreatePlaylistCommand;
import com.crio.jukebox.Commands.CreateUserCommand;
import com.crio.jukebox.Commands.DeletePlaylistCommand;
import com.crio.jukebox.Commands.LoadDataCommand;
import com.crio.jukebox.Commands.ModifyPlaylistCommand;
import com.crio.jukebox.Commands.PlayPlaylistCommand;
import com.crio.jukebox.Commands.PlaySongCommand;
import com.crio.jukebox.Repositories.IPlaylistRepository;
import com.crio.jukebox.Repositories.ISongRepository;
import com.crio.jukebox.Repositories.IUserRepository;
import com.crio.jukebox.Repositories.PlaylistRepository;
import com.crio.jukebox.Repositories.SongRepository;
import com.crio.jukebox.Repositories.UserRepository;
import com.crio.jukebox.Services.IPlaylistService;
import com.crio.jukebox.Services.ISongService;
import com.crio.jukebox.Services.IUserService;
import com.crio.jukebox.Services.PlaylistService;
import com.crio.jukebox.Services.SongService;
import com.crio.jukebox.Services.UserService;

public class ApplicationConfig {
    private final IUserRepository userRepository = new UserRepository();
    private final IPlaylistRepository playlistRepository = new PlaylistRepository();
    private final ISongRepository songRepository = new SongRepository();

    private final IUserService userService = new UserService(userRepository, playlistRepository);
    private final IPlaylistService playlistService = new PlaylistService(playlistRepository, songRepository, userRepository, userService);
    private final ISongService songService = new SongService(userRepository, playlistRepository, songRepository);

    private final CreatePlaylistCommand createPlaylistCommand = new CreatePlaylistCommand(playlistService);
    private final CreateUserCommand createUserCommand = new CreateUserCommand(userService);
    private final DeletePlaylistCommand deletePlaylistCommand = new DeletePlaylistCommand(userService);
    private final LoadDataCommand loadDataCommand = new LoadDataCommand(songService);
    private final ModifyPlaylistCommand modifyPlaylistCommand = new ModifyPlaylistCommand(playlistService);
    private final PlayPlaylistCommand playPlaylistCommand = new PlayPlaylistCommand(playlistService);
    private final PlaySongCommand playSongCommand = new PlaySongCommand(songService);

    private final CommandInvoker commandInvoker = new CommandInvoker();

    public CommandInvoker getCommandInvoker(){
        commandInvoker.register("LOAD-DATA", loadDataCommand);
        commandInvoker.register("CREATE-USER", createUserCommand);
        commandInvoker.register("CREATE-PLAYLIST", createPlaylistCommand);
        commandInvoker.register("DELETE-PLAYLIST", deletePlaylistCommand);
        commandInvoker.register("PLAY-PLAYLIST", playPlaylistCommand);
        commandInvoker.register("MODIFY-PLAYLIST", modifyPlaylistCommand);
        commandInvoker.register("PLAY-SONG", playSongCommand);

        return commandInvoker;
    }

}
