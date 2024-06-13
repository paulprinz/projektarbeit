package at.technikum.springrestbackend.service;

import at.technikum.springrestbackend.dto.SongDto;
import at.technikum.springrestbackend.exception.EntityNotFoundException;
import at.technikum.springrestbackend.model.Song;
import at.technikum.springrestbackend.repository.SongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class SongService {

    @Autowired
    SongRepository songRepository;


    /**
     * Retrieves all songs from the repository.
     *
     * @return a list of all songs.
     */
    public List<Song> findAll(){
        return songRepository.findAll();
    }

    /**
     * Retrieves a song by their ID.
     *
     * @param id the ID of the song to be retrieved.
     * @return the song with the specified ID.
     * @throws EntityNotFoundException if no song with the given ID is found.
     */
    public Song findById(Long id) throws EntityNotFoundException{
        var result = songRepository.findById(id);
        if (result.isEmpty()) {
            throw new EntityNotFoundException("Song with id: " + id + " not found.");
        }
        return result.get();
    }

    /**
     * Saves a new song to the repository.
     *
     * @param songDto the data transfer object containing song details.
     * @return the saved song.
     * @throws Exception if an error occurs while saving the song.
     */
    public Song save(SongDto songDto) throws Exception {
        try {
            Song song = new Song();
            song.setId(songDto.getId());
            song.setName(songDto.getName());
            song.setArtist(songDto.getArtist());
            song.setLength(songDto.getLength());
            song.setLikeCount(songDto.getLikeCount());
            song.setComments(songDto.getComments());
            song.setFileLink(songDto.getFileLink());
            song.setGenre(songDto.getGenre());
            song.setUser_id(songDto.getUser_id());

            return songRepository.save(song);
        } catch (Exception e) {
            throw new Exception("Error saving song: " + e.getMessage(), e);
        }
    }

    /**
     * Updates an existing song in the repository.
     *
     * @param songDto the data transfer object containing updated song details.
     * @return the updated song.
     * @throws Exception if an error occurs while updating the song.
     */
    public Song updateById(SongDto songDto) throws Exception {
        try {
            Song existingSong = findById(songDto.getId());

            existingSong.setName(songDto.getName());
            existingSong.setArtist(songDto.getArtist());
            existingSong.setLength(songDto.getLength());
            existingSong.setLikeCount(songDto.getLikeCount());
            existingSong.setComments(songDto.getComments());
            existingSong.setFileLink(songDto.getFileLink());
            existingSong.setGenre(songDto.getGenre());
            existingSong.setUser_id(songDto.getUser_id());

            return songRepository.save(existingSong);
        } catch (Exception e) {
            throw new Exception("Error updating Song: " + e.getMessage(), e);
        }
    }

    /**
     * Deletes a song from the repository by their ID.
     *
     * @param id the ID of the song to be deleted.
     * @throws Exception if an error occurs while deleting the song.
     */
    public void deleteById(Long id) throws Exception {
        try {
            songRepository.deleteById(id);
        } catch (Exception e) {
            throw new Exception("Error deleting song: " + e.getMessage(), e);
        }
    }

    /**
     * Converts a Song entity to a SongDto.
     *
     * @param song the song entity to be converted.
     * @return the converted SongDto.
     */
    public SongDto convertToDto(Song song) {
        return new SongDto(
                song.getId(),
                song.getName(),
                song.getArtist(),
                song.getLength(),
                song.getLikeCount(),
                song.getComments(),
                song.getFileLink(),
                song.getGenre(),
                song.getUser_id()
        );
    }

}
