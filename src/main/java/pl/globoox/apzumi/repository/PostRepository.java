package pl.globoox.apzumi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.globoox.apzumi.model.Post;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {

    List<Post> getPostByTitle(String title);
    Post getPostByid(int id);
}
