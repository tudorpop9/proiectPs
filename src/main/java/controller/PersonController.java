package controller;

import entity.Person;
import exception.RowAlreadyExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import service.PersonService;

import java.util.List;

@RestController
public class PersonController {
    @Autowired
    private PersonService personService;
    @GetMapping("/")
    public String howAreYou(){
        return "I'm a PersonController";
    }

    @GetMapping("/allPeople")
    public List<Person> getAllPeople(){
        return personService.getAllPeople();
    }

    @GetMapping("/getPerson/{cnp}")
    public Person getPeople(@PathVariable Long cnp){
        return personService.getPerson(cnp);
    }

    @PostMapping("/addPerson")
    public void addPerson(@RequestBody Person person) throws RowAlreadyExistsException {
        personService.addPerson(person);
    }

    @PostMapping("/addPeople")
    public void addPeople(@RequestBody Person[] people) throws RowAlreadyExistsException {
        for(Person p : people)
            personService.addPerson(p);
    }

}
