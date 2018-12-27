package com.louvrehotels.votingservice.controller;

import com.louvrehotels.votingservice.service.VotingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

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

    @PostMapping
    ResponseEntity<VoteReadDto> vote(@RequestBody VoteCreateDto voteCreateDto) {
        VoteReadDto v = votingService.vote(voteCreateDto);
        URI location = URI.create("/" + v.getId());
        return ResponseEntity.created(location).build();
    }

    @GetMapping("/{id}")
    ResponseEntity<VoteReadDto> findById(@PathVariable Long id) {
        VoteReadDto vote = votingService.findById(id);
        if (vote==null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(vote);
    }
}
