package in.ashokit.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.ashokit.entity.user;
import in.ashokit.repo.userrepo;
@Service
public class userserviceimpl implements userservice {
	private userrepo repo;
	@Autowired
	public userserviceimpl(userrepo repo)
	{
		this.repo=repo;
	}

	@Override
	public boolean saveuser(user u) {
		// TODO Auto-generated method stub
	    
	    return repo.save(u).getUid()!=null;
    
	}

}
