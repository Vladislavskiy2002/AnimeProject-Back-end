package com.vladislavskiy.Anime.repositories;

import com.vladislavskiy.Anime.models.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CommentRepository extends JpaRepository<Comment,Long> {
}
