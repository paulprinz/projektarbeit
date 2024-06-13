package at.technikum.springrestbackend.service;

import at.technikum.springrestbackend.dto.PictureDto;
import at.technikum.springrestbackend.exception.EntityNotFoundException;
import at.technikum.springrestbackend.model.Picture;
import at.technikum.springrestbackend.repository.PictureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class PictureService {

    @Autowired
    PictureRepository pictureRepository;

    /**
     * Retrieves a picture by their ID.
     *
     * @param id the ID of the picture to be retrieved.
     * @return the picture with the specified ID.
     * @throws EntityNotFoundException if no picture with the given ID is found.
     */
    public Picture findById(Long id) throws EntityNotFoundException {
        var result = pictureRepository.findById(id);
        if (result.isEmpty()) {
            throw new EntityNotFoundException("Picture with id: " + id + " not found.");
        }
        return result.get();
    }

    /**
     * Saves a new picture to the repository.
     *
     * @param pictureDto the data transfer object containing picture details.
     * @return the saved picture.
     * @throws Exception if an error occurs while saving the picture.
     */
    public Picture save(PictureDto pictureDto) throws Exception{
        try {
            Picture picture = new Picture();
            picture.setId(pictureDto.getId());
            picture.setUserId(pictureDto.getUserId());
            picture.setFileLink(pictureDto.getFileLink());

            return pictureRepository.save(picture);
        } catch (Exception e) {
            throw new Exception("Error saving Picture: " + e.getMessage(), e);
        }
    }

    /**
     * Deletes a picture from the repository by their ID.
     *
     * @param id the ID of the picture to be deleted.
     * @throws Exception if an error occurs while deleting the picture.
     */
    public void deleteById(Long id) throws Exception {
        try {
            pictureRepository.deleteById(id);
        } catch (Exception e) {
            throw new Exception("Error picture user: " + e.getMessage(), e);
        }
    }

    /**
     * Converts a Picture entity to a PictureDto.
     *
     * @param picture the picture entity to be converted.
     * @return the converted PictureDto.
     */
    public PictureDto convertToDto(Picture picture) {
        return new PictureDto(
                picture.getId(),
                picture.getFileLink(),
                picture.getUserId()
        );
    }

}
