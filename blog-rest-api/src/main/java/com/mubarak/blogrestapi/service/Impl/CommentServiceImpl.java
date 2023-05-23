package com.mubarak.blogrestapi.service.Impl;

import com.mubarak.blogrestapi.exception.BlogApiException;
import com.mubarak.blogrestapi.exception.ResourceNotFoundException;
import com.mubarak.blogrestapi.model.Comment;
import com.mubarak.blogrestapi.model.Post;
import com.mubarak.blogrestapi.payload.CommentDto;
import com.mubarak.blogrestapi.repository.CommentRepository;
import com.mubarak.blogrestapi.repository.PostRepository;
import com.mubarak.blogrestapi.service.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


@Service
public class CommentServiceImpl implements CommentService {

    private CommentRepository commentRepository;
    private PostRepository postRepository;
    private ModelMapper modelMapper;

    public CommentServiceImpl(CommentRepository commentRepository,PostRepository postRepository,ModelMapper modelMapper) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public CommentDto createComment(long postId, CommentDto commentDto) {

        Comment comment= mapToEntity(commentDto);

        //retrieve post entity by id
        Post post = postRepository.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post", "id", postId));

        //set post to comment entity
        comment.setPost(post);

        //save comment entity to db
        Comment newComment = commentRepository.save(comment);
        return mapToDto(newComment);
    }

    @Override
    public List<CommentDto> getCommentsByPostId(long postId) {

        //retrieve comment by post id
        List<Comment> comments = commentRepository.findBypostId(postId);
        //convert list of comments to list of commentDto
        return comments.stream().map(comment -> mapToDto(comment)).collect(Collectors.toList());

    }

    @Override
    public CommentDto getCommenById(long postId, long commentId) {

        Post post = postRepository.findById(postId).orElseThrow(()->
                new ResourceNotFoundException("Post", "id", postId));

        //retrieve comment by id
        Comment comment = commentRepository.findById(commentId).orElseThrow(()->
                new ResourceNotFoundException("Comment", "id", commentId));

        if(!Objects.equals(comment.getPost().getId(), post.getId())){
            throw new BlogApiException(HttpStatus.BAD_REQUEST, "Comment not in this post");

        }

return mapToDto(comment);

    }

    @Override
    public CommentDto updateComment(long postId, long commentId, CommentDto commentRequest) {
        Post post = postRepository.findById(postId).orElseThrow(()->
                new ResourceNotFoundException("Post", "id", postId));

        Comment comment = commentRepository.findById(commentId).orElseThrow(()->
                new ResourceNotFoundException("Comment", "id", commentId));


        if(!Objects.equals(comment.getPost().getId(), post.getId())){
            throw new BlogApiException(HttpStatus.BAD_REQUEST, "Comment not in this post");

        }

        comment.setName(commentRequest.getName());
        comment.setEmail(commentRequest.getEmail());
        comment.setMessage(commentRequest.getMessage());

        Comment updatedComment =  commentRepository.save(comment);
        return mapToDto(updatedComment);
    }

    @Override
    public void deleteComment(long postId, long commentId) {
        Post post = postRepository.findById(postId).orElseThrow(()->
                new ResourceNotFoundException("Post", "id", postId));

        Comment comment = commentRepository.findById(commentId).orElseThrow(()->
                new ResourceNotFoundException("Comment", "id", commentId));


        if(!Objects.equals(comment.getPost().getId(), post.getId())){
            throw new BlogApiException(HttpStatus.BAD_REQUEST, "Comment not in this post");

        }

        commentRepository.delete(comment);
    }

    private CommentDto mapToDto(Comment comment){
        CommentDto commentDto = modelMapper.map(comment, CommentDto.class);
        return commentDto;

    }

    private Comment mapToEntity (CommentDto commentDto){
        Comment comment = modelMapper.map(commentDto, Comment.class);
        return comment;
    }
}
