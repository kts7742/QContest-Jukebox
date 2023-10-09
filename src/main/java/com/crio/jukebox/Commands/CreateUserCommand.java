package com.crio.jukebox.Commands;

import java.util.List;
import com.crio.jukebox.Entities.user;
import com.crio.jukebox.Services.IUserService;


public class CreateUserCommand implements ICommand{

    private final IUserService userService;

    public CreateUserCommand(IUserService userService){
        this.userService = userService;
    }

    @Override
    public void execute(List<String> tokens) {
        String userName = tokens.get(1);
        user user = userService.create(userName);
        System.out.println(user);
    }
    
}
