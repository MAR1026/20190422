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
    public ResponseFormat ReadAll() {

        List<Post> result = this.postService.ReadAll();
        if(result != null) {
            ResponseFormat responseFormat = new ResponseFormat(ResponseType.POST_GET, result, "전체 글 목록");
            return  responseFormat;
        }

        return new ResponseFormat(ResponseType.FAIL, null);
    }


    @GetMapping("/post/read/{id}")
    public ResponseFormat Read(@PathVariable Long id) {

        Post result = this.postService.Read(id);
        if(result != null) {
            ResponseFormat responseFormat = new ResponseFormat(ResponseType.POST_GET, result);
            return responseFormat;
        }

        return new ResponseFormat(ResponseType.FAIL, null);
    }

    @GetMapping("post/readByAccount/{account}")
    public ResponseFormat ReadByAccount(@PathVariable String account) {
        Post result = this.postService.ReadByAccount(account);
        if(result != null) {
            ResponseFormat responseFormat = new ResponseFormat(ResponseType.POST_GET, result, "유저 아이디로 포스트 검색");
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

    @DeleteMapping("/post/delete/{postid}")
    public ResponseFormat Delete(@PathVariable Long postid) {
        boolean result = this.postService.Delete(postid);
        if(result) {
            ResponseFormat responseFormat = new ResponseFormat(ResponseType.POST_DELETE, result);
            return responseFormat;
        }

        return new ResponseFormat(ResponseType.FAIL, null);
    }

}
