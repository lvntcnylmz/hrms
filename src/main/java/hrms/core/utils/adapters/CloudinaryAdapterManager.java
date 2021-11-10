package hrms.core.utils.adapters;

import java.io.IOException;
import java.util.Map;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hrms.core.utils.results.DataResult;
import hrms.core.utils.results.ErrorDataResult;
import hrms.core.utils.results.SuccessDataResult;

@Service
public class CloudinaryAdapterManager implements CloudinaryAdapterService {

    private Cloudinary cloudinary;

    @Autowired
    public CloudinaryAdapterManager(Cloudinary cloudinary) {
        this.cloudinary = cloudinary;
    }

    @Override
    public DataResult<?> uploadPhoto(String photoUrl) {

        try {
            Map<?, ?> result = this.cloudinary.uploader().upload(photoUrl, ObjectUtils.emptyMap());
            result.get("url");
            return new SuccessDataResult<Map<?, ?>>("Uploaded");
        } catch (IOException e) {
            return new ErrorDataResult<>(ExceptionUtils.getMessage(e));
        }
    }

}
