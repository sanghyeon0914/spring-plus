package org.example.expert.domain.comment.repository;

import org.example.expert.domain.comment.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    //N + 1 문제가 생긴 상황
    /*@Query("SELECT c FROM Comment c JOIN c.user WHERE c.todo.id = :todoId")
    List<Comment> findByTodoIdWithUser(@Param("todoId") Long todoId);*/

    // 1차 수정본
    @Query("SELECT c FROM Comment c JOIN FETCH c.user WHERE c.todo.id = :todoId")
    List<Comment> findByTodoIdWithUser(@Param("todoId") Long todoId);

    // 2차 수정본
    /*@EntityGraph(attributePaths = "todoId")
    List<Comment> findByTodoIdWithUser(@Param("todoId") Long todoId);*/
}

