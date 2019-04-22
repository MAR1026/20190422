package kr.hs.dgsw.web01blog.Service;

import kr.hs.dgsw.web01blog.Domain.Post;

import java.util.List;

public interface PostService {
    Post Create(Post post);
    Post Read(Post post);
    List<Post> ReadAll();
    Post Update(Post post);
    boolean Delete(Post post);


}
