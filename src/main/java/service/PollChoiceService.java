package service;

import entity.Choice;
import entity.ParlimentalChoice;
import entity.PollChoice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.ChoiceRepo;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class PollChoiceService {
    @Autowired
    private ChoiceRepo choiceRepo;

    /**
     * Adds a pollChoice in db
     * @param pollChoice
     */
    public void addPollChoice(PollChoice pollChoice){
        if(!choiceRepo.existsByTitle(pollChoice.getTitle())){
            choiceRepo.save(pollChoice);
        }
        /// throw something
    }

    /**
     * Deletes a PollChoice located by its title in db
     * @param title
     */
    public void deleteChoiceByTitle(String title){
        Choice choice= choiceRepo.findByTitle(title);
        if(choice instanceof PollChoice){
            choiceRepo.deleteByTitle(title);
        }else{
            ///throw something
        }
    }

    /**
     * returns all PollChoice from db
     * @return
     */
    public List<Choice> getAllCandidates(){
        //List<Choice> candidates = new ArrayList<>();
        return choiceRepo.findAll().stream()
                .filter(x -> x instanceof PollChoice)
                .collect(Collectors.toList());
    }

    /**
     * returns a PollChoice found by title
     * @param title
     * @return
     */
    public PollChoice getPollChoice(String title){
        Choice candidate = choiceRepo.findByTitle(title);
        if(candidate instanceof PollChoice){
            return (PollChoice) candidate;
        }else{
            //throw something
        }
        return null;
    }

    /**
     * returns ANY choice found by title
     * @param title
     * @return
     */
    public Choice getAnyChoice(String title){
        return choiceRepo.findByTitle(title);
    }

    /**
     * Updates a pollChoice, but only if it already exists in db
     * @param pollChoice
     */
    public void updatePollChoice(PollChoice pollChoice){
        Choice candidate = choiceRepo.findByTitle(pollChoice.getTitle());
        if(candidate instanceof PollChoice){
            choiceRepo.save(pollChoice);
        }else{
            //throw something
        }
    }

    /**
     * Increases the number of votes of a Choice by 1
     * @param pollChoice
     */
    public void increaseVotes(PollChoice pollChoice){
        pollChoice.incrementVotes();
        this.updatePollChoice(pollChoice);
    }

    /**
     * Adds a bulk of votes to the Choice
     * @param pollChoice
     * @param newVotes
     */
    public void addVotesInBulk(PollChoice pollChoice, Long newVotes){
        pollChoice.bulkVotes(newVotes);
        this.updatePollChoice(pollChoice);
    }
}
