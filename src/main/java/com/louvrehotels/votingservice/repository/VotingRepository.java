package com.louvrehotels.votingservice.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface VotingRepository extends CrudRepository<Vote, Long> {
    @Query("SELECT count(v) from Vote v where v.pet = ?1")
    Long countVotesByPet(PetEnum pet);
}
