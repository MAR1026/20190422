package kr.hs.dgsw.web01blog.Controller;

import kr.hs.dgsw.web01blog.Domain.Post;
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

    @GetMapping("/post/read")
    public Post Read(@RequestBody Post post) { return this.postService.Read(post); }

    @PostMapping("/post/create")
    public Post Create(@RequestBody Post post) { return this.postService.Create(post); }

    @PutMapping("/post/update")
    public Post Update(@RequestBody Post post) { return this.postService.Update(post); }

    @DeleteMapping("/post/delete")
    public boolean Delete(@RequestBody Post post) { return this.postService.Delete(post); }

}
