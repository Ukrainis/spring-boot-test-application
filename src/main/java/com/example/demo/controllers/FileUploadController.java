package com.example.demo.controllers;

import com.example.demo.exceptions.EmptyFileException;
import com.example.demo.exceptions.TooBigFileException;
import com.example.demo.exceptions.WrongFileExtensionException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @Operation(summary = "File upload endpoint.")
    @ApiResponses(value = {@ApiResponse(responseCode = "400", description = "If file is empty, of more then 5000 bytes or has other extension then .txt"),
            @ApiResponse(responseCode = "200", description = "If correct .txt file is sent")})
    @PostMapping(path = "/api/fileUpload", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<String> fileUploadHandler(@RequestParam("file") MultipartFile file) throws IOException {
        log.info("File size: " + file.getSize());
        if (file.isEmpty()) {
            throw new EmptyFileException();
        }
        String filename = file.getOriginalFilename();
        log.info("File name: " + filename);
        String fileExtension = filename.split("\\.")[1];
        if (!fileExtension.equals("txt")) {
            throw new WrongFileExtensionException();
        }
        if (file.getSize() > 5000) {
            throw new TooBigFileException();
        }
        String fileContain = new String(file.getBytes());
        return ResponseEntity.status(HttpStatus.OK).body(fileContain);
    }
}
