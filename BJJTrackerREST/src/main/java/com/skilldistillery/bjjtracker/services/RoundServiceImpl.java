package com.skilldistillery.bjjtracker.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.bjjtracker.entities.Round;
import com.skilldistillery.bjjtracker.repositories.RoundRepository;

@Service
public class RoundServiceImpl implements RoundService {
	@Autowired
	private RoundRepository roundRepo;
	
	@Override
	public List<Round> index() {
		return roundRepo.findAll();
	}

	@Override
	public Round show(int id) {
		return roundRepo.findById(id).get();
	}

	@Override
	public Round create(Round round) {
		return roundRepo.saveAndFlush(round);
	}

	@Override
	public Round edit(int id, Round round) {
		round.setId(id);
		return roundRepo.saveAndFlush(round);
	}

	@Override
	public void delete(int id) {
		roundRepo.deleteById(id);
	}

}
