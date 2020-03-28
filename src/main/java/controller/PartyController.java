package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import repository.PartyRepo;

@RestController
public class PartyController {
    @Autowired
    private PartyRepo partyRepo;
}
