package org.example.expert.domain.comment.dto.response;


import lombok.Getter;
import org.example.expert.domain.comment.entity.Comment;

@Getter
public class CommentDeleteResponse {

    private final Long id;

    public CommentDeleteResponse(Long id) {
        this.id = id;
    }

    public static CommentDeleteResponse from(Comment comment) {
        return new CommentDeleteResponse(
                comment.getId()
        );
    }
}
