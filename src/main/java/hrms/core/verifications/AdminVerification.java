package hrms.core.verifications;

import javax.persistence.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


public class AdminVerification {
    
    private int id;

    private int userId;

    private boolean verification;

}
