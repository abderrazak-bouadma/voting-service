package com.louvrehotels.votingservice.controller;

import com.louvrehotels.votingservice.repository.Vote;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface VoteMapper {

    Vote fromCreateDto(VoteCreateDto voteCreateDto);

    VoteReadDto toVoteReadDto(Vote vote);
}
