package hrms.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hrms.business.abstracts.ContactService;
import hrms.core.utils.results.Result;
import hrms.entities.concretes.Contact;

@RestController
@CrossOrigin
@RequestMapping("/api/contacts")
public class ContactsController {
    
    private final ContactService contactService;

    @Autowired
    public ContactsController(ContactService contactService) {
        this.contactService = contactService;
    }

    @PostMapping("/add")
    public Result add(@RequestBody Contact contact){
        return this.contactService.add(contact);
    }

}
