package hrms.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import hrms.entities.concretes.Contact;

public interface ContactDao extends JpaRepository<Contact, Integer> {
    
}
