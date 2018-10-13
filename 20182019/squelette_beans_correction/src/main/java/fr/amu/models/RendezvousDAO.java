package fr.amu.models;

import java.util.List;

public interface RendezvousDAO {
	void add(Rendezvous rdv);
	void update(Rendezvous rdv);
	void delete(Rendezvous rdv);
	List<Rendezvous> findAll();
	Rendezvous findById( Integer id );
	List<Rendezvous> findByType( String type );
}

