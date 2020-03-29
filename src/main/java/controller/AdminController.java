package controller;

import entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import service.*;

import javax.servlet.http.Part;
import java.util.List;

@RestController
public class AdminController {
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

    @PostMapping("/admin/election/createElection")
    public void createElection(@RequestBody Election e){
        electionService.addElection(e);
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
    public void addPerson(@PathVariable String partyAcronym, @PathVariable Long personCnp){
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



}
