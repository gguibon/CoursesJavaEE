package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
	Session session;
	Statement st;
	Connection conn;
	public HibernateUtil() {
		try {
			Configuration configuration = new Configuration();
            configuration.configure("hibernate.cfg.xml");
            StandardServiceRegistryBuilder ssrb = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
            SessionFactory sessionFactory = configuration.buildSessionFactory(ssrb.build());
			session = sessionFactory.openSession();


			// Load the JDBC driver.
			Class.forName("org.hsqldb.jdbcDriver");
//			Class.forName("org.hsqldb.server.WebServer");
			System.out.println("Driver Loaded.");
			// Establish the connection to the database.
			String url = "jdbc:hsqldb:file:data/bdd";
//			String url = "jdbc:hsqldb:hsql://localhost/database1";

			conn = DriverManager.getConnection(url, "sa", "");
			System.out.println("Got Connection.");
			st = conn.createStatement();
			
			
			
			executeSQLCommand(
					"create table IF NOT EXISTS message (id varchar(255) NOT NULL,content varchar(255), author varchar(255), date varchar(255), "
					+ " PRIMARY KEY (id)"
					+ ");");		
			executeSQLCommand(
					"create table IF NOT EXISTS user (name varchar(255) NOT NULL, password varchar(255), "
					+ "PRIMARY KEY (name) );");
			executeSQLCommand("MERGE INTO user USING (VALUES('gael', 'jaja'), ('lannister', 'jaja')) " + 
			"   AS vals(x,y) ON user.name = vals.x " +  
			"   WHEN NOT MATCHED THEN INSERT VALUES vals.x, vals.y");
			executeSQLCommand("CREATE TABLE IF NOT EXISTS  liike (" + 
					"  liike_id int NOT NULL," + 
					"  id varchar(255) NOT NULL," + 
					"  liike_author varchar(255) NOT NULL," + 
					"  PRIMARY KEY (liike_id)," + 
					"  CONSTRAINT fk_messageid FOREIGN KEY (id) REFERENCES message(id) ON DELETE CASCADE ON UPDATE CASCADE\n" + 
					");");
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Session getSession() {
		return session;
	}

	public void executeSQLCommand(String sql) throws Exception {
		st.executeUpdate(sql);
	}

	public void checkData(String sql) throws Exception {
		ResultSet rs = st.executeQuery(sql);
		ResultSetMetaData metadata = rs.getMetaData();

		for (int i = 0; i < metadata.getColumnCount(); i++) {
			System.out.print("\t" + metadata.getColumnLabel(i + 1));
		}
		System.out.println("\n----------------------------------");

		while (rs.next()) {
			for (int i = 0; i < metadata.getColumnCount(); i++) {
				Object value = rs.getObject(i + 1);
				if (value == null) {
					System.out.print("\t       ");
				} else {
					System.out.print("\t" + value.toString().trim());
				}
			}
			System.out.println("");
		}
	}
	
	
	public void quit() throws Exception {
		// Close connexion to the database and save changes
		System.out.println("Closing database ...");
		Statement statement = conn.createStatement();
		statement.executeQuery("SHUTDOWN");
		statement.close();
		conn.close();
		System.out.println("Database closed and changes saved!");
	}
}
