package kr.hs.dgsw.web01blog.Service;

import kr.hs.dgsw.web01blog.Domain.User;
import kr.hs.dgsw.web01blog.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User Create(User user) {

        System.out.println(user.getAccount());

        if(this.userRepository.findByAccount(user.getAccount()).isPresent())
            return null;
        else
            return this.userRepository.save(user);
    }

    @Override
    public User Read(User user) {
        Optional<User> found = this.userRepository.findByAccount(user.getAccount());
        if(found.isPresent()){
            return found.get();
        } else
            return null;
    }

    @Override
    public List<User> ReadAll() {
        return this.userRepository.findAll();
    }

    @Override
    public User Update(User user) {
        User found = Read(user);

        if(found != null) {
            found.setName(Optional.ofNullable(user.getName()).orElse(found.getName()));
            found.setEmail(Optional.ofNullable(user.getEmail()).orElse(found.getEmail()));
            found.setPhone(Optional.ofNullable(user.getPhone()).orElse(found.getPhone()));
            found.setProfilePath(Optional.ofNullable(user.getProfilePath()).orElse(found.getProfilePath()));

            return this.userRepository.save(found);
        } else
            return null;
    }

    @Override
    public boolean Delete(User user) {
        User found = Read(user);
        if(found != null){
            this.userRepository.deleteById(found.getId());
            return true;
        }
        return false;
    }
}
