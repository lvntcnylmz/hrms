package hrms.core.verifications.concretes;

import org.springframework.stereotype.Service;

import hrms.core.utils.results.Result;
import hrms.core.utils.results.SuccessResult;
import hrms.core.verifications.abstracts.EmailVerificationService;

@Service
public class EmailVerification implements EmailVerificationService {

    @Override
    public Result verifyEmail(String email) {
        
        return new SuccessResult("Email has verified.");
    }
    
}
