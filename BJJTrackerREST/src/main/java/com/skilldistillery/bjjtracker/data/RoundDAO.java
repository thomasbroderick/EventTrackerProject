package com.skilldistillery.bjjtracker.data;

import java.util.List;

import com.skilldistillery.bjjtracker.entities.Round;

public interface RoundDAO {
	List<Round> index();
	Round show(int id);
	Round create(Round round);
	Round edit(int id, Round round);
	Round replace(int id, Round round);
	Boolean delete(int id);
}
