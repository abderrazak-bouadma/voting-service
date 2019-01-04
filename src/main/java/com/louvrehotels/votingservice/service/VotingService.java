package com.louvrehotels.votingservice.service;

import com.louvrehotels.votingservice.controller.VoteCreateDto;
import com.louvrehotels.votingservice.controller.VoteMapper;
import com.louvrehotels.votingservice.controller.VoteReadDto;
import com.louvrehotels.votingservice.repository.PetEnum;
import com.louvrehotels.votingservice.repository.Vote;
import com.louvrehotels.votingservice.repository.VotingRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class VotingService {

    @Autowired
    VotingRepository votingRepository;

    @Autowired
    VoteMapper voteMapper;

    public static final Logger logger = LoggerFactory.getLogger(VotingService.class);

    public List<VoteReadDto> getAllVotes() {
        List<VoteReadDto> result = new ArrayList<>();
        votingRepository.findAll().forEach(e -> result.add(voteMapper.toVoteReadDto(e)));
        return result;
    }

    @Transactional
    public VoteReadDto vote(VoteCreateDto voteCreateDto) {
        try {
            Vote vote = voteMapper.fromCreateDto(voteCreateDto);
            Vote saved = votingRepository.save(vote);
            logger.info("voted {}", saved);
            return voteMapper.toVoteReadDto(saved);
        } catch (Exception e) {
            throw new ServiceException(e.getMessage());
        }
    }

    public VoteReadDto findById(Long id) {
        Optional<Vote> result = votingRepository.findById(id);
        return voteMapper.toVoteReadDto(result.orElse(null));
    }

    public Long findVotesByPet(PetEnum pet) {
        return votingRepository.countVotesByPet(pet);
    }
}
