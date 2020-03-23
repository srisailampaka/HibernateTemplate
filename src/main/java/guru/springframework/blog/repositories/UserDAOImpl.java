package guru.springframework.blog.repositories;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.FlushMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import guru.springframework.blog.MSSqlConectionPool;
import guru.springframework.blog.domain.PrimusUser;


@Repository
public class UserDAOImpl {

	
	public void create() {
		
		
	}
	
	public void save() {
		// TODO Auto-generated method stub
		
	/*	try {
			Session session = MSSqlConectionPool.getCurrentSession();
		
		//session.beginTransaction();
		HibernateTemplate hibernateTemplate=new HibernateTemplate(session.getSessionFactory());
		//hibernateTemplate.setCheckWriteOperations(false);
		//userList=(List<User>) hibernateTemplate.find("from user_tbl");
		hibernateTemplate.getSessionFactory().getCurrentSession().setFlushMode(FlushMode.AUTO);
		PrimusUser user=new PrimusUser();
		user.setAge(12);
        user.setName("srisailam");
		hibernateTemplate.save(user);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		try {
			HibernateTemplate hibernateTemplate=new HibernateTemplate(MSSqlConectionPool.getSessionFactory());
			
			 PrimusUser primusUser=new PrimusUser();
			 primusUser.setAge(11);
			 primusUser.setName("Srisailam");
			 hibernateTemplate.save(primusUser);
	               
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		
		} 
	}

	
	public List<PrimusUser> getList() {
		// TODO Auto-generated method stub
     List<PrimusUser> primusUserList = new ArrayList<PrimusUser>();
	try {
		HibernateTemplate hibernateTemplate=new HibernateTemplate(MSSqlConectionPool.getSessionFactory());
		String queryString = "select age,name from PrimusUser";
		primusUserList=(List<PrimusUser>) hibernateTemplate.find(queryString); 
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	
	} 
		
		//session.beginTransaction();
		//HibernateTemplate hibernateTemplate=new HibernateTemplate(session.getSessionFactory());
		//hibernateTemplate.setCheckWriteOperations(false);
		//return (List<PrimusUser>) hibernateTemplate.find("from user_primus");
	
		return primusUserList;
	}

}
