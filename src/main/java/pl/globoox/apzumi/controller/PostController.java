package pl.globoox.apzumi.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import pl.globoox.apzumi.model.Post;
import pl.globoox.apzumi.service.PostService;

import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostController {

    private PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }


    /* Update data from JSON */
    @PostMapping("/update")
    public void updateData() {
        postService.downloadData();
    }

    /* Display all posts where title equals parameter */
    @PostMapping("/{title}")
    public List<Post> getByTitle(@PathVariable(value = "title") String title) {
        return postService.getPostsByTitle(title);
    }

    /* Edit posts using their ID */
    @PutMapping("/{id}")
    @ResponseBody
    public Post editByID(
            @PathVariable(value = "id") int id,
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String body) {

        return postService.editPostByID(id, title, body);
    }

    /* Delete post using his ID */
    @DeleteMapping("/{id}")
    public String deleteByID(@PathVariable(value = "id") int id) {
        return postService.deletePostByID(id);
    }

}
