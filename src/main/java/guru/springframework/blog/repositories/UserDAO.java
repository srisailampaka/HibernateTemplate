package guru.springframework.blog.repositories;

import java.util.List;

import guru.springframework.blog.domain.PrimusUser;

public interface UserDAO {
	public void create();
	public void save();
	public List<PrimusUser> getList();

}
