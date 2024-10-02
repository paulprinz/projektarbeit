package at.technikum.springrestbackend.service;

import at.technikum.springrestbackend.minio.MinioService;
import at.technikum.springrestbackend.model.Song;
import at.technikum.springrestbackend.model.User;
import at.technikum.springrestbackend.repository.SongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.Optional;
import java.util.UUID;

@Service
public class SongService {

    @Autowired
    private SongRepository songRepository;

    @Autowired
    private MinioService minioService;

    /**
     * Retrieves a song by its ID.
     *
     * @param id the ID of the song to retrieve
     * @return an {@link Optional<Song>} containing the song if found,
     * or an empty {@link Optional} if no song exists with the given ID
     */
    public Optional<Song> findSongById(Long id) {
        return songRepository.findById(id);
    }

    /**
     * Downloads the song file associated with the specified song.
     *
     * @param song the Song object containing the file details
     * @param songBucketName the name of the Minio bucket where the song is stored
     * @return an InputStream of the downloaded song file
     * @throws Exception if there is an error during the file download process
     */
    public InputStream downloadSongFile(Song song, String songBucketName) throws Exception {
        return minioService.downloadFile(songBucketName, song.getFileName());
    }

    /**
     * Uploads a song file and saves it to the database.
     *
     * @param file the MultipartFile representing the song to be uploaded
     * @param songBucketName the name of the Minio bucket where the file will be uploaded
     * @param user the User who is uploading the song
     * @return the saved Song object containing file details
     * @throws Exception if there is an error during the upload or saving process
     */
    public Song uploadSong(MultipartFile file, String songBucketName, User user) throws Exception {
        // Generate a unique file name
        String originalFileName = file.getOriginalFilename();
        String uniqueFileName = UUID.randomUUID().toString() + "_" + originalFileName;

        // Upload the song to Minio
        minioService.uploadFile(songBucketName, uniqueFileName, file);

        // Save the song in the database
        Song song = new Song();
        song.setFileName(uniqueFileName);
        song.setUser(user);
        songRepository.save(song);

        return song;
    }

    /**
     * Deletes the specified song from Minio and the database.
     *
     * @param song the Song object to be deleted
     * @param songBucketName the name of the Minio bucket where the song is stored
     * @throws Exception if there is an error during the deletion process
     */
    public void deleteSong(Song song, String songBucketName) throws Exception {
        // Delete the song from Minio
        minioService.deleteFile(songBucketName, song.getFileName());

        // Remove the song from the database
        songRepository.deleteById(song.getId());
    }

    /**
     * Checks if the uploaded file is a valid audio type (MPEG or WAV).
     *
     * @param file the MultipartFile to be validated
     * @return true if the file type is valid, false otherwise
     */
    public boolean isValidFileType(MultipartFile file) {
        String contentType = file.getContentType();
        return "audio/mpeg".equals(contentType) || "audio/wav".equals(contentType);
    }

}
