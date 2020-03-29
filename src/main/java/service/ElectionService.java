package service;

import entity.Choice;
import entity.Election;
import entity.ParlimentalChoice;
import entity.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.ElectionRepo;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ElectionService {
    @Autowired
    ElectionRepo electionRepo;

    /**
     * Adds/Creates an election
     * @param election
     */
    public void addElection(Election election){
        if(!electionRepo.existsByTitle(election.getTitle())){
            electionRepo.save(election);
        }else{
            //throw..
        }
    }

    /**
     * Retrieves an election with a certain title
     * @param title
     * @return
     */
    public Election getElection(String title){
        return electionRepo.findByTitle(title);
    }

    /**
     * Deletes an election by its title
     * @param title
     */
    public void deleteElectionByTitle(String title){
        if(electionRepo.existsByTitle(title)){
            electionRepo.deleteByTitle(title);
        }else{
            //throw....
        }
    }

    /**
     * Returns the choices of an election found by its title
     * @param title
     * @return
     */
    public List<Choice> getAllChoices(String title){
        //List<Choice> candidates = new ArrayList<>();
        return this.getElection(title).getChoices();
    }

    public void registerAVote(String electionTitle, Person p, Choice c){
        Election e = this.getElection(electionTitle);
        if(!e.containtsVoter(p) && e.getChoices().contains(c)){
            c.incrementVotes();
            e.addVoter(p);
            electionRepo.save(e);
        }else{
            //throw
        }
    }



}
