package at.technikum.springrestbackend.mapper;

import at.technikum.springrestbackend.dto.SongDto;
import at.technikum.springrestbackend.model.Song;
import org.springframework.stereotype.Component;

@Component
public class SongMapper {

    public SongDto toDto(Song song){
        SongDto songDto = new SongDto();
        songDto.setId(song.getId());
        songDto.setName(song.getName());
        songDto.setArtist(song.getArtist());
        songDto.setLength(song.getLength());
        songDto.setLikeCount(song.getLikeCount());
        songDto.setComments(song.getComments());
        songDto.setFileLink(song.getFileLink());
        songDto.setGenre(song.getGenre());
        songDto.setPicture(song.getPicture());

        return songDto;
    }

    public Song toEntity(SongDto songDto){
        if (songDto.getId()==null){
            return new Song (
                    songDto.getName(),
                    songDto.getArtist(),
                    songDto.getLength(),
                    songDto.getLikeCount(),
                    songDto.getComments(),
                    songDto.getFileLink(),
                    songDto.getGenre(),
                    songDto.getPicture()
            );
        }
        return new Song(
                songDto.getId(),
                songDto.getName(),
                songDto.getArtist(),
                songDto.getLength(),
                songDto.getLikeCount(),
                songDto.getComments(),
                songDto.getFileLink(),
                songDto.getGenre(),
                songDto.getPicture()
        );
    }
}
