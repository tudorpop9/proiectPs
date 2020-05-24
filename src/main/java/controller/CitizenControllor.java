package controller;

import entity.Choice;
import entity.Person;
import entity.User;
import exception.RowAlreadyExistsException;
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

    /**
     * Selectam poll-ulul curent folosind titlul acestuia, similar varianta de votare
     * persoana care voteaza dupa cnp
     * Apelam registerAVote care incrementeaza voturile variantei si pune persoana in lista votantiilor
     * pentru a nu mai vota inca o data
     * @param electionTitle
     * @param personCnp
     * @param choiceTitle
     */
    @PostMapping("/citizen/vote/{electionTitle}/{personCnp}/{choiceTitle}")
    public void vote(@PathVariable String electionTitle, @PathVariable Long personCnp, @PathVariable String choiceTitle){
        Person p = personService.getPerson(personCnp);
        Choice c = pollChoiceService.getAnyChoice(choiceTitle);
        electionService.registerAVote(electionTitle, p, c);
    }

    /**
     * Introducand field-uri suplimentare, un cetatean poate deveni membru pentru a accesa alte facilitati
     * @param cnp
     * @param mail
     * @param pwd
     * @param phoneNr
     */
    @PostMapping("/citizen/makeAccount/{cnp}/{mail}/{pwd}/{phoneNr}")
    public void makeAccount(@PathVariable Long cnp, @PathVariable String mail, @PathVariable String pwd, @PathVariable String phoneNr) throws RowAlreadyExistsException {
        Person person =  personService.getPerson(cnp);
        userService.upgradeToMember(person,mail,pwd,phoneNr);
    }


}
