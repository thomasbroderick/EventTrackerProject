package com.skilldistillery.bjjtracker.data;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.skilldistillery.bjjtracker.entities.Round;

@Transactional
@Service
public class RoundDAOImpl implements RoundDAO {
	@PersistenceContext
	private EntityManager em;

	@Override
	public List<Round> index() {
		String query = "SELECT r from Round r";
		return em.createQuery(query, Round.class).getResultList();
	}

	@Override
	public Round show(int id) {
		return em.find(Round.class, id);
	}

	@Override
	public Round create(Round round) {
		try {
			em.persist(round);
			em.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return round;
	}

	@Override
	public Round edit(int id, Round round) {
		Round managed = em.find(Round.class, id);
		try {
			Round mapped = round;
			if (mapped.getOpponent() != null)
				managed.setOpponent(mapped.getOpponent());
			if (mapped.getDate() != null)
				managed.setDate(mapped.getDate());
			if (mapped.getPointsScored() != 0)
				managed.setPointsScored(mapped.getPointsScored());
			if (mapped.getResult() != null)
				managed.setResult(mapped.getResult());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return managed;
	}

	@Override
	public Round replace(int id, Round round) {
		Round managed = em.find(Round.class, id);
		try {
			Round mapped = round;
			managed.setOpponent(mapped.getOpponent());
			managed.setDate(mapped.getDate());
			managed.setPointsScored(mapped.getPointsScored());
			managed.setResult(mapped.getResult());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return managed;
	}

	@Override
	public Boolean delete(int id) {
		Boolean result = false;
		try {
			em.remove(em.find(Round.class, id));
			result = true;
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return result;
	}

}
