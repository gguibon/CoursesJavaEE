package fr.amu.models;

public class Rendezvous {

	private int id;
	private int duree;
	private String personnes; // personnes est un simple stringp pour ce test, il serait préférable d'avoir une relation dans la bdd avec un bean person
	private String lieu;
	private String type;

	public Rendezvous() {
	}

	public Rendezvous( int duree, String lieu, String type, String personnes) {
		this.duree = duree;
		this.lieu = lieu;
		this.type = type;
		this.personnes = personnes;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public int getDuree() {
		return duree;
	}

	public void setDuree(int duree) {
		this.duree = duree;
	}

	public String getPersonnes() {
		return personnes;
	}

	public void setPersonnes(String personnes) {
		this.personnes = personnes;
	}

	public String getLieu() {
		return lieu;
	}

	public void setLieu(String lieu) {
		this.lieu = lieu;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getNumPersonnes() {
		return personnes.split(", ").length;
	}

	@Override
	public String toString() {
	return String.format(
	 "RDV[id='%s', lieu='%s', duree='%s', type='%s', personnes='%s']",
	 String.valueOf(id), lieu, String.valueOf(duree), type, personnes );
	}

}