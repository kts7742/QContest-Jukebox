package com.crio.jukebox.Repositories;

import java.util.Optional;
import com.crio.jukebox.Entities.user;

public interface IUserRepository {
    public user save(user entity);
    public Optional<user> findById(String id);
    public boolean existsById(String id);
}
