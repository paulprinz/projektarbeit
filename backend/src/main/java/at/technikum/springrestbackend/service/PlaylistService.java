package at.technikum.springrestbackend.service;

import at.technikum.springrestbackend.dto.PlaylistDto;
import at.technikum.springrestbackend.exception.EntityNotFoundException;
import at.technikum.springrestbackend.model.Playlist;
import at.technikum.springrestbackend.repository.PlaylistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PlaylistService {

    @Autowired
    PlaylistRepository playlistRepository;


    /**
     * Retrieves all playlists from the repository.
     *
     * @return a list of all playlists.
     */
    public List<Playlist> findAll(){
        return playlistRepository.findAll();
    }

    /**
     * Retrieves a playlist by their ID.
     *
     * @param id the ID of the playlist to be retrieved.
     * @return the playlist with the specified ID.
     * @throws EntityNotFoundException if no playlist with the given ID is found.
     */
    public Playlist findById(Long id){
        var result = playlistRepository.findById(id);
        if (result.isEmpty()) {
            throw new EntityNotFoundException("Playlist with id: " + id + " not found.");
        }
        return result.get();
    }

    /**
     * Saves a new playlist to the repository.
     *
     * @param playlistDto the data transfer object containing playlist details.
     * @return the saved playlist.
     * @throws Exception if an error occurs while saving the playlist.
     */
    public Playlist save(PlaylistDto playlistDto) throws Exception {
        try {
            Playlist playlist = new Playlist();
            playlist.setId(playlistDto.getId());
            playlist.setName(playlistDto.getName());
            playlist.setUser_id(playlistDto.getUserId());
            playlist.setSongs(playlistDto.getSongs());

            return playlistRepository.save(playlist);
        } catch (Exception e) {
            throw new Exception("Error saving playlist: " + e.getMessage(), e);
        }
    }

    /**
     * Updates an existing playlist in the repository.
     *
     * @param playlistDto the data transfer object containing updated playlist details.
     * @return the updated playlist.
     * @throws Exception if an error occurs while updating the playlist.
     */
    public Playlist updateById(PlaylistDto playlistDto) throws Exception {
        try {
            Playlist playlist = findById(playlistDto.getId());
            playlist.setId(playlistDto.getId());
            playlist.setName(playlistDto.getName());
            playlist.setUser_id(playlistDto.getUserId());
            playlist.setSongs(playlistDto.getSongs());

            return playlistRepository.save(playlist);
        } catch (Exception e) {
            throw new Exception("Error updating playlist: " + e.getMessage(), e);
        }
    }

    /**
     * Deletes a playlist from the repository by their ID.
     *
     * @param id the ID of the playlist to be deleted.
     * @throws Exception if an error occurs while deleting the playlist.
     */
    public void deleteById(Long id) throws Exception {
        try{
            playlistRepository.deleteById(id);
        } catch (Exception e) {
            throw new Exception("Error deleting playlist: " + e.getMessage(), e);
        }
    }

    /**
     * Converts a Playlist entity to a PlaylistDto.
     *
     * @param playlist the playlist entity to be converted.
     * @return the converted PlaylistDto.
     */
    public PlaylistDto convertToDto(Playlist playlist) {
        return new PlaylistDto(
                playlist.getId(),
                playlist.getName(),
                playlist.getUser_id(),
                playlist.getSongs()
        );
    }

}
