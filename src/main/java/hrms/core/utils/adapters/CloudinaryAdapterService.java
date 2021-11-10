package hrms.core.utils.adapters;

import java.io.IOException;

import hrms.core.utils.results.DataResult;

public interface CloudinaryAdapterService {
    
    DataResult<?> uploadPhoto(String photoUrl) throws IOException;

}
