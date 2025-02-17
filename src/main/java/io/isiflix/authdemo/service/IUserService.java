package io.isiflix.authdemo.service;

import io.isiflix.authdemo.dto.LoginDTO;
import io.isiflix.authdemo.model.User;
import io.isiflix.authdemo.security.IsiToken;

public interface IUserService {
    public User create(User usuario);
    public IsiToken login(LoginDTO dto);
}
