package org.example.expert.domain.comment.controller;

import lombok.RequiredArgsConstructor;
import org.example.expert.domain.comment.dto.response.CommentDeleteResponse;
import org.example.expert.domain.comment.service.CommentAdminService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CommentAdminController {

    private final CommentAdminService commentAdminService;

    @PostMapping("/admin/comments/{commentId}")
    public ResponseEntity<CommentDeleteResponse> deleteComment(@PathVariable long commentId) {

        return ResponseEntity.status(HttpStatus.OK)
                .body(commentAdminService.deleteComment(commentId));
    }
}
