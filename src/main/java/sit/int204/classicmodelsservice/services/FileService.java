package sit.int204.classicmodelsservice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import sit.int204.classicmodelsservice.exceptions.ResourceNotFoundException;
import sit.int204.classicmodelsservice.properties.FileStorageProperties;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Locale;

@Service
public class FileService{
    private final Path fileStorageLocation;
    @Autowired
    public FileService(FileStorageProperties fileStorageProperties){
        this.fileStorageLocation= Paths.get(fileStorageProperties.getUploadDir()).toAbsolutePath().normalize();
        try {
            Files.createDirectories(this.fileStorageLocation);
        } catch (Exception ex) {
            throw new RuntimeException("Could not create the directory where the uploaded files will be stored.", ex);
        }
    }
    public String store(MultipartFile file) {
//        String x = null;
//        x.toLowerCase(Locale.ROOT);
        // Normalize file name
        String fileName= StringUtils.cleanPath(file.getOriginalFilename());
        try {
            // Check if the file's name contains invalid characters
            if (fileName.contains("..")) {
                throw new RuntimeException("Sorry! Filename contains invalid path sequence " + fileName);
            }
            // Copy file to the target location (Replacing existing file with the same name)
            Path targetLocation= this.fileStorageLocation.resolve(fileName);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
            return fileName;
        }
        catch (IOException ex) {
            throw new RuntimeException("Could not store file " + fileName+ ". Please try again!", ex);
        }
    }
    public Resource loadFileAsResource(String fileName) {
//        Integer.valueOf("asde");
        try {
            Path filePath= this.fileStorageLocation.resolve(fileName).normalize();
            Resource resource = new UrlResource(filePath.toUri());
            return resource;
//            if (resource.exists()) {
//                return resource;
//          }  else {
//                throw new ResourceNotFoundException("[WARNING]: File not found " + fileName);
//            }
        } catch (MalformedURLException ex) {
            throw new RuntimeException("File operation error: " + fileName, ex);
        }
    }
}