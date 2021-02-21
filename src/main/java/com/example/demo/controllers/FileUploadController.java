package com.example.demo.controllers;

import com.example.demo.exceptions.EmptyFileException;
import com.example.demo.exceptions.TooBigFileException;
import com.example.demo.exceptions.WrongFileExtensionException;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Slf4j
@RestController
public class FileUploadController {

    @ApiOperation("File upload endpoint.")
    @ApiResponses(value = {@ApiResponse(code = 400, message = "If file is empty, of more then 5000 bytes or has other extension then .txt"),
            @ApiResponse(code = 200, message = "If correct .txt file is sent")})
    @PostMapping(path = "/api/fileUpload", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE}, produces = {MediaType.TEXT_PLAIN_VALUE})
    public ResponseEntity<String> fileUploadHandler(@RequestParam("file") MultipartFile file) throws IOException {
        log.info("File size: " + file.getSize());
        if (file.isEmpty()) {
            throw new EmptyFileException();
        }
        if (file.getSize() > 5000) {
            throw new TooBigFileException();
        }
        String filename = file.getOriginalFilename();
        log.info("File name: " + filename);
        if (!filename.endsWith(".txt")) {
            throw new WrongFileExtensionException();
        }
        String fileContain = new String(file.getBytes());
        return ResponseEntity.status(HttpStatus.OK).body(fileContain);
    }
}
