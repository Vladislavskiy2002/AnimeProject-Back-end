package com.vladislavskiy.Anime.services.Impl;

import com.vladislavskiy.Anime.handler.CommentNotFoundException;
import com.vladislavskiy.Anime.models.Comment;
import com.vladislavskiy.Anime.repositories.CommentRepository;
import com.vladislavskiy.Anime.services.CommentService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentServiceImpl implements CommentService {
    private CommentRepository repository;

    public CommentServiceImpl(CommentRepository repository) {
        this.repository = repository;
    }
    @Override
    public List<Comment> getAllComments() {
        return Optional.of(repository.findAll()).orElseThrow(()->new CommentNotFoundException("Comments haven't found"));
    }

    @Override
    public Comment getCommentByid(Long id) {
        return repository.findById(id).orElseThrow(()->new CommentNotFoundException("Comment with id: "+ id +" hasn't found"));
    }
}
