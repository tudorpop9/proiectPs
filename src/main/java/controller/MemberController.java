package controller;

import entity.Choice;
import entity.Election;
import entity.Person;
import entity.User;
import enums.ElectionType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import service.ElectionService;
import service.PersonService;
import service.PollChoiceService;
import service.UserService;

import java.util.Date;
import java.util.List;

@RestController
public class MemberController {
    @Autowired
    private UserService userService;
    @Autowired
    private PersonService personService;
    @Autowired
    private PollChoiceService pollChoiceService;
    @Autowired
    private ElectionService electionService;

    /**
     * Selectam poll-ulul curent folosind titlul acestuia, similar varianta de votare
     * persoana care voteaza dupa cnp
     * Apelam registerAVote care incrementeaza voturile variantei si pune persoana in lista votantiilor
     * pentru a nu mai vota inca o data
     * @param electionTitle
     * @param personCnp
     * @param choiceTitle
     */
    @PostMapping("/member/vote/{electionTitle}/{personCnp}/{choiceTitle}")
    public void vote(@PathVariable String electionTitle, @PathVariable Long personCnp, @PathVariable String choiceTitle){
        Person p = personService.getPerson(personCnp);
        Choice c = pollChoiceService.getAnyChoice(choiceTitle);
        electionService.registerAVote(electionTitle, p, c);
    }

    /**
     * Folosind un cnp member-ul va dezactiva contul si va deveni un cetatean simplu
     * @param cnp
     */
    @PostMapping("/member/deleteAccount/{cnp}")
    public void vote(@PathVariable Long cnp){
       User usr = userService.getUserByCnp(cnp);
       userService.downgradeToCitizen(usr);
    }

    /**
     * Member-ul poate crea un poll
     * @param title
     * @param choices
     * @param startDate
     * @param endDate
     */
    @PostMapping("/member/poll/create/{electionTitle}/{choiceList}/{start}/{date}")
    public void createElection(@PathVariable String title, @PathVariable List<Choice> choices, @PathVariable Date startDate, @PathVariable Date endDate){
        electionService.addElection(title, ElectionType.POLL, choices, startDate, endDate);
    }

    /**
     * Adauga o varianta de alegere intr-un oarecare poll, dupa titlurile acestora
     * @param pollTitle
     * @param choiceTitle
     */
    @PostMapping("/member/poll/addChoice/{pollTitle]/{choiceTitle}")
    public void addChoice(@PathVariable String pollTitle, @PathVariable String choiceTitle){
        Choice c = pollChoiceService.getAnyChoice(choiceTitle);
        electionService.addChoice(pollTitle, c);
    }

    /**
     * Dintr-un poll selecteaza toate variantele detinute
     * @param pollTitle
     * @return
     */
    @GetMapping("/admin/poll/allChoices/{pollTitle")
    public List<Choice> getAllElectionChoices(@PathVariable String pollTitle){
        return electionService.getAllChoices(pollTitle);
    }



}
