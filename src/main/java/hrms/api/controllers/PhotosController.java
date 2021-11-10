package hrms.api.controllers;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hrms.business.abstracts.PhotoService;
import hrms.core.utils.results.Result;
import hrms.entities.concretes.Photo;

@RestController
@CrossOrigin
@RequestMapping("/api/photos")
public class PhotosController {
    
    private PhotoService photoService;

    @Autowired
    public PhotosController(PhotoService photoService) {
        this.photoService = photoService;
    }

    @PostMapping("/add")
    public Result add(@RequestBody Photo photo) throws IOException{
        return this.photoService.add(photo);
    }

}
