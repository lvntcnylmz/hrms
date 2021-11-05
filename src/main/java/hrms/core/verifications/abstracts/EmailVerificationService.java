package hrms.core.verifications.abstracts;

import hrms.core.utils.results.Result;

public interface EmailVerificationService {
    
    Result verifyEmail(String email);

}
