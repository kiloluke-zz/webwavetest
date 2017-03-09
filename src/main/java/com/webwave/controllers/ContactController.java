package com.webwave.controllers;

import com.webwave.domain.Contact;
import com.webwave.domain.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/contacts")
public class ContactController {

    @Autowired
    private ContactRepository repository;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String listContacts(Model model) {
        model.addAttribute("contacts", repository.findAll());
        return "contacts/list";
    }

    @RequestMapping(value = "/{id}/delete", method = RequestMethod.GET)
    public ModelAndView delete(@PathVariable long id) {
        repository.delete(id);
        return new ModelAndView("redirect:/contacts");
    }

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String newProject() {
        return "contacts/new";
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public ModelAndView create(
            @RequestParam("firstName") String firstName,
            @RequestParam("lastName") String lastName,
            @RequestParam("email") String email,
            @RequestParam("phone") String phone,
            @RequestParam("address") String address) {
        repository.save(new Contact(firstName, lastName, email, phone, address));
        return new ModelAndView("redirect:/contacts");
    }

    @RequestMapping(value = "/update", method = RequestMethod.GET)
    public ModelAndView update(@RequestParam("contact_id") long id,
                               @RequestParam("firstName") String firstName,
                               @RequestParam("lastName") String lastName,
                               @RequestParam("email") String email,
                               @RequestParam("phone") String phone,
                               @RequestParam("address") String address) {
        Contact contact = repository.findOne(id);
        contact.setFirstName(firstName);
        contact.setLastName(lastName);
        contact.setPhone(phone);
        contact.setEmail(email);
        contact.setAddress(address);
        repository.save(contact);
        return new ModelAndView("redirect:/contacts");
    }

    @RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
    public String edit(@PathVariable long id,
                       Model model) {
        Contact contact = repository.findOne(id);
        model.addAttribute("contact", contact);
        return "contacts/edit";
    }


}
