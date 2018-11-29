package fr.amu.models;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public class ProductDAOImpl implements ProductDAO {
	
	@Autowired
	JdbcTemplate jt;
 
	@Override
	public void add(Product product) {
		String sql = "INSERT INTO products(category,productTitle,img,description,date) values(?,?,?,?,?)";
		jt.update(sql, product.getCategory(), product.getProductTitle(), product.getImg(), product.getDescription(), product.getDate());
	}

	@Override
	public void update(Product product) {
			// nous n'avonspas implémenté la modification d'un produit dans la vue donc ce n'est jamais utilisé
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

