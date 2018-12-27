package com.louvrehotels.votingservice.controller;

import com.louvrehotels.votingservice.repository.PetEnum;

/**
 * Created by Abderrazak BOUADMA
 * on 10/12/2018.
 */

public class VoteReadDto {

    Long id;
    PetEnum pet;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PetEnum getPet() {
        return pet;
    }

    public void setPet(PetEnum pet) {
        this.pet = pet;
    }
}
