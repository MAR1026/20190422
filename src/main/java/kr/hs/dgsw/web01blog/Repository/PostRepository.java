package kr.hs.dgsw.web01blog.Repository;

import kr.hs.dgsw.web01blog.Domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
