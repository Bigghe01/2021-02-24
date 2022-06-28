package it.polito.tdp.PremierLeague.model;


import java.util.PriorityQueue;




import it.polito.tdp.PremierLeague.model.Event.EventType;






public class Simulatore {

	//dati in ingresso
	private int N;
	private Match match;
	//dati in uscita
	private int goals1;
	private int goals2;
	private int num_infortuni;
	
	
	//coda degli eventi
	private PriorityQueue<Event>queue;
	
	//modello del mondo
	private int giocatoris1;
	private int giocatoris2;
	private int team1;
	private int team2;
	private int teamGiocMigliore;
	
	public void setTeamGiocMigliore(int teamGiocMigliore) {
		this.teamGiocMigliore = teamGiocMigliore;
	}

	
	
	public Simulatore(int n, Match match) {
		super();
		N = n;
		this.match = match;
	}



	public void init (Match m, int N) {
		this.N=N;
		
		
		//inizializza gli output
		goals1=0;
		goals2=0;
		
		//inizializzo il mondo
		giocatoris1=11;
		giocatoris2=11;
		team1=m.teamHomeID;
		team2=m.teamAwayID;
		
		//creo la coda
		this.queue = new PriorityQueue<>();
		
		//caricamento iniziale coda
		for(int i=1;i<=N;i++) {
			double caso=Math.random();
			if(caso<0.5) {
			this.queue.add(new Event(EventType.GOAL,i));
			continue;
		}
		else if(caso<0.8) {
			this.queue.add(new Event(EventType.ESPULSIONE,i));
			continue;
		}
		else  {
			this.queue.add(new Event(EventType.INFORTUNIO,i));
			
			continue;
		}}
	}
	
	public void run() {
		
		while(!this.queue.isEmpty())
		{
			Event e=this.queue.poll();
			
			processEvent(e);
		}
		
	}

	private void processEvent(Event e) {
	
		EventType type=e.getType();
		int num_azione=e.getNum_azione();
		
		switch(type) {
		case GOAL:
			if(giocatoris1>giocatoris2) {
				goals1++;
			}
			else if(giocatoris1<giocatoris2) {
				goals2++;
			}
			else {
				if(teamGiocMigliore==team1) {
					goals1++;
				}
				else {
					goals2++;
				}
			}
			break;
			
		case ESPULSIONE:
			
			double caso2=Math.random();
			if(caso2<0.6) {
				if(teamGiocMigliore==team1) {
					giocatoris1--;
				}
				else  {
					giocatoris2--;
				}
			}else {
				if(teamGiocMigliore!=team1) {
					giocatoris1--;
				}
				else  {
					giocatoris2--;
				}
			}
			
			break;
		case INFORTUNIO:
			num_infortuni++;
		double caso3=Math.random();
		
		if(caso3<0.5) {
			for(int i=1;i<=2;i++) {
				double caso=Math.random();
				if(caso<0.5) {
				this.queue.add(new Event(EventType.GOAL,num_azione+1));
				continue;
			}
			else if(caso<0.8) {
				this.queue.add(new Event(EventType.ESPULSIONE,num_azione+1));
				continue;
			}
			else  {
				this.queue.add(new Event(EventType.INFORTUNIO,num_azione+1));
				continue;
			}}
			
		}
		else {
			for(int i=1;i<=3;i++) {
				double caso=Math.random();
				if(caso<0.5) {
				this.queue.add(new Event(EventType.GOAL,num_azione+1));
				continue;
			}
			else if(caso<0.8) {
				this.queue.add(new Event(EventType.ESPULSIONE,num_azione+1));
				continue;
			}
			else  {
				this.queue.add(new Event(EventType.INFORTUNIO,num_azione+1));
				continue;
			}}
		}
			
			break;
		}
		
	}



	public int getGoals1() {
		return goals1;
	}



	public void setGoals1(int goals1) {
		this.goals1 = goals1;
	}



	public int getGoals2() {
		return goals2;
	}



	public void setGoals2(int goals2) {
		this.goals2 = goals2;
	}



	public int getGiocatoris1() {
		return giocatoris1;
	}



	public void setGiocatoris1(int giocatoris1) {
		this.giocatoris1 = giocatoris1;
	}



	public int getGiocatoris2() {
		return giocatoris2;
	}



	public void setGiocatoris2(int giocatoris2) {
		this.giocatoris2 = giocatoris2;
	}



	public int getN() {
		return N;
	}



	public Match getMatch() {
		return match;
	}



	public PriorityQueue<Event> getQueue() {
		return queue;
	}



	public int getTeam1() {
		return team1;
	}



	public int getTeam2() {
		return team2;
	}



	public int getTeamGiocMigliore() {
		return teamGiocMigliore;
	}



	public int getNum_infortuni() {
		return num_infortuni;
	}



	public void setNum_infortuni(int num_infortuni) {
		this.num_infortuni = num_infortuni;
	}
	
	
	
}
