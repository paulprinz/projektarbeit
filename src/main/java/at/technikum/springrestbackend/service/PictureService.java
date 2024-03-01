package at.technikum.springrestbackend.service;


import at.technikum.springrestbackend.exception.EntityNotFoundException;
import at.technikum.springrestbackend.model.Picture;
import at.technikum.springrestbackend.repository.PictureRepository;
import at.technikum.springrestbackend.repository.PictureRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PictureService {
    private final PictureRepository pictureRepository;


    public PictureService(PictureRepository pictureRepository) {
        this.pictureRepository = pictureRepository;
    }


    public Picture find(Long id){
        return pictureRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
    }


    public Picture save(Picture picture){
        return pictureRepository.save(picture);
    }

    public void delete(Picture picture){
        pictureRepository.delete(picture);
    }

    public void deleteById(Long id){
        pictureRepository.deleteById(id);
    }
}
