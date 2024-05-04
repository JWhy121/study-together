package com.elice.studytogether.mapper;


import com.elice.studytogether.domain.Comment;
import com.elice.studytogether.dto.CommentPostDto;
import com.elice.studytogether.dto.CommentResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CommentMapper {

    List<CommentResponseDto> commentResponseDtoList(List<CommentResponseDto> comments);

    CommentPostDto commentToCommentPostDto(Comment comment);

    CommentResponseDto commentToCommentResponseDto(Comment comment);

    Comment commentPostDtoToComment(CommentPostDto commentPostDto);
}