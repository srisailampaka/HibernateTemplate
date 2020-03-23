package guru.springframework.blog.repositories;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import ch.qos.logback.core.db.dialect.MsSQLDialect;
import guru.springframework.blog.MSSqlConectionPool;
import guru.springframework.blog.domain.PrimusUser;


@RestController
@RequestMapping("/customers")

public class UserController {

	@Autowired
	UserDAOImpl userDAOImpl;
    

    @GetMapping("/id")
    public List<PrimusUser> getCustomers() {
    	List<PrimusUser> userList=new ArrayList<>();
    	/*Connection connection= MSSqlConectionPool.getConnection();
  	Statement statement=connection.createStatement();
  	ResultSet rs=statement.executeQuery("Select *from user_tbl");
  	while (rs.next()) {
		User user=new User();
		user.setId(rs.getInt(1));
		user.setAge(rs.getInt(2));
		user.setName(rs.getString(3));
		userList.add(user);
		
}*/
    	userDAOImpl.save();
    	userList=userDAOImpl.getList();
  	
  	System.out.println(userList);
        return userList;
    }

    
}
