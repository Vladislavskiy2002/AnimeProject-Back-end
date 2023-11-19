package com.vladislavskiy.Anime.controller;

import com.vladislavskiy.Anime.dto.CommentDto;
import com.vladislavskiy.Anime.handler.CommentNotFoundException;
import com.vladislavskiy.Anime.models.Comment;
import com.vladislavskiy.Anime.services.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
public class CommentController {
    private final CommentService service;

    public CommentController(CommentService service) {
        this.service = service;
    }

    @GetMapping("/comment")
    public ResponseEntity<?> getAllComments(){
        try {
            return new ResponseEntity<>(service.getAllComments(), HttpStatus.OK);
        }
        catch (CommentNotFoundException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/comment/{id}")
    public ResponseEntity<?> getCommentByID(@PathVariable Long id) {
        try {
            return new ResponseEntity<>(service.getCommentByid(id), HttpStatus.OK);
        } catch (CommentNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
