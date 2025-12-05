package org.example.expert.domain.comment.service;

import lombok.RequiredArgsConstructor;
import org.example.expert.aspects.RequestAndResponseInfoCheck;
import org.example.expert.domain.comment.dto.response.CommentDeleteResponse;
import org.example.expert.domain.comment.entity.Comment;
import org.example.expert.domain.comment.repository.CommentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CommentAdminService {

    private final CommentRepository commentRepository;

    @RequestAndResponseInfoCheck
    @Transactional
    public CommentDeleteResponse deleteComment(long commentId) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 댓글입니다."));

        commentRepository.deleteById(comment.getId());

        return CommentDeleteResponse.from(comment);
    }
}
