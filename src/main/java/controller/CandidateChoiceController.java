package controller;

import entity.CandidateChoice;
import entity.Choice;
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
        service.addCandidateChoice(candidate);
    }

    @PostMapping("CandidateChoice/deleteByTitle/{title}")
    public void deleteByTitle(@PathVariable String title){
        service.deleteChoiceByTitle(title.replace("_"," "));
    }

}
