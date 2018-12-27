package com.louvrehotels.votingservice.controller;

import com.louvrehotels.votingservice.repository.PetEnum;

/**
 * Created by Abderrazak BOUADMA
 * on 10/12/2018.
 */
public class VoteCreateDto {
    PetEnum pet;

    public PetEnum getPet() {
        return pet;
    }

    public void setPet(PetEnum pet) {
        this.pet = pet;
    }
}
