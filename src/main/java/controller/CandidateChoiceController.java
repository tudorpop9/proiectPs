package controller;

import entity.CandidateChoice;
import entity.Choice;
import exception.PersonRequirementsException;
import exception.WrongObjTypeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import service.CandidateChoiceService;

import java.util.List;

//Created only to test some polymorphic stuff
@RestController
public class CandidateChoiceController {
    @Autowired
    private CandidateChoiceService service;

    @GetMapping("CandidateChoice/allCandidateChoices")
    public List<Choice> getAllCandidateChoices(){
        return service.getAllCandidates();
    }

    @PostMapping("CandidateChoice/addCandidateChoice")
    public void addCandidateChoice(@RequestBody CandidateChoice candidate){
        try {
            service.addCandidateChoice(candidate);
        } catch (PersonRequirementsException e) {
            e.printStackTrace();
        }
    }

    @PostMapping("CandidateChoice/deleteByTitle/{title}")
    public void deleteByTitle(@PathVariable String title){
        try {
            service.deleteChoiceByTitle(title.replace("_"," "));
        } catch (WrongObjTypeException e) {
            e.printStackTrace();
        }
    }

}
