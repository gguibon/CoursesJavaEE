package fr.amu.models;

import java.sql.PreparedStatement;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public class ProductDAOImpl implements ProductDAO {
	
	@Autowired
	JdbcTemplate jt;
 
	@Override
	public Integer add(Product product) {
		String sql = "INSERT INTO products(category,productTitle,img,description,date) values(?,?,?,?,?)";
		GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
		// nom d ela column générée
		String id_column = "ID";
		// the update method takes an implementation of PreparedStatementCreator which could be a lambda, la méthode update a besoin d'un preparedSatementCreator pour modifier sa valeur de sortie
		// 'con' est la connection JDBC cachée par JDBCTemplate
		jt.update(con -> {
		    PreparedStatement ps = con.prepareStatement(sql, 
		    // comme avant on suit l'ordre des ? dans la requete SQL
		    new String[]{id_column}); // cet array de String correspond aux clés utilisées pour récupérer les valeur 
		    //(ordre important, ici en premier car ID est en premier dans CREATE TABLE)
		    ps.setString(1, product.getCategory());
		    ps.setString(2, product.getProductTitle());
		    ps.setString(3, product.getImg());
		    ps.setString(4, product.getDescription());
		    ps.setString(5, product.getDate());
		    return ps;
		  }
		  , keyHolder); // ceci va capturer les valeurs de sortie voulues dans une hashmap
	
		 // after the update executed we can now get the value of the generated ID
		// une fois l'update faite on récupère les valeurs grâce à la hashmap de .getKeys(). 
		Integer id = (Integer) keyHolder.getKeys().get(id_column);
		 return id.intValue();
	}

	@Override
	public void update(Product product) {
			
	}

	@Override
	public void delete(Product product) {
		String sql = "DELETE FROM products WHERE id = ?";
		jt.update(sql, product.getId());	
	}

	@Override
	public List<Product> findAll() {
		RowMapper<Product> rowMapper = new BeanPropertyRowMapper<Product>(Product.class);
		return jt.query("SELECT * FROM products", rowMapper);
	}

	@Override
	public Product findById(Integer id) {
		String sql = "SELECT * FROM products WHERE id = ?";
		RowMapper<Product> rowMapper = new BeanPropertyRowMapper<Product>(Product.class);
		return jt.queryForObject(sql, rowMapper, id);
	}

	@Override
	public List<Product> findByCategory(String category) {
		RowMapper<Product> rowMapper = new BeanPropertyRowMapper<Product>(Product.class);
		return jt.query("SELECT * FROM products WHERE category = ?", rowMapper, category);
	}

	
}

