package service;

import entity.CandidateChoice;
import entity.Choice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.ChoiceRepo;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class CandidateChoiceService {
    @Autowired
    private ChoiceRepo choiceRepo;

    /**
     * Adds a candidate choice to the db
     * @param candidateChoice
     */
    public void addCandidateChoice(CandidateChoice candidateChoice){
        if(candidateChoice.getPerson().getAge() > 35L &&
                !choiceRepo.existsByTitle(candidateChoice.getTitle())){

            choiceRepo.save(candidateChoice);
        }else{
            /// throw something
        }
    }

    /**
     * Deletes a Choice located by its title in db
     * @param title
     */
    public void deleteChoiceByTitle(String title){
        Choice choice = choiceRepo.findByTitle(title);
        if(choice instanceof CandidateChoice){
            choiceRepo.deleteByTitle(title);
        }else{
            ///throw something
        }
    }

    /**
     * returns all CandidateChoices from db
     * @return
     */
    public List<Choice> getAllCandidates(){
        //List<Choice> candidates = new ArrayList<>();
        return choiceRepo.findAll().stream()
                                    .filter(x -> x instanceof CandidateChoice)
                                    .collect(Collectors.toList());
    }

    /**
     * returns a CandidateChoice found by title
     * @param title
     * @return
     */
    public CandidateChoice getCandidateChoice(String title){
        Choice candidate = choiceRepo.findByTitle(title);
        if(candidate instanceof CandidateChoice){
            return (CandidateChoice) candidate;
        }else{
            //throw something
        }
        return null;
    }

    /**
     * Updates a candidateChoice, but only if it already exists in db
     * @param candidateChoice
     */

    public void updateCandidateChoice(CandidateChoice candidateChoice){
        Choice candidate = choiceRepo.findByTitle(candidateChoice.getTitle());
        if(candidate instanceof CandidateChoice){
            choiceRepo.save(candidateChoice);
        }else{
            //throw something
        }
    }

    /**
     * Increases the number of votes of a Choice by 1
     * @param candidateChoice
     */
    public void increaseVotes(CandidateChoice candidateChoice){
        candidateChoice.incrementVotes();
        this.updateCandidateChoice(candidateChoice);
    }

    /**
     * Adds a bulk of votes to the Choice
     * @param candidateChoice
     * @param newVotes
     */
    public void addVotesInBulk(CandidateChoice candidateChoice, Long newVotes){
        candidateChoice.bulkVotes(newVotes);
        this.updateCandidateChoice(candidateChoice);
    }



}
