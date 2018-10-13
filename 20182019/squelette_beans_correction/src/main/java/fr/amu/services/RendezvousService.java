package fr.amu.services;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.base.Joiner;

import fr.amu.Application;
import fr.amu.models.Rendezvous;
import fr.amu.models.RendezvousDAO;

@Service
public class RendezvousService {
	private static final Logger log = LoggerFactory.getLogger(Application.class);
	
//	private List<Rendezvous> rendezvousList; // version test sans bdd
	
	@Autowired
	private RendezvousDAO rdvdao;
	
	public RendezvousService() {
		/*
		// version test sans bdd, autrement l'init de base est dans Application.java
		rendezvousList.add(new Rendezvous(2, "Saint-Jérôme", "Cours", "Julien, Julie"));
		rendezvousList.add(new Rendezvous(1, "Odéon", "Cinéma", "Luca, Lucie"));
		rendezvousList.add(new Rendezvous(2, "Bercy", "Banque", "James, Kate"));
		 */
	}
	
	public void addRDV(int duree, String lieu, String type, String personnes) {
		Rendezvous rdv = new Rendezvous(duree, lieu, type, personnes);
//		rendezvousList.add(rdv);// version test sans bdd
		rdvdao.add(rdv);
	}
	
	public Rendezvous getRDVByID(int id) {
		return rdvdao.findById(id);
	}
	
	public int getNumRdv() {
//		return rendezvousList.size(); // version test sans bdd
		return rdvdao.findAll().size();
	}
	
	public List<Rendezvous> getRdvPerType(String type) {
		/*
		// version test sans bdd
		List<Rendezvous> rdvs = new ArrayList<Rendezvous>();
		for (Rendezvous rdv : rendezvousList) 
			if (rdv.getType().equals(type)) rdvs.add(rdv);
		return rdvs
		*/
		return rdvdao.findByType(type);
	}
	
	public void deleteRdv(Rendezvous rdv) {
		rdvdao.delete(rdv);
	}
	
	public void visualizeRdvs() {
		List<String> lines = new ArrayList<String>();
		for (Rendezvous rdv : rdvdao.findAll())
			lines.add(rdv.toString());
//		for (Rendezvous rdv : rendezvousList) lines.add(rdv.toString()); // version test sans bdd
		log.info(String.format("VISUALIZATION\n===== BDD CONTENT ROWS =====\n%s", Joiner.on("\n=====\n").join(lines)) );
	}
}
