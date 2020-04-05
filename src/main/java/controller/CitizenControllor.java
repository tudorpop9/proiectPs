package controller;

import entity.Choice;
import entity.Person;
import entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import service.ElectionService;
import service.PersonService;
import service.PollChoiceService;
import service.UserService;

@RestController
public class CitizenControllor {
    @Autowired
    private UserService userService;
    @Autowired
    private PersonService personService;
    @Autowired
    private PollChoiceService pollChoiceService;
    @Autowired
    private ElectionService electionService;

    @PostMapping("/citizen/vote/{electionTitle}/{personCnp}/{choiceTitle}")
    public void vote(@PathVariable String electionTitle, @PathVariable Long personCnp, @PathVariable String choiceTitle){
        Person p = personService.getPerson(personCnp);
        Choice c = pollChoiceService.getAnyChoice(choiceTitle);
        electionService.registerAVote(electionTitle, p, c);
    }

    @PostMapping("/citizen/makeAccount/{cnp}/{mail}/{pwd}/{phoneNr}")
    public void makeAccount(@PathVariable Long cnp, @PathVariable String mail, @PathVariable String pwd, @PathVariable String phoneNr){
        User user =  userService.getUserByCnp(cnp);
        userService.upgradeToMember(user,mail,pwd,phoneNr);
    }


}
