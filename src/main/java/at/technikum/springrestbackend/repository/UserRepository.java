package at.technikum.springrestbackend.repository;

import at.technikum.springrestbackend.model.User;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends ListCrudRepository<User, String> {}
