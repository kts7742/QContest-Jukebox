package com.crio.jukebox.Repositories;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import com.crio.jukebox.Entities.user;

public class UserRepository implements IUserRepository {
    private final Map<String, user> userMap;
    private Integer autoIncrement = 0;

    public UserRepository(){
        userMap = new HashMap<String, user>();
    }
    public UserRepository(Map<String, user> userMap){
       this.userMap = userMap;
       this.autoIncrement = userMap.size();
    }

    @Override
    public user save(user entity) {
        if(entity.getId() == null){
            autoIncrement++;
            user u = new user(autoIncrement.toString(), entity.getName());
            userMap.put(u.getId(), u);
            return u;
        }
        userMap.put(entity.getId(), entity);
        return entity;
    }

    @Override
    public Optional<user> findById(String id) {
        return Optional.ofNullable(userMap.get(id));
    }

    @Override
    public boolean existsById(String id) {
        if(userMap.containsKey(id)){
            return true;
           }
           return false;
    }
    
}
