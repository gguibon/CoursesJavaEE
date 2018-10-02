package fr.amu.models;

import javax.persistence.Entity;
import javax.persistence.Id;


@Entity
public class User {

    @Id
    private String name;
    private String password;

    public User() {}

    public User(String name, String password) {
        this.name = name;
        this.password = password;
    }

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}   
	
	@Override
    public String toString() {
        return String.format(
                "User[name='%s', pwd='%s']",
                name, password );
    }

}