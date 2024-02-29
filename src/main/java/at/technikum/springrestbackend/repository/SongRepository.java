package at.technikum.springrestbackend.repository;

import at.technikum.springrestbackend.model.Picture;
import at.technikum.springrestbackend.model.Song;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SongRepository extends ListCrudRepository<Song, Long> {}
