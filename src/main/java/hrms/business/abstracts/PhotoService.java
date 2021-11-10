package hrms.business.abstracts;

import java.io.IOException;

import hrms.core.utils.results.Result;
import hrms.entities.concretes.Photo;

public interface PhotoService {
    
    Result add(Photo photo) throws IOException;

}
