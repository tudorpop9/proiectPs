package entity;

import enums.ElectionType;

import java.util.Date;
import java.util.List;

public class PresidentialElection extends Election {
    public PresidentialElection(String title, ElectionType electionType, List<Choice> choices, Date startDate, Date endDate) {
        super(title, electionType, choices, startDate, endDate);
    }

    @Override
    public String getReport() {
        String report = new String();
        for(Choice c : this.getChoices()){
            CandidateChoice conv = (CandidateChoice)c;
            report +=  conv.getPerson().toString() + " " + conv.getParty().getAcronym() + " " + conv.getVotes().toString() + "\n";
        }
        return report;
    }
}
