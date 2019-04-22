package kr.hs.dgsw.web01blog.Service;

import kr.hs.dgsw.web01blog.Domain.User;

import java.util.List;

public interface UserService {

    User Create(User user);
    User Read(User user);
    List<User> ReadAll();
    User Update(User user);
    boolean Delete(User user);

}
