
package com.company.company_management_system.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.company.company_management_system.entity.Contact;
import com.company.company_management_system.service.ContactService;

@RestController
@RequestMapping("/api/contacts")
@CrossOrigin(origins = {
	    "http://localhost:3000",
	    "https://sparkling-communication-production-0a7a.up.railway.app"
	})
public class ContactController {

    private final ContactService contactService;

    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }

    // GET all contacts
    @GetMapping
    public List<Contact> getAllContacts() {
        return contactService.getAllContacts();
    }

    // GET contact by ID
    @GetMapping("/{id}")
    public Contact getContact(@PathVariable Long id) {
        return contactService.getContactById(id);
    }

    // CREATE contact
    @PostMapping
    public Contact createContact(@RequestBody Contact contact) {
        return contactService.createContact(contact);
    }

    // UPDATE contact
    @PutMapping("/{id}")
    public Contact updateContact(
            @PathVariable Long id,
            @RequestBody Contact contact) {

        return contactService.updateContact(id, contact);
    }

    // DELETE contact
    @DeleteMapping("/{id}")
    public String deleteContact(@PathVariable Long id) {

        contactService.deleteContact(id);

        return "Contact deleted successfully";
    }
}
