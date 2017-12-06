package fr.amu.models;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface MessageRepository extends CrudRepository<Message, String> {

	public List<Message> findAllByOrderByDateAsc();
}