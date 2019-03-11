package com.eduonix.votingsystem.repositories;

import com.eduonix.votingsystem.entity.Citizen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CitizenRepo extends JpaRepository<Citizen, Integer> {

    public Citizen findByName(String name);

}
