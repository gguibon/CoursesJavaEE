package fr.amu.models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public class RendezvousDAOImpl implements RendezvousDAO {
	@Autowired
	JdbcTemplate jt;
	
	@Override
	public void add(Rendezvous rdv) {
		String sql = "INSERT INTO rendezvous(duree,lieu,type,personnes) values(?,?,?,?)";
		jt.update(sql, rdv.getDuree(), rdv.getLieu(), rdv.getType(), rdv.getPersonnes());
	}

	@Override
	public void update(Rendezvous rdv) {
		
	}

	@Override
	public void delete(Rendezvous rdv) {
		String sql = "DELETE FROM rendezvous WHERE id = ?";
		jt.update(sql, rdv.getId());
	}

	@Override
	public Rendezvous findById(Integer id) {
		String sql = "SELECT * FROM rendezvous WHERE id = ?";
		RowMapper<Rendezvous> rowMapper = new BeanPropertyRowMapper<Rendezvous>(Rendezvous.class);
		return jt.queryForObject(sql, rowMapper, id);
	}

	@Override
	public List<Rendezvous> findByType(String type) {
		return jt.query("SELECT * FROM rendezvous WHERE type = ?", new RDVRowMapper(), type);
	} 
	
	@Override
	public List<Rendezvous> findAll() {
        return jt.query("select * from rendezvous", 
                new RDVRowMapper());
    }
}

/**
 * Classe permettant de définir ce qui sera récupéré à chaque ligne (row) de manière personnalisée.
 * Autrement, un BeanPropertyRowMapper serait utile pour simplement mapper en fonction de sa définition (voir méthode findById())
 * @author gael
 *
 */
class RDVRowMapper implements RowMapper<Rendezvous>
{
    @Override
    public Rendezvous mapRow(ResultSet rs, int rowNum) throws SQLException {
        Rendezvous rdv = new Rendezvous();
        rdv.setId(rs.getInt("id"));
        rdv.setDuree(rs.getInt("duree"));
        rdv.setLieu(rs.getString("lieu"));
        rdv.setType(rs.getString("type"));
        rdv.setPersonnes(rs.getString("personnes"));
        return rdv;
    }
}

