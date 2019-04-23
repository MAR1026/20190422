package kr.hs.dgsw.web01blog.Controller;

import kr.hs.dgsw.web01blog.Domain.Post;
import kr.hs.dgsw.web01blog.Protocol.ResponseFormat;
import kr.hs.dgsw.web01blog.Protocol.ResponseType;
import kr.hs.dgsw.web01blog.Service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PostController {

    @Autowired
    private PostService postService;

    @GetMapping("/post/readall")
    public List<Post> ReadAll() {
        return this.postService.ReadAll();
    }

    @GetMapping("/post/read/{userid}")
    public ResponseFormat Read(@PathVariable Long userId) {

        Post result = this.postService.Read(userId);

        if(result != null) {
            ResponseFormat responseFormat = new ResponseFormat(ResponseType.POST_GET, result);
            return responseFormat;
        }

        return new ResponseFormat(ResponseType.FAIL, null);
    }

    @PostMapping("/post/create")
    public ResponseFormat Create(@RequestBody Post post) {

        Post result = this.postService.Create(post);
        if(result != null) {
            ResponseFormat responseFormat = new ResponseFormat(ResponseType.POST_ADD, result);
            return responseFormat;
        }

        return new ResponseFormat(ResponseType.FAIL, null);
    }

    @PutMapping("/post/update")
    public ResponseFormat Update(@RequestBody Post post) {

        Post result = this.postService.Update(post);
        if(result != null) {
            ResponseFormat responseFormat = new ResponseFormat(ResponseType.POST_UPDATE, result);
            return responseFormat;
        }

        return new ResponseFormat(ResponseType.FAIL, null);
    }

    @DeleteMapping("/post/delete/{userid}")
    public ResponseFormat Delete(@PathVariable Long postId) {
        boolean result = this.postService.Delete(postId);
        if(result) {
            ResponseFormat responseFormat = new ResponseFormat(ResponseType.POST_DELETE, result);
            return responseFormat;
        }

        return new ResponseFormat(ResponseType.FAIL, null);
    }

}
