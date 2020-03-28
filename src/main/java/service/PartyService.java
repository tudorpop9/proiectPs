package service;

import entity.Party;
import entity.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.PartyRepo;
import repository.PersonRepo;

import java.util.ArrayList;
import java.util.List;

@Service
public class PartyService {
    @Autowired
    private PartyRepo partyRepo;

    @Autowired
    private PersonRepo personRepo;

    /**
     * Returns a party identified by their acronym
     * @param acronym
     * @return
     */
    public Party getParty(String acronym){
        return partyRepo.findByAcronym(acronym);
    }

    /**
     * Returns all parties form database
     * @return
     */
    public List<Party> getAllParties(){
        List<Party> parties = new ArrayList<>();
        partyRepo.findAll().forEach(parties::add);
        return parties;
    }

    /**
     * Adds a new party do db, if it does not exists already
     * @param party
     */
    public void addParty(Party party){
        if(!partyRepo.existsById(party.getAcronym())){
            partyRepo.save(party);
        }else{
            //Throw something
        }
    }

    /**
     * Removes a party
     * @param party
     */
    public void removeParty(Party party){
        partyRepo.delete(party);
    }

    /**
     * removes a party selected by id
     * @param acronym
     */
    public void removeParty(String acronym){
        partyRepo.deleteByAcronym(acronym);
    }

    /**
     * Assigns a person to a party
     * @param person
     * @param party
     */
    public void addPerson(Person person, Party party){
        if(person.getParty() == null && person.getAge() >= 18){
            party.addMembers(person);
            person.setParty(party);
            personRepo.save(person);
            partyRepo.save(party);
        }

    }

    /**
     * Adds one or more people to a party member list
     * @param people
     * @param party
     */
    public void addPeople(Person[] people, Party party){
        for(Person person : people){
            if(person.getParty() == null && person.getAge() >= 18){
                party.addMembers(person);
                person.setParty(party);
                personRepo.save(person);
            }
        }

        partyRepo.save(party);

    }



}
