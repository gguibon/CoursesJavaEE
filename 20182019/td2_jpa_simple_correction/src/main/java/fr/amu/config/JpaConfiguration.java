package fr.amu.config;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.eclipse.persistence.config.PersistenceUnitProperties; 
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.autoconfigure.orm.jpa.JpaBaseConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.autoconfigure.transaction.TransactionManagerCustomizers;
import org.springframework.context.annotation.Configuration;
import org.springframework.instrument.classloading.InstrumentationLoadTimeWeaver;
import org.springframework.orm.jpa.vendor.AbstractJpaVendorAdapter;
import org.springframework.orm.jpa.vendor.EclipseLinkJpaVendorAdapter;
import org.springframework.transaction.jta.JtaTransactionManager;

/**
 * JPA est une spécification, nous avons besoin d'une implémentation.
 * Ici nous choisissons celle de eclipse link : import org.eclipse.persistence.config.PersistenceUnitProperties; 
 * @author gael
 *
 */
@Configuration // annotation indiquant à Spring une configuration du contexte général. On utilise les outils d'auto configuration de spring boot JpaBaseConfiguration etc.
public class JpaConfiguration extends JpaBaseConfiguration {

    protected JpaConfiguration(DataSource dataSource, JpaProperties properties, ObjectProvider<JtaTransactionManager> jtaTransactionManager, 
    		ObjectProvider<TransactionManagerCustomizers> transactionManagerCustomizers) {
        super(dataSource, properties, jtaTransactionManager, transactionManagerCustomizers);
    }

    @Override // permet d'adapter le JPA en fonction de l'implémentation (ici EclipseLink)
    protected AbstractJpaVendorAdapter createJpaVendorAdapter() {
        return new EclipseLinkJpaVendorAdapter();
    }

    @Override // on prend les propriétés propres à l'implémeentation du PersistenceUnit
    protected Map<String, Object> getVendorProperties() {
        HashMap<String, Object> map = new HashMap<>();
        map.put(PersistenceUnitProperties.WEAVING, detectWeavingMode());
        map.put(PersistenceUnitProperties.DDL_GENERATION, "drop-and-create-tables");
        return map;
    }

    private String detectWeavingMode() {
        return InstrumentationLoadTimeWeaver.isInstrumentationAvailable() ? "true" : "static";
    }
}
