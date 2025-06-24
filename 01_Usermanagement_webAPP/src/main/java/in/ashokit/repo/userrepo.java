package in.ashokit.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import in.ashokit.entity.user;

public interface userrepo extends JpaRepository<user, Integer> {
  public user findByUemail(String uemail);




}
