package at.technikum.springrestbackend.repository;

import at.technikum.springrestbackend.model.Playlist;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlaylistRepository extends ListCrudRepository<Playlist, Long> {}
