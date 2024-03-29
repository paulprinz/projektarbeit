package at.technikum.springrestbackend.service;


import at.technikum.springrestbackend.dto.SongDto;
import at.technikum.springrestbackend.dto.UserDto;
import at.technikum.springrestbackend.exception.EntityNotFoundException;
import at.technikum.springrestbackend.model.Picture;
import at.technikum.springrestbackend.model.Song;
import at.technikum.springrestbackend.model.User;
import at.technikum.springrestbackend.repository.SongRepository;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Transient;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SongService {
    private final SongRepository songRepository;


    public SongService(SongRepository songRepository) {
        this.songRepository = songRepository;
    }

    public List<Song> findAll(){
        return songRepository.findAll();
    }
    
    public Song find(Long id){
        return songRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
    }


    public Song save(Song song){
        return songRepository.save(song);
    }

    public void delete(Song song){
        songRepository.delete(song);
    }

    public void deleteById(Long id){
        songRepository.deleteById(id);
    }

    public Song updateById(Long id, SongDto songDto) {
        Song existingSong = songRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);

        existingSong.setName(songDto.getName());
        existingSong.setArtist(songDto.getArtist());
        existingSong.setLikeCount(songDto.getLikeCount());
        existingSong.setComments(songDto.getComments());
        existingSong.setPicture(songDto.getPicture());

        return songRepository.save(existingSong);
    }
}
