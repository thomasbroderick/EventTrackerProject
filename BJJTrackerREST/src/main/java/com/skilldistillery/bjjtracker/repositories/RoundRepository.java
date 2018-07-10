package com.skilldistillery.bjjtracker.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.bjjtracker.entities.Round;

public interface RoundRepository extends JpaRepository<Round, Integer> {

}
