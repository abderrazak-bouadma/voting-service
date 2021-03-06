package com.louvrehotels.votingservice.controller;

import com.louvrehotels.votingservice.repository.PetEnum;
import com.louvrehotels.votingservice.service.VotingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.OPTIONS, RequestMethod.PUT})
@RestController
@RequestMapping("/votes")
public class VotingController {

    @Autowired
    VotingService votingService;

    @GetMapping
    ResponseEntity<List<VoteReadDto>> allVotes() {
        List<VoteReadDto> allVotes = votingService.getAllVotes();
        if (allVotes.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(allVotes);
    }

    // hello there
    @PostMapping
    ResponseEntity<VoteReadDto> vote(@RequestBody VoteCreateDto voteCreateDto) {
        VoteReadDto v = votingService.vote(voteCreateDto);
        URI location = URI.create("/" + v.getId());
        return ResponseEntity.created(location).build();
    }

    @GetMapping("/{id}")
    ResponseEntity<VoteReadDto> findById(@PathVariable Long id) {
        VoteReadDto vote = votingService.findById(id);
        if (vote == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(vote);
    }

    @GetMapping("/total/{pet}")
    ResponseEntity<Long> findTotalVotesByPet(@PathVariable PetEnum pet) {
        Long total = votingService.findVotesByPet(pet);
        return ResponseEntity.ok(total);
    }
}
