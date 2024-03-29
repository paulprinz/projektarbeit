package at.technikum.springrestbackend.mapper;

import at.technikum.springrestbackend.dto.PlaylistDto;
import at.technikum.springrestbackend.model.Playlist;
import org.springframework.stereotype.Component;

@Component
public class PlaylistMapper {

    public PlaylistDto toDto(Playlist playlist){
        PlaylistDto playlistDto = new PlaylistDto();
        playlistDto.setId(playlist.getId());
        playlistDto.setName(playlist.getName());
        playlistDto.setCreator(playlist.getCreator());
        playlistDto.setSongs(playlist.getSongs());

        return playlistDto;
    }

    public Playlist toEntity(PlaylistDto playlistDto){
        if (playlistDto.getId()==null){
            return new Playlist (
                    playlistDto.getName(),
                    playlistDto.getCreator(),
                    playlistDto.getSongs()
            );
        }
        return new Playlist(
                playlistDto.getId(),
                playlistDto.getName(),
                playlistDto.getCreator(),
                playlistDto.getSongs()
        );
    }
}
