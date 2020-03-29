package controller;

import entity.CandidateChoice;
import entity.Choice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import service.CandidateChoiceService;

import java.util.List;

@RestController
public class CandidateChoiceController {
    @Autowired
    private CandidateChoiceService service;

    @GetMapping("/allCandidateChoices")
    public List<Choice> getAllCandidateChoices(){
        return service.getAllCandidates();
    }

    @PostMapping("/addCandidateChoice")
    public void addCandidateChoice(@RequestBody CandidateChoice candidate){
        service.addCandidateChoice(candidate);
    }

}
