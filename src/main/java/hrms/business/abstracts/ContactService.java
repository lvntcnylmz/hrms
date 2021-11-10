package hrms.business.abstracts;

import hrms.core.utils.results.Result;
import hrms.entities.concretes.Contact;

public interface ContactService {
    
    Result add(Contact contact);

}
