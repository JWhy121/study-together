package com.elice.studytogether.mapper;

import com.elice.studytogether.domain.Comment;
import com.elice.studytogether.domain.Post;
import com.elice.studytogether.dto.CommentPostDto;
import com.elice.studytogether.dto.PostDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PostMapper {

    Post postDtoToPost(PostDto postDto);
}
