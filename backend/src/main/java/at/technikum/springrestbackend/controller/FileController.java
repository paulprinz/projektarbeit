package at.technikum.springrestbackend.controller;

import at.technikum.springrestbackend.minio.MinioService;
import at.technikum.springrestbackend.model.Picture;
import at.technikum.springrestbackend.model.User;
import at.technikum.springrestbackend.repository.PictureRepository;
import at.technikum.springrestbackend.security.principal.UserPrincipal;
import at.technikum.springrestbackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.Optional;

@RestController
@RequestMapping("api/files")
@CrossOrigin
public class FileController {

    @Autowired
    private MinioService minioService;

    @Autowired
    UserService userService;

    @Autowired
    PictureRepository pictureRepository;

    @Value("${minio.bucket-name}")
    private String pictureBucketName;


    @PostMapping("/upload/avatar")
    public ResponseEntity<String> uploadAvatar(
            @AuthenticationPrincipal UserPrincipal userPrincipal,
            @RequestParam("file") MultipartFile file) {

        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body("File is empty.");
        }
        try {
            User user = userService.findByName(userPrincipal.getUsername());
            // Generate a unique file name
            String originalFileName = file.getOriginalFilename();
            String uniqueFileName = user.getId() + "_" + originalFileName;

            // Upload to Minio
            minioService.uploadFile(pictureBucketName, uniqueFileName, file);

            // Save to DB
            Picture picture = new Picture();
            picture.setFileName(uniqueFileName);
            picture.setUser(user);
            pictureRepository.save(picture);

            return ResponseEntity.status(HttpStatus.CREATED).body("File uploaded successfully!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred: " + e.getMessage());
        }
    }

    @GetMapping("/download/avatar/{userId}")
    public ResponseEntity<InputStreamResource> downloadAvatar(
            @PathVariable Long userId) {
        try {
            Optional<Picture> pictureOpt = pictureRepository.findByUser(userId);
            if (pictureOpt.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
            }

            Picture picture = pictureOpt.get();
            InputStream inputStream = minioService.downloadFile(pictureBucketName, picture.getFileName());
            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + picture.getFileName() + "\"")
                    .body(new InputStreamResource(inputStream));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @DeleteMapping("/delete/avatar/{userId}")
    public ResponseEntity<String> deleteAvatar(
            @AuthenticationPrincipal UserPrincipal userPrincipal,
            @PathVariable Long userId) {

        try {
            User user = userService.findById(userId);
            if (user == null || !userPrincipal.getId().equals(user.getId())) {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body("You are not allowed to delete this photo.");
            }

            // Retrieve the existing photo
            Optional<Picture> existingPhotoOpt = pictureRepository.findByUser(userId);
            if (existingPhotoOpt.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No photo found for this user.");
            }

            // Delete the file from Minio
            Picture existingPhoto = existingPhotoOpt.get();
            minioService.deleteFile(pictureBucketName, existingPhoto.getFileName());

            // Remove the picture from the database
            userService.deletePictureByUserId(userId);

            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Photo deleted successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred: " + e.getMessage());
        }
    }

}
