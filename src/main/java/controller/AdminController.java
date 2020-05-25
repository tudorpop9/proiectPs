package controller;

import entity.*;
import enums.ElectionType;
import exception.PersonRequirementsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import repository.UserRepo;
import service.*;

import javax.servlet.http.Part;
import java.util.Date;
import java.util.List;

@RestController
public class AdminController {
    @Autowired
    private UserService userService;
    @Autowired
    private ElectionService electionService;
    @Autowired
    private PollChoiceService pollChoiceService;
    @Autowired
    private ParlimentalChoiceService parlimentalChoiceService;
    @Autowired
    private CandidateChoiceService candidateChoiceService;
    @Autowired
    private PartyService partyService;
    @Autowired
    private PersonService personService;


    //Election section
    @GetMapping("/admin/allElections")
    public List<Election> getAllElections(){
        return electionService.getAllElecions();
    }

    @PostMapping("/admin/election/vote/{electionTitle}/{personCnp}/{choiceTitle}")
    public void vote(@PathVariable String electionTitle, @PathVariable Long personCnp, @PathVariable String choiceTitle){
        Person p = personService.getPerson(personCnp);
        Choice c = pollChoiceService.getAnyChoice(choiceTitle);//Provizoriu pana gasesc o idee mai buna
        electionService.registerAVote(electionTitle, p, c);
    }

    @PostMapping("/admin/election/createElection/{electionTitle}/{type}/{choiceList}/{startDate}/{dateEnd}")
    public void createElection(@PathVariable String title,@PathVariable ElectionType electionType,@PathVariable List<Choice> choices,@PathVariable Date startDate,@PathVariable Date endDate){
        electionService.addElection(title, electionType, choices, startDate, endDate);
    }

    @PostMapping("/admin/election/addChoice/{electionTitle]/{choiceTitle}")
    public void addChoice(@PathVariable String electionTitle, @PathVariable String choiceTitle){
        Choice c = pollChoiceService.getAnyChoice(choiceTitle);
        electionService.addChoice(electionTitle, c);
    }

    @GetMapping("/admin/election/allChoices/{electionTitle")
    public List<Choice> getAllElectionChoices(@PathVariable String electionTitle){
        return electionService.getAllChoices(electionTitle);
    }

    //Party section
    @PostMapping("/admin/party/addParty")
    public void addParty(@RequestBody Party p){
        partyService.addParty(p);
    }

    @PostMapping("admin/party/addPerson/{partyAcronym}/{personCnp}")
    public void addPersonToParty(@PathVariable String partyAcronym, @PathVariable Long personCnp) throws PersonRequirementsException {
        Person p = personService.getPerson(personCnp);
        Party party = partyService.getParty(partyAcronym);
        partyService.addPerson(p, party);
    }

    // mai trebuie sa fac o metoda pentru mai multe persoane // persionService++

    @PostMapping("admin/party/removeParty/{acronym}")
    public void removeParty(@PathVariable String acronym){
       partyService.removeParty(acronym);
    }

    @GetMapping("admin/party/allParties")
    public List<Party> getAllParties(){
        return partyService.getAllParties();
    }

    @GetMapping("admin/party/findParty/{acronym}")
    public Party findParty(@PathVariable String acronym){
        return partyService.getParty(acronym);
    }

    //CandidateChoice
    @PostMapping("admin/Choice/CandidateChoice/add")
    public void CandidateChoice(@RequestBody CandidateChoice candidateChoice){
        try {
            candidateChoiceService.addCandidateChoice(candidateChoice);
        } catch (PersonRequirementsException e) {
            e.printStackTrace();
        }
    }

    //ParlimentalChoice
    @PostMapping("admin/Choice/ParlimentalChoice/add")
    public void ParlimentalChoice(@RequestBody ParlimentalChoice parlimentalChoice){
        parlimentalChoiceService.addParlimentalChoice(parlimentalChoice);
    }

    //PollChoice
    @PostMapping("admin/Choice/PollChoice/add")
    public void PollChoice(@RequestBody PollChoice pollChoice){
        pollChoiceService.addPollChoice(pollChoice);
    }



}
