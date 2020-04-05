package controller;

import entity.Choice;
import entity.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import service.ElectionService;
import service.PersonService;
import service.PollChoiceService;
import service.UserService;

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

    @PostMapping("/member/vote/{electionTitle}/{personCnp}/{choiceTitle}")
    public void vote(@PathVariable String electionTitle, @PathVariable Long personCnp, @PathVariable String choiceTitle){
        Person p = personService.getPerson(personCnp);
        Choice c = pollChoiceService.getAnyChoice(choiceTitle);
        electionService.registerAVote(electionTitle, p, c);
    }

}
