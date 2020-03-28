package service;

import entity.Party;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.PartyRepo;

import java.util.ArrayList;
import java.util.List;

@Service
public class PartyService {
    @Autowired
    private PartyRepo partyRepo;

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





}
