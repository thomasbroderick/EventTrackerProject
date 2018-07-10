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
import com.skilldistillery.bjjtracker.services.RoundService;

@RestController
@RequestMapping("api")
public class RoundController {
	@Autowired
	RoundService service;
	
	@RequestMapping(path="rounds", method=RequestMethod.GET)
	public List<Round> index(){
	  return service.index();
	}
	
	@RequestMapping(path = "rounds/{id}", method = RequestMethod.GET)
	public Round show(@PathVariable int id) {
		return service.show(id);
	}
	
	@RequestMapping(path = "rounds", method = RequestMethod.POST)
	public Round create(@RequestBody Round round) {
		return service.create(round);
	}
	
	
	@RequestMapping(path = "rounds/{id}", method = RequestMethod.PATCH)
	public Round edit(@RequestBody Round round, @PathVariable int id) {
		return service.edit(id, round);
	}
	
	@RequestMapping(path = "rounds/{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable int id) {
		service.delete(id);
	}
}
