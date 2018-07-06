package com.skilldistillery.bjjtracker.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.bjjtracker.data.RoundDAO;
import com.skilldistillery.bjjtracker.entities.Round;

@RestController
@RequestMapping("api")
public class RoundController {
	@Autowired
	RoundDAO roundDAO;
	
	@RequestMapping(path="rounds", method=RequestMethod.GET)
	public List<Round> index(){
	  return roundDAO.index();
	}
	
	@RequestMapping(path = "rounds/{id}", method = RequestMethod.GET)
	public Round show(@PathVariable int id) {
		return roundDAO.show(id);
	}
	
	@RequestMapping(path = "rounds", method = RequestMethod.POST)
	public Round create(@RequestBody Round round) {
		return roundDAO.create(round);
	}
	
	@RequestMapping(path = "rounds/{id}", method = RequestMethod.PUT)
	public Round replace(@RequestBody Round round, @PathVariable int id) {
		return roundDAO.replace(id, round);
	}
	
	@RequestMapping(path = "rounds/{id}", method = RequestMethod.PATCH)
	public Round edit(@RequestBody Round round, @PathVariable int id) {
		return roundDAO.edit(id, round);
	}
	
	@RequestMapping(path = "rounds/{id}", method = RequestMethod.DELETE)
	public Boolean delete(@PathVariable int id) {
		return roundDAO.delete(id);
	}
}
