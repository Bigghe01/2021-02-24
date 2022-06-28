package it.polito.tdp.PremierLeague.model;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleDirectedWeightedGraph;

import it.polito.tdp.PremierLeague.db.PremierLeagueDAO;


public class Model {
	
private PremierLeagueDAO dao;
private Graph<Prestazione,DefaultWeightedEdge> grafo;
	public List<Match>partite;
	private List<Prestazione>giocatPartita;
	private Map<Integer,String>mappap;
	private int goals1;
	private int goals2;
	private int giocatoris1;
	private int giocatoris2;
	private Prestazione migliorepart;
	private String squadra1;
	private String squadra2;
	public int num_infortuni;
	
	public Model() {
		dao=new PremierLeagueDAO();
		partite=dao.listAllMatches();
		mappap=new TreeMap<Integer,String>();
		for(Player p: dao.listAllPlayers()) {
			mappap.put(p.getPlayerID(), p.getName());
		}
	}
	
	public String creaGrafo(Match m) {
		this.grafo=new SimpleDirectedWeightedGraph<>(DefaultWeightedEdge.class);
		this.giocatPartita=dao.PlayersMatch(m);
		//aggiungo vertici
		Graphs.addAllVertices(this.grafo,giocatPartita);
		
		//aggiunta archi
		for(Prestazione p: giocatPartita) {
			for(Prestazione p2: giocatPartita) {
				if((p.getEfficienza()-p2.getEfficienza())>=0 && p.getTeam()!=p2.getTeam() && !this.grafo.containsEdge(p, p2) && !this.grafo.containsEdge(p2, p)) {
					Graphs.addEdgeWithVertices(this.grafo, p, p2,p.getEfficienza()-p2.getEfficienza());
				}
				
			
		}}
		squadra1=m.teamHomeNAME;
		squadra2=m.teamAwayNAME;
		return "Grafo creato con "+this.grafo.vertexSet().size()+
				"vertici e "+this.grafo.edgeSet().size()+"\n";
	}
	
	public String getEgeUscenti(){
		String s="";
		for(Prestazione p: this.grafo.vertexSet()) {
			s+=p.toString()+" ///// "+this.grafo.outgoingEdgesOf(p)+"\n";
		}
		return s;
	}
	public String GiocatoreMigliore() {
		Prestazione migliore=null;
		double pesomax=(double) Integer.MIN_VALUE;
		
		
		for(Prestazione p:this.grafo.vertexSet()) {
			double sommaex=0.0;
			for(DefaultWeightedEdge d:this.grafo.outgoingEdgesOf(p)) {
				 sommaex+=this.grafo.getEdgeWeight(d);}
			double sommaint=0.0;
				 for(DefaultWeightedEdge d:this.grafo.incomingEdgesOf(p)) {
			sommaint+=this.grafo.getEdgeWeight(d);
			}
			double peso=sommaex-sommaint;
			if(peso>pesomax) {
				pesomax=peso;
				migliore=p;
			}
		}
		migliorepart=migliore;
		return "Giocatore Migliore\n"+migliore.getPlayerId()+": "+mappap.get(migliore.getPlayerId())+" Totale Efficienza: "+pesomax;
	}
	
	
	public void simula(Match partita, int N) {
		Simulatore sim = new Simulatore(N, partita);
		sim.init(partita, N);
		sim.setTeamGiocMigliore(migliorepart.getTeam());
	
		sim.run();
		this.goals1=sim.getGoals1();
		this.goals2=sim.getGoals2();
		this.giocatoris1=sim.getGiocatoris1();
		this.giocatoris2=sim.getGiocatoris2();
		this.num_infortuni=sim.getNum_infortuni();
	}

	public Prestazione getMigliorepart() {
		return migliorepart;
	}

	public void setMigliorepart(Prestazione migliorepart) {
		this.migliorepart = migliorepart;
	}

	public int getGoals1() {
		return goals1;
	}

	public int getGoals2() {
		return goals2;
	}

	public int getGiocatoris1() {
		return giocatoris1;
	}

	public int getGiocatoris2() {
		return giocatoris2;
	}

	public String getSquadra1() {
		return squadra1;
	}

	public void setSquadra1(String squadra1) {
		this.squadra1 = squadra1;
	}

	public String getSquadra2() {
		return squadra2;
	}

	public void setSquadra2(String squadra2) {
		this.squadra2 = squadra2;
	}

	public PremierLeagueDAO getDao() {
		return dao;
	}

	public List<Prestazione> getGiocatPartita() {
		return giocatPartita;
	}
	
}
