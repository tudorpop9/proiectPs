package service;

import entity.Person;
import exception.PersonRequirementsException;
import exception.RowAlreadyExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.PersonRepo;

import java.util.ArrayList;
import java.util.List;

@Service
public class PersonService {
    @Autowired
    private PersonRepo personRepo;

    /**
     *
     * @return a list with all the people in db
     */
    public List<Person> getAllPeople(){
        List<Person> people = new ArrayList<>();
        personRepo.findAll().forEach(people::add);
        return people;
    }

    /**
     * returns the person with a given cnp
     * @param cnp
     * @return
     */
    public Person getPerson(Long cnp){
        return personRepo.findByCnp(cnp);
    }

    /**
     * Adds a new person to the db
     * @param person
     */
    public void addPerson(Person person) throws RowAlreadyExistsException {
        if(personRepo.existsByCnp(person.getCnp())){
            throw new RowAlreadyExistsException("Person already exists in db");
        }
        personRepo.save(person);
    }

    /**
     * Updates a person in db
     * @param person
     */
    public void updatePerson(Person person) throws PersonRequirementsException {
        if(!personRepo.existsByCnp(person.getCnp())){
            throw new PersonRequirementsException("Person does not exist in db");
        }
        personRepo.save(person);
    }

    /**
     * Removes a person from db by cnp
     * @param cnp
     */
    public void removePerson(Long cnp){
        personRepo.deleteByCnp(cnp);
    }



}
