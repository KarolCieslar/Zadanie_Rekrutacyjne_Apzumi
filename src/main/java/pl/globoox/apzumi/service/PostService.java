package pl.globoox.apzumi.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import pl.globoox.apzumi.exception.ResourceNotFoundException;
import pl.globoox.apzumi.model.Post;
import pl.globoox.apzumi.repository.PostRepository;

import java.util.List;

@Service
public class PostService {


    private static final Logger log = LoggerFactory.getLogger(PostService.class);
    private PostRepository postRepository;
    private RestTemplate restTemplate;


    public PostService(PostRepository postRepository, RestTemplate restTemplate) {
        this.postRepository = postRepository;
        this.restTemplate = restTemplate;
    }


    /* Get post by ID */
    public Post getPostByID(int id) {
        Post post = postRepository.getPostByid(id);
        if (post == null) {
            throw new ResourceNotFoundException("Invalid post ID: " + id);
        }
        if (post.isDeleted()) {
            throw new ResourceNotFoundException("Invalid post ID: " + id);
        }
        return post;
    }


    /* This function return lists of posts downloaded
     from database. Parameters of this function is post title */
    public List<Post> getPostsByTitle(String title) {
        List<Post> posts = postRepository.getPostByTitle(title);
        posts.removeIf(Post::isDeleted);
        if (posts.size() == 0) {
            throw new ResourceNotFoundException("Invalid post title: " + title);
        }
        return posts;
    }


    /* This function save post to database */
    public Post savePost(Post post) {
        return postRepository.save(post);
    }


    /* By this function You can edit post title and body
     You have to use parameters post id to edit specific post
     Parameters title and body are not required to pass together */
    public Post editPostByID(int id, String title, String body) {
        Post post = postRepository.getPostByid(id);
        if (post == null) {
            throw new ResourceNotFoundException("Invalid post ID: " + id);
        }

        if (title != null) {
            post.setTitle(title);
        }

        if (body != null) {
            post.setBody(body);
        }

        post.setEdited(true);
        postRepository.save(post);
        return post;
    }


    /* This functions able You to delete post by ID */
    public String deletePostByID(int id) {
        Post post = postRepository.getPostByid(id);
        if (post == null) {
            throw new ResourceNotFoundException("Invalid post ID: " + id);
        }
        if (post.isDeleted()) {
            throw new ResourceNotFoundException("Invalid post ID: " + id);
        }
        post.setDeleted(true);
        postRepository.save(post);
        return "Post deleted!";
    }


    /* This function using RestRemplate
     to collect updated data from JSON API */
    @Scheduled(cron = "0 0 12 * * ?")
    public void downloadData() {
        log.debug(" --- START DOWNLOADING JSON DATA ---");
        Post[] postsList = restTemplate.getForObject("https://jsonplaceholder.typicode.com/posts", Post[].class);
        if (postsList == null) {
            log.debug("No data downloaded...");
            return;
        }

        for (Post updatedPost : postsList) {
            updatedPost.setEdited(false);
            Post post = postRepository.getPostByid(updatedPost.getId());

            if (post == null) {
                postRepository.save(updatedPost);
            } else {
                if (!post.isEdited() && !post.isDeleted()) {
                    log.debug("Load post..." + post.getId());
                    postRepository.save(updatedPost);
                }
            }
        }
        log.debug(" --- DATA SUCCESFULLY LOADED ---");
    }
}
