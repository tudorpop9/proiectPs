package service;

import entity.Choice;
import entity.ParlimentalChoice;
import entity.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.ChoiceRepo;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ParlimentChoiceService {
    @Autowired
    private ChoiceRepo choiceRepo;

    /**
     * Adds a parlimentalChoice in db
     * @param parlimentalChoice
     */
    public void addParlimentalChoice(ParlimentalChoice parlimentalChoice){
        if(!choiceRepo.existsByTitle(parlimentalChoice.getTitle())){

            choiceRepo.save(parlimentalChoice);
        }else{
            /// throw something
        }
    }

    /**
     * Deletes a ParlimentalChoice located by its title in db
     * @param title
     */
    public void deleteChoiceByTitle(String title){
        Choice choice= choiceRepo.findByTitle(title);
        if(choice instanceof ParlimentalChoice){
            choiceRepo.deleteByTitle(title);
        }else{
            ///throw something
        }
    }

    /**
     * returns all ParlimentalChoice from db
     * @return
     */
    public List<Choice> getAllCandidates(){
        //List<Choice> candidates = new ArrayList<>();
        return choiceRepo.findAll().stream()
                .filter(x -> x instanceof ParlimentalChoice)
                .collect(Collectors.toList());
    }

    /**
     * returns a ParlimentalChoice found by title
     * @param title
     * @return
     */
    public ParlimentalChoice getParlimentalChoice(String title){
        Choice candidate = choiceRepo.findByTitle(title);
        if(candidate instanceof ParlimentalChoice){
            return (ParlimentalChoice) candidate;
        }else{
            //throw something
        }
        return null;
    }

    /**
     * Updates a parlimentalChoice, but only if it already exists in db
     * @param parlimentalChoice
     */
    public void updateParlimentalChoice(ParlimentalChoice parlimentalChoice){
        Choice candidate = choiceRepo.findByTitle(parlimentalChoice.getTitle());
        if(candidate instanceof ParlimentalChoice){
            choiceRepo.save(parlimentalChoice);
        }else{
            //throw something
        }
    }

    /**
     * Adds a person to a parlimentalChoice's list of proposed people
     * @param person
     * @param parlimentalChoice
     */
    public void addProposedPerson(Person person, ParlimentalChoice parlimentalChoice){
        if(choiceRepo.existsByTitle(parlimentalChoice.getTitle())){
            parlimentalChoice.addProposedPerson(person);
            choiceRepo.save(parlimentalChoice);
        }else{
            //throw something
        }
    }

    /**
     * Adds more people to a parlimentalChoice's list of proposed people
     * @param people
     * @param parlimentalChoice
     */
    public void addProposedPeople(Person[] people, ParlimentalChoice parlimentalChoice){
        for(Person p : people){
            this.addProposedPerson(p, parlimentalChoice);
        }
    }

    /**
     * Increases the number of votes of a Choice by 1
     * @param parlimentalChoice
     */
    public void increaseVotes(ParlimentalChoice parlimentalChoice){
        parlimentalChoice.incrementVotes();
        this.updateParlimentalChoice(parlimentalChoice);
    }

    /**
     * Adds a bulk of votes to the Choice
     * @param parlimentalChoice
     * @param newVotes
     */
    public void addVotesInBulk(ParlimentalChoice parlimentalChoice, Long newVotes){
        parlimentalChoice.bulkVotes(newVotes);
        this.updateParlimentalChoice(parlimentalChoice);
    }

}
