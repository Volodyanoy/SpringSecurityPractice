package org.example.volodyanoy.SecurityApp.util;

import org.example.volodyanoy.SecurityApp.models.Person;
import org.example.volodyanoy.SecurityApp.services.PersonDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class PersonValidator implements Validator {
    private final PersonDetailsService personDetailsService;

    @Autowired
    public PersonValidator(PersonDetailsService personDetailsService) {
        this.personDetailsService = personDetailsService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Person.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Person person = (Person) target;
        try {
            personDetailsService.loadUserByUsername(person.getUsername());

        } catch (UsernameNotFoundException ignored) {
            return; // всё ок, такого пользователя нет
        }

        errors.rejectValue("username", "", "Such username already exists");
    }
}
