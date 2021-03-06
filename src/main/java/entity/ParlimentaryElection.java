package entity;

import enums.ElectionType;

import java.util.Date;
import java.util.List;

public class ParlimentaryElection extends Election {

    public ParlimentaryElection(String title, ElectionType electionType, List<Choice> choices, Date startDate, Date endDate) {
        super(title, electionType, choices, startDate, endDate);
    }

    @Override
    public String getReport() {
        String report = new String();
        for(Choice c : this.getChoices()){
            ParlimentalChoice pe = (ParlimentalChoice)c;
            report +=  pe.getParty().getAcronym() + " " + pe.getVotes().toString() + "\n";
        }
        return report;
    }
}
