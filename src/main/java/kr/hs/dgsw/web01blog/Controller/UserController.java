package kr.hs.dgsw.web01blog.Controller;

import kr.hs.dgsw.web01blog.Domain.User;
import kr.hs.dgsw.web01blog.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/user/readall")
    public List<User> ReadAll() {
        return this.userService.ReadAll();
    }

    @GetMapping("/user/read")
    public User Read(@RequestBody User user) { return this.userService.Read(user); }

    @PostMapping("/user/create")
    public User Create(@RequestBody User user) { return this.userService.Create(user); }

    @PutMapping("/user/update")
    public User Update(@RequestBody User user) { return this.userService.Update(user); }

    @DeleteMapping("/user/delete")
    public boolean Delete(@RequestBody User user) { return this.userService.Delete(user); }

}
