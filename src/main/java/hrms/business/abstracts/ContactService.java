package hrms.business.abstracts;

import hrms.core.utils.results.DataResult;
import hrms.core.utils.results.Result;
import hrms.entities.concretes.Contact;

import java.util.List;

public interface ContactService {
    
    Result add(Contact contact);

    DataResult<List<Contact>> getAll();

}
