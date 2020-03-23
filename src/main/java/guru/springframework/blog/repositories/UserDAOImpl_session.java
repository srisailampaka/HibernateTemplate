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
public class UserDAOImpl implements UserDAO{

	@Override
	public void create() {
		
		
	}

	@Override
	public void save() {
		// TODO Auto-generated method stub
		Session session = null;
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
			session = MSSqlConectionPool.getCurrentSession();
			 PrimusUser primusUser=new PrimusUser();
			 primusUser.setAge(11);
			 primusUser.setName("Srisailam");
	         session.save(primusUser);
	            session.flush();    
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		
		} finally {
	        try {if(session != null) session.close();} catch(Exception ex) {}
	    }
	}

	@Override
	public List<PrimusUser> getList() {
		// TODO Auto-generated method stub
     Session session = null;
     List<PrimusUser> primusUserList = new ArrayList<PrimusUser>();
	try {
		session = MSSqlConectionPool.getCurrentSession();
		  String queryStr = "from PrimusUser up";
            Query query = session.createQuery(queryStr);
            primusUserList = query.list();    
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	
	} finally {
        try {if(session != null) session.close();} catch(Exception ex) {}
    }
		
		//session.beginTransaction();
		//HibernateTemplate hibernateTemplate=new HibernateTemplate(session.getSessionFactory());
		//hibernateTemplate.setCheckWriteOperations(false);
		//return (List<PrimusUser>) hibernateTemplate.find("from user_primus");
	
		return primusUserList;
	}

}
