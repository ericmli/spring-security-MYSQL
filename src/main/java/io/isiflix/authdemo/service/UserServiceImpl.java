package io.isiflix.authdemo.service;

import io.isiflix.authdemo.dto.LoginDTO;
import io.isiflix.authdemo.model.User;
import io.isiflix.authdemo.repo.UserRepo;
import io.isiflix.authdemo.security.IsiToken;
import io.isiflix.authdemo.security.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserRepo repo;

    @Override
    public User create(User usuario) {
        // TODO Auto-generated method stub
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String novaSenha = encoder.encode(usuario.getPassword());
        usuario.setPassword(novaSenha);
        return repo.save(usuario);
    }

    @Override
    public IsiToken login(LoginDTO dto) {
        User res = repo.findByLogin(dto.login());
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        if(encoder.matches(dto.password(), res.getPassword())){
            return TokenUtil.encode(res);
        }
        return null;
    }
}


