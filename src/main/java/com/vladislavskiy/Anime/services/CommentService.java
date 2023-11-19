package com.vladislavskiy.Anime.services;

import com.vladislavskiy.Anime.dto.CommentDto;
import com.vladislavskiy.Anime.models.Comment;

import java.util.List;

public interface CommentService {
    List<Comment> getAllComments();

    Comment getCommentByid(Long id);
}
