package it.polito.tdp.PremierLeague.model;



public class Event implements Comparable<Event> {
	public enum EventType{
		GOAL, 
		ESPULSIONE, 
		INFORTUNIO, 
	}
	private EventType type;
	private int num_azione;
	
	
	public Event(EventType type, int num_azione) {
		super();
		this.type = type;
		this.num_azione = num_azione;
	}
	public EventType getType() {
		return type;
	}
	public void setType(EventType type) {
		this.type = type;
	}
	
	
	public int getNum_azione() {
		return num_azione;
	}
	public void setNum_azione(int num_azione) {
		this.num_azione = num_azione;
	}
	@Override
	public int compareTo(Event o) {
		
		return this.num_azione-o.num_azione;
	}
	
}
