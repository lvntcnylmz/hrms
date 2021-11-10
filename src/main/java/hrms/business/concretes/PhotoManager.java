package hrms.business.concretes;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hrms.business.abstracts.PhotoService;
//import hrms.core.utils.adapters.concretes.CloudinaryAdapterManager;
import hrms.core.utils.results.Result;
import hrms.core.utils.results.SuccessDataResult;
import hrms.dataAccess.abstracts.PhotoDao;
import hrms.entities.concretes.Photo;

@Service
public class PhotoManager implements PhotoService {

    private PhotoDao photoDao;
    //private CloudinaryAdapterManager cloudinaryAdapterManager;

    @Autowired
    public PhotoManager(PhotoDao photoDao) {
        this.photoDao = photoDao;
        //this.cloudinaryAdapterManager = cloudinaryAdapterManager;
    }

    //FIXME : cloudinary adapter not working and type casting error...
    @Override
    public Result add(Photo photo) throws IOException {
        //this.cloudinaryAdapterManager.upload(photo.getPhotoURL());
        return new SuccessDataResult<Photo>(this.photoDao.save(photo), "Saved");
    }
    
}
