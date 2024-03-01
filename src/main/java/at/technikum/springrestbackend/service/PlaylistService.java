package at.technikum.springrestbackend.service;


import at.technikum.springrestbackend.dto.PlaylistDto;
import at.technikum.springrestbackend.exception.EntityNotFoundException;
import at.technikum.springrestbackend.model.Playlist;
import at.technikum.springrestbackend.model.Song;
import at.technikum.springrestbackend.model.User;
import at.technikum.springrestbackend.repository.PlaylistRepository;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlaylistService {
    private final PlaylistRepository playlistRepository;


    public PlaylistService(PlaylistRepository playlistRepository) {
        this.playlistRepository = playlistRepository;
    }

    public List<Playlist> findAll(){
        return playlistRepository.findAll();
    }

    public Playlist find(Long id){
        return playlistRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
    }


    public Playlist save(Playlist playlist){
        return playlistRepository.save(playlist);
    }

    public void delete(Playlist playlist){
        playlistRepository.delete(playlist);
    }

    public void deleteById(Long id){
        playlistRepository.deleteById(id);
    }

    public Playlist updateById(Long id, PlaylistDto playlistDto) {
        Playlist existingPlaylist = playlistRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);

        existingPlaylist.setName(playlistDto.getName());
        existingPlaylist.setSongs(playlistDto.getSongs());

        return playlistRepository.save(existingPlaylist);
    }
}
