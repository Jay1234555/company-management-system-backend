
package com.company.company_management_system.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.company.company_management_system.entity.Contact;
import com.company.company_management_system.repository.ContactRepository;

@Service
public class ContactService {

    private final ContactRepository contactRepository;

    public ContactService(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    // Get all contacts
    public List<Contact> getAllContacts() {
        return contactRepository.findAll();
    }

    // Get contact by ID
    public Contact getContactById(Long id) {
        return contactRepository.findById(id)
                .orElseThrow(() ->
                    new RuntimeException(
                        "Contact not found with id: " + id
                    ));
    }

    // Create contact
    public Contact createContact(Contact contact) {
        return contactRepository.save(contact);
    }

    // Update contact
    public Contact updateContact(
            Long id,
            Contact contactDetails) {

        Contact contact = contactRepository.findById(id)
                .orElseThrow(() ->
                    new RuntimeException(
                        "Contact not found with id: " + id
                    ));

        contact.setName(contactDetails.getName());
        contact.setEmail(contactDetails.getEmail());
        contact.setPhone(contactDetails.getPhone());
        contact.setMessage(contactDetails.getMessage());

        return contactRepository.save(contact);
    }

    // Delete contact
    public void deleteContact(Long id) {

        Contact contact = contactRepository.findById(id)
                .orElseThrow(() ->
                    new RuntimeException(
                        "Contact not found with id: " + id
                    ));

        contactRepository.delete(contact);
    }
}
