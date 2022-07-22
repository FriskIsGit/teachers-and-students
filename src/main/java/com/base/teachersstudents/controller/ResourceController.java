package com.base.teachersstudents.controller;

import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

@RequestMapping("/download/")
@RestController
public class ResourceController{
    @GetMapping(path = {"/file/Foo", "/file/Foo.class"})
    public ResponseEntity<InputStreamResource> download(){
        System.out.println("Called");
        String aFilePath = "src/main/resources/Foo.class";
        File file = new File(aFilePath);
        if(!file.exists()){
            System.out.println("Resource doesn't exist");
            return ResponseEntity.notFound().build();
        }

        return constructFileResponse(file, "Foo.class");
    }

    private ResponseEntity<InputStreamResource> constructFileResponse(File file, String fileName){
        InputStreamResource resource = null;
        try{
            resource = new InputStreamResource(new FileInputStream(file));
        }catch (FileNotFoundException ignored){
        }

        return ResponseEntity.ok()
                .contentLength(file.length())
                .header("filename", fileName)
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(resource);
    }
}
