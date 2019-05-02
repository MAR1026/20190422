package kr.hs.dgsw.web01blog.Controller;

import kr.hs.dgsw.web01blog.Domain.User;
import kr.hs.dgsw.web01blog.Protocol.ResponseFormat;
import kr.hs.dgsw.web01blog.Protocol.ResponseType;
import kr.hs.dgsw.web01blog.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/user/readall")
    public ResponseFormat ReadAll() {
        List<User> result = this.userService.ReadAll();
        if(result != null) {
            ResponseFormat responseFormat = new ResponseFormat(ResponseType.USER_GET, result);
            return responseFormat;
        }

        return new ResponseFormat(ResponseType.FAIL, null);
    }

    @GetMapping("/user/read/{account}")
    public ResponseFormat Read(@PathVariable String account) {
        User result = this.userService.Read(account);
        if(result != null) {
            ResponseFormat responseFormat = new ResponseFormat(ResponseType.USER_GET, result, result.getId());
            return responseFormat;
        }
        return new ResponseFormat(ResponseType.FAIL, null);
    }

    @PostMapping("/user/create")
    public ResponseFormat Create(@RequestBody User user) {

        User result = this.userService.Create(user);
        if(result != null) {
            ResponseFormat responseFormat = new ResponseFormat(ResponseType.USER_ADD, result, result.getId());
            return responseFormat;
        }
        return new ResponseFormat(ResponseType.FAIL, null);

    }

    @PutMapping("/user/update")
    public ResponseFormat Update(@RequestBody User user) {

        User result = this.userService.Update(user);
        if(result != null) {
            ResponseFormat responseFormat = new ResponseFormat(ResponseType.USER_UPDATE, result, result.getId());
            return responseFormat;
        }
        return new ResponseFormat(ResponseType.FAIL, null);
    }

    @DeleteMapping("/user/delete")
    public ResponseFormat Delete(@RequestBody User user) {
        boolean result = this.userService.Delete(user);
        if(result) {
            ResponseFormat responseFormat = new ResponseFormat(ResponseType.USER_DELETE, result, user.getId());
            return responseFormat;
        }
        return new ResponseFormat(ResponseType.FAIL, null);
    }

}
