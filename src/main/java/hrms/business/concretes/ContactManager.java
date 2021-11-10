package hrms.business.concretes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hrms.business.abstracts.ContactService;
import hrms.core.utils.results.Result;
import hrms.core.utils.results.SuccessDataResult;
import hrms.dataAccess.abstracts.ContactDao;
import hrms.entities.concretes.Contact;

@Service
public class ContactManager implements ContactService {

    private ContactDao contactDao;

    @Autowired
    public ContactManager(ContactDao contactDao) {
        this.contactDao = contactDao;
    }

    @Override
    public Result add(Contact contact) {
        return new SuccessDataResult<Contact>(this.contactDao.save(contact), "Saved");
    }
    
}
