package com.skilldistillery.bjjtracker.services;

import java.util.List;

import com.skilldistillery.bjjtracker.entities.Round;

public interface RoundService {
	List<Round> index();
	Round show(int id);
	Round create(Round round);
	Round edit(int id, Round round);
	void delete(int id);
}
