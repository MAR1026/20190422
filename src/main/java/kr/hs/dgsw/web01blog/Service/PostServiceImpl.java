package kr.hs.dgsw.web01blog.Service;

import kr.hs.dgsw.web01blog.Domain.Post;
import kr.hs.dgsw.web01blog.Repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository postRepository;

    @Override
    public Post Create(Post post) {
        return this.postRepository.save(post);
    }

    @Override
    public Post Read(Post post) {
        Optional<Post> found = this.postRepository.findById(post.getId());
        if(found.isPresent()){
            return found.get();
        } else
            return null;
    }

    @Override
    public List<Post> ReadAll() {
        return this.postRepository.findAll();
    }

    @Override
    public Post Update(Post post) {
        Post found = Read(post);

        if(found != null) {
            found.setTitle(Optional.ofNullable(post.getTitle()).orElse(found.getTitle()));
            found.setContent(Optional.ofNullable(post.getContent()).orElse(found.getContent()));
            found.setPicturePath(Optional.ofNullable(post.getPicturePath()).orElse(found.getPicturePath()));

            return this.postRepository.save(found);
        } else
            return null;
    }

    @Override
    public boolean Delete(Post post) {
        Post found = Read(post);
        if(found != null){
            this.postRepository.deleteById(found.getId());
            return true;
        }
        return false;
    }
}
