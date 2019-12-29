package com.arun.libraryApplication.Controller;

import com.arun.libraryApplication.Entity.Comment;
import com.arun.libraryApplication.Exception.ResourceNotFoundException;
import com.arun.libraryApplication.Repository.CommentRepository;
import com.arun.libraryApplication.Repository.PostRepository;
import net.bytebuddy.implementation.bytecode.Throw;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class CommentController {

    @Autowired
    CommentRepository commentRepository;

    @Autowired
    PostRepository postRepository;

    @GetMapping("/posts/{postId}/comments")
    public Page<Comment> getAllCommentsByPostId(@PathVariable (value = "postId") Long postId, Pageable pageable){
        return commentRepository.findByPostId(postId,pageable);
    }

    @PostMapping("/posts/{postId}/comments")
    public Comment createComment(@PathVariable (value = "postId") Long postId, @RequestBody Comment comment){
        return postRepository.findById(postId).map(post -> {
            comment.setPost(post);
            return commentRepository.save(comment);
        }).orElseThrow(()-> new ResourceNotFoundException("Post ID "+postId+" Not Found"));
    }

    @PutMapping("/posts/{postId}/coments/{commentId}")
    public Comment updateComment(@PathVariable (value = "postId") Long postId,
                                 @PathVariable (value = "commentId") Long commentId,
                                 @Valid @RequestBody Comment commentRequest){
        if(!postRepository.existsById(postId)){
            throw new ResourceNotFoundException("Post Id "+postId+" Not Found ");
        }

        return commentRepository.findById(commentId).map(comment->{
            comment.setText(commentRequest.getText());
            return commentRepository.save(comment);
        }).orElseThrow(()-> new ResourceNotFoundException("Comment Id "+commentId+" Not Found"));
    }

    @DeleteMapping("/posts/{postId}/comments/{commentId}")
    public ResponseEntity<?> deleteComment(@PathVariable (value = "postId") Long postId,
                                           @PathVariable (value = "commentId") Long commentId){
        return commentRepository.findByIdAndPostId(commentId,postId).map(comment -> {
            commentRepository.delete(comment);
            return ResponseEntity.ok().build();
        }).orElseThrow(()-> new ResourceNotFoundException("Comment not found with Id "+commentId+" and post Id " + postId));
    }
}
