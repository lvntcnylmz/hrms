package hrms.api.controllers;

import hrms.core.utils.results.DataResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import hrms.business.abstracts.ContactService;
import hrms.core.utils.results.Result;
import hrms.entities.concretes.Contact;

import java.util.List;

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

    @GetMapping
    public DataResult<List<Contact>> getAll(){
        return this.contactService.getAll();
    }

}
