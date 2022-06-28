package it.polito.tdp.PremierLeague.model;

public class Prestazione {
private int PlayerId;
private int MatchId;
private double efficienza;
private int team;




public Prestazione(int playerId, int matchId, double efficienza, int team) {
	super();
	PlayerId = playerId;
	MatchId = matchId;
	this.efficienza = efficienza;
	this.team=team;
}
public int getPlayerId() {
	return PlayerId;
}
public void setPlayerId(int playerId) {
	PlayerId = playerId;
}
public int getMatchId() {
	return MatchId;
}
public void setMatchId(int matchId) {
	MatchId = matchId;
}
public double getEfficienza() {
	return efficienza;
}
public void setEfficienza(double efficienza) {
	this.efficienza = efficienza;
}
public int getTeam() {
	return team;
}
@Override
public String toString() {
	return "Prestazione [PlayerId=" + PlayerId + ", MatchId=" + MatchId + ", efficienza=" + efficienza + ", team="
			+ team + "]";
}


}
