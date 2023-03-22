package sit.int204.classicmodelsservice.controllers;


import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MultipartFile;
import sit.int204.classicmodelsservice.exceptions.ErrorResponse;
import sit.int204.classicmodelsservice.services.FileService;

import java.io.FileNotFoundException;

@RestController
    @RequestMapping("/api/files")
    public class FileController{
        private final FileService fileService;
        @Autowired
        public FileController(FileService fileService) {
            this.fileService= fileService;
        }
        @GetMapping("/{filename:.+}")
        @ResponseBody
        public ResponseEntity<Resource> serveFile(@PathVariable String filename) {
            Resource file = fileService.loadFileAsResource(filename);
            return  ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(file);
        }

        @PostMapping("")
        public String fileUpload(@RequestParam("file") MultipartFile file) {
            fileService.store(file);
            return "You successfully uploaded " + file.getOriginalFilename() + "!";
        }

}
