package at.technikum.springrestbackend.service;


import at.technikum.springrestbackend.exception.EntityNotFoundException;
import at.technikum.springrestbackend.model.Song;
import at.technikum.springrestbackend.repository.SongRepository;
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

//    public SongDto updateById(String id, SongDto songDto){
//
//
//
//        return new SongDto(
//                id,
//                songDto.getNickname(),
//                songDto.get
//                );
//    }
//

}
