package pl.globoox.apzumi;


import org.junit.Assert;
import org.junit.Test;
import pl.globoox.apzumi.model.Post;
import pl.globoox.apzumi.service.PostService;

public class PostServiceTest {

    private PostService postService;

    public PostServiceTest(PostService postService) {
        this.postService = postService;
    }


    @Test
    public void shouldEidtPostTitleOrBody() {
        Post actualPost = postService.getPostByID(1);
        actualPost.setTitle("New Title");
        actualPost.setBody("New Body");
        Assert.assertEquals(actualPost, );
    }

}
