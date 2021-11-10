package hrms.config;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import hrms.core.utils.adapters.CloudinaryAdapterManager;
import hrms.core.utils.adapters.CloudinaryAdapterService;

@Configuration
public class CloudinaryConfig {

    @Bean
    public Cloudinary cloudinaryAccount() {
        return new Cloudinary(ObjectUtils.asMap(
                "cloud_name", "deb9msuiv", 
                "api_key", "873412812631156", "api_secret",
                "adLQvmKytY7JM25_Epf7BJTTpq4"
                ));
    }

    @Bean
    public CloudinaryAdapterService cloudinaryAdapterService() {
        return new CloudinaryAdapterManager(cloudinaryAccount());
    }
}
