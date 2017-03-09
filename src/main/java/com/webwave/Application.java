package com.webwave;

import com.webwave.domain.Contact;
import com.webwave.domain.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application implements CommandLineRunner {

    @Autowired
    private ContactRepository repository;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... strings) throws Exception {

        repository.save(new Contact("Harry", "Potter", "hp@gmail.com", "3428095", "Hogwarts"));
        repository.save(new Contact("Ron", "Wesley", "rw@gmail.com", "34232095", "Hogwarts"));
        repository.save(new Contact("Hermione", "Granger", "hg@gmail.com", "2288095", "Hogwarts"));
    }
}
