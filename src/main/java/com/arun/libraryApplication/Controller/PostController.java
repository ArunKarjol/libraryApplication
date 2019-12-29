package com.arun.libraryApplication.Controller;

import com.arun.libraryApplication.Entity.Post;
import com.arun.libraryApplication.Exception.ResourceNotFoundException;
import com.arun.libraryApplication.Repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class PostController {

    @Autowired
    PostRepository postRepository;

    @GetMapping("/posts")
    public Page<Post> getAllPosts(Pageable pageable){
        return postRepository.findAll(pageable);
    }

    @PostMapping("/posts")
    public Post createPost(@Valid @RequestBody Post post){
        return postRepository.save(post);
    }

    @PutMapping("/posts/{postId}")
    public Post updatePost(@PathVariable Long postId, @Valid @RequestBody Post postRequest){
        return postRepository.findById(postId).map(post -> {
            post.setTitle(postRequest.getTitle());
            post.setDescription(postRequest.getDescription());
            post.setContent(postRequest.getContent());
            return postRepository.save(post);
        }).orElseThrow(()-> new  ResourceNotFoundException("PostId "+postId+" Not Found")) ;
    }

    @DeleteMapping("posts/{postId}")
    public ResponseEntity<?> deletePost(@PathVariable Long postId){
        return postRepository.findById(postId).map(post->{
            postRepository.delete(post);
            return ResponseEntity.ok().build();
        }).orElseThrow(()-> new ResourceNotFoundException("PostId "+postId+" Not Found"));
    }

}