package guru.springframework.blog;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Environment;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;

import com.microsoft.sqlserver.jdbc.SQLServerXADataSource;

import guru.springframework.blog.domain.BmrUser;
import guru.springframework.blog.domain.PrimusUser;

public class MSSqlConectionPool {
	 private static SQLServerXADataSource ds = new SQLServerXADataSource();
	 private static SessionFactory sf ;
     
	    static {
	        ds.setApplicationName("PrimusTesting");
	        ds.setDatabaseName("PRIMUS");
	        ds.setServerName("DESKTOP-1Q7CTAU");
	        
	        ds.setPortNumber(1433);
	        ds.setIntegratedSecurity(true);
	        Properties properties = new Properties();
	        
	        // See: application.properties
	        properties.put(Environment.DIALECT,"org.hibernate.dialect.SQLServerDialect");
	        properties.put(Environment.SHOW_SQL, "true");
	        properties.put(Environment.CURRENT_SESSION_CONTEXT_CLASS,"org.springframework.orm.hibernate5.SpringSessionContext");
	        properties.put(Environment.DEFAULT_SCHEMA,"dbo");
	        properties.put(Environment.HBM2DDL_AUTO,"update");
	         
	        // Fix Postgres JPA Error:
	        // Method org.postgresql.jdbc.PgConnection.createClob() is not yet implemented.
	        // properties.put("hibernate.temp.use_jdbc_metadata_defaults",false);
	 
	        LocalSessionFactoryBean factoryBean = new LocalSessionFactoryBean();
	 
	        // Package contain entity classes
	        //factoryBean.setPackagesToScan(new String[] { "" });
	        Class<?>[] annotatedClasses;
	        if(ds.getDatabaseName().equalsIgnoreCase("primus")) {
	       annotatedClasses = new Class<?>[]{PrimusUser.class};
	        }else
	        {
	        	 annotatedClasses = new Class<?>[]{BmrUser.class};
	        }
	        factoryBean.setAnnotatedClasses(annotatedClasses);
	        factoryBean.setDataSource(ds);
	        factoryBean.setHibernateProperties(properties);
	        try {
				factoryBean.afterPropertiesSet();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        //
	        sf= factoryBean.getObject();
	        
	    }
	     
	    public static Connection getConnection() throws SQLException {
	        return ds.getConnection();
	    }
	    public static Session getCurrentSession() throws SQLException {
	    	Session session;
	    	try {
	    	    session = sf.getCurrentSession();
	    	} catch (HibernateException e) {
	    	    session = sf.openSession();
	    	}
	        return session;
	    }
	    public static SessionFactory getSessionFactory() throws SQLException {
	    	
	        return sf;
	    }
	  //  spring.datasource.url=jdbc:sqlserver://localhost;databaseName=PRIMUS;integratedSecurity=true
	    //	spring.datasource.driverClassName=com.microsoft.sqlserver.jdbc.SQLServerDriver
	   // spring.jpa.show-sql=true
	   // 		spring.jpa.hibernate.dialect=org.hibernate.dialect.SQLServer2012Dialect
	    //		spring.jpa.hibernate.ddl-auto = create
	    private MSSqlConectionPool(){ }
}
