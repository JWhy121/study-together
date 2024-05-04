package com.elice.studytogether.mapper;

import com.elice.studytogether.domain.Comment;
import com.elice.studytogether.dto.CommentPostDto;
import com.elice.studytogether.dto.CommentResponseDto;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-04-29T02:06:47+0900",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.10 (Oracle Corporation)"
)
@Component
public class CommentMapperImpl implements CommentMapper {

    @Override
    public List<CommentResponseDto> commentResponseDtoList(List<CommentResponseDto> comments) {
        if ( comments == null ) {
            return null;
        }

        List<CommentResponseDto> list = new ArrayList<CommentResponseDto>( comments.size() );
        for ( CommentResponseDto commentResponseDto : comments ) {
            list.add( commentResponseDto );
        }

        return list;
    }

    @Override
    public CommentPostDto commentToCommentPostDto(Comment comment) {
        if ( comment == null ) {
            return null;
        }

        CommentPostDto commentPostDto = new CommentPostDto();

        commentPostDto.setContent( comment.getContent() );

        return commentPostDto;
    }

    @Override
    public CommentResponseDto commentToCommentResponseDto(Comment comment) {
        if ( comment == null ) {
            return null;
        }

        CommentResponseDto commentResponseDto = new CommentResponseDto();

        commentResponseDto.setId( comment.getId() );
        commentResponseDto.setContent( comment.getContent() );

        return commentResponseDto;
    }

    @Override
    public Comment commentPostDtoToComment(CommentPostDto commentPostDto) {
        if ( commentPostDto == null ) {
            return null;
        }

        Comment comment = new Comment();

        comment.setContent( commentPostDto.getContent() );

        return comment;
    }
}
