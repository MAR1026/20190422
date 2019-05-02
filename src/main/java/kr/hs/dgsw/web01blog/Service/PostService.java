package kr.hs.dgsw.web01blog.Service;

import kr.hs.dgsw.web01blog.Domain.Post;

import java.util.List;

public interface PostService {
    Post Create(Post post);
    Post Read(Long id);
    Post ReadByAccount(String userId);
    List<Post> ReadAll();
    Post Update(Post post);
    boolean Delete(Long postId);


}
