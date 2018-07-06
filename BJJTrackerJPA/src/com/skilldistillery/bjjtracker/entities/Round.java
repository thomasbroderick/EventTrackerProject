package com.skilldistillery.bjjtracker.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.CreationTimestamp;

@Entity
public class Round {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String opponent;
	@CreationTimestamp
	private Date date;
	@Column(name="points_scored")
	private int pointsScored;
	private String result;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getOpponent() {
		return opponent;
	}
	public void setOpponent(String opponent) {
		this.opponent = opponent;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public int getPointsScored() {
		return pointsScored;
	}
	public void setPointsScored(int pointsScored) {
		this.pointsScored = pointsScored;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	@Override
	public String toString() {
		return "Round [id=" + id + ", opponent=" + opponent + ", date=" + date + ", pointsScored=" + pointsScored
				+ ", result=" + result + "]";
	}
	
	
	
	
}
