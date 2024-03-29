package at.technikum.springrestbackend.repository;

import at.technikum.springrestbackend.model.Picture;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PictureRepository extends ListCrudRepository<Picture, Long> {}
