package pl.globoox.apzumi;


import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;
import pl.globoox.apzumi.exception.ResourceNotFoundException;
import pl.globoox.apzumi.model.Post;
import pl.globoox.apzumi.repository.PostRepository;
import pl.globoox.apzumi.service.PostService;

import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class PostServiceTest {

    @Autowired
    private PostService postService;

    @Test
    public void shouldAddPost() {
        // Create post
        Post aPost = new Post();
        aPost.setTitle("Test Title");
        aPost.setBody("Test Body");

        // Test add post
        Post addedPost = postService.savePost(aPost);

        // Verify
        Assert.assertNotNull(aPost);
        Assert.assertNotNull(addedPost);
        Assert.assertNotSame(aPost, addedPost);
    }


    @Test
    public void shouldDeletePost() {
        // Get random post from database
        Post post = postService.getPostByID(1);

        // Test remove post
        postService.deletePostByID(post.getId());

        // Verify
        Assert.assertNotNull(post);
        Assertions.assertThatExceptionOfType(ResourceNotFoundException.class);
    }


    @Test
    public void shouldEditTitleOrBody() {
        // Get random post from database
        Post post = postService.getPostByID(1);

        // Test edit post
        Post editedPost = postService.editPostByID(post.getId(), "New title", "New body");

        // Verify
        Assert.assertNotNull(post);
        Assert.assertNotNull(editedPost);
        Assert.assertEquals("New title", editedPost.getTitle());
    }


    @Test
    public void shouldGetPostByTitle() {
        // Create post
        Post addPost = new Post();
        addPost.setTitle("Test Title");
        addPost.setBody("Test Body");
        postService.savePost(addPost);

        // Test get post
        List<Post> findedPosts = postService.getPostsByTitle("Test Title");

        // Verify
        Assert.assertNotNull(addPost);
        Assert.assertNotNull(findedPosts);
        for (Post post : findedPosts) {
            Assert.assertEquals("Test Title", post.getTitle());
        }
    }


    @Test
    public void shouldGetPostByID() {
        // Create post
        Post addPost = new Post();
        addPost.setId(1);
        addPost.setTitle("Test Title");
        addPost.setBody("Test Body");
        postService.savePost(addPost);

        // Test get post
        Post post = postService.getPostByID(1);

        // Verify
        Assert.assertNotNull(addPost);
        Assert.assertNotNull(post);
        Assert.assertEquals("Test Title", post.getTitle());
    }
}
