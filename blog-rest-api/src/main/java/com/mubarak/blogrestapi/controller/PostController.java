package com.mubarak.blogrestapi.controller;

import com.mubarak.blogrestapi.payload.PostDto;
import com.mubarak.blogrestapi.payload.PostDtoV2;
import com.mubarak.blogrestapi.payload.PostResponse;
import com.mubarak.blogrestapi.service.PostService;
import com.mubarak.blogrestapi.utils.AppConstants;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping()
public class PostController {

    private PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }


    //crete post api
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/api/v1/posts")
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto){
 return  new ResponseEntity<>(postService.createPost(postDto), HttpStatus.CREATED);
    }

    //get all post api
    @GetMapping("/api/v1/posts")
    public PostResponse getAllPosts(@RequestParam(value= "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false)int pageNumber,
                                    @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
                                    @RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
                                    @RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir){
        return postService.getAllPosts(pageNumber, pageSize, sortBy, sortDir);
    }

    //get post by id
    @GetMapping("/api/v1/posts/{id}")
    // using versioning with query parameter
    //@GetMapping("/api/posts/{id}", params = "version=1)
    //using custom headers
    //@GetMapping("/api/posts/{id}", headers = "X-API-VERSION=1")
    public ResponseEntity<PostDto> getPostById(@PathVariable(name= "id") long id){
        return ResponseEntity.ok(postService.getPostById(id));

    }
    //get post by id for version 2 (using uri path)
    @GetMapping("/api/v2/posts/{id}")
    // using versioning with query parameter
    //@GetMapping("/api/posts/{id}", params = "version=2)
    //using custom headers
    //@GetMapping("/api/posts/{id}", headers = "X-API-VERSION=2")
    public ResponseEntity<PostDtoV2> getPostByIdV2(@PathVariable(name= "id") long id){
        PostDto postDto = postService.getPostById(id);
        PostDtoV2 postDtoV2 = new PostDtoV2();
        postDtoV2.setId(postDto.getId());
        postDtoV2.setTitle(postDto.getTitle());
        postDtoV2.setDescription(postDto.getDescription());
        postDtoV2.setContent(postDto.getContent());
        List<String> tags = new ArrayList<>();
        tags.add("Java");
        tags.add("Spring boot");
        tags.add("Go");
        postDtoV2.setTags(tags);
        return ResponseEntity.ok(postDtoV2);

    }

    //update post by Id api
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/api/v1/posts/{id}")
    public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto, @PathVariable(name = "id") long id){
       PostDto postResponse = postService.updatePost(postDto, id);
       return new ResponseEntity<>(postResponse, HttpStatus.OK);
    }

    //delete post rest api
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/api/v1/posts/{id}")
    public ResponseEntity<String> deletePost(@PathVariable(name = "id") long id){
        postService.deletePostById(id);
        return new ResponseEntity<>("Post deleted successfully", HttpStatus.OK);
    }

}
