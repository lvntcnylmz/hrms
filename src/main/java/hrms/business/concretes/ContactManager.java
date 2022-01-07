package hrms.business.concretes;

import hrms.business.abstracts.ContactService;
import hrms.core.utils.messages.Message;
import hrms.core.utils.results.Result;
import hrms.core.utils.results.SuccessDataResult;
import hrms.dataAccess.abstracts.ContactDao;
import hrms.entities.concretes.Contact;
import org.springframework.stereotype.Service;

@Service
public class ContactManager implements ContactService {

    private final ContactDao contactDao;

    public ContactManager(ContactDao contactDao) {
        this.contactDao = contactDao;
    }

    @Override
    public Result add(Contact contact) {
        return new SuccessDataResult<Contact>(this.contactDao.save(contact), Message.SAVED);
    }

}
