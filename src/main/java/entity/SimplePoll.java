package entity;

import enums.ElectionType;

import java.util.Date;
import java.util.List;

public class SimplePoll extends Election {
    public SimplePoll(String title, ElectionType electionType, List<Choice> choices, Date startDate, Date endDate) {
        super(title, electionType, choices, startDate, endDate);
    }

    @Override
    public String getReport() {
        String report = new String();
        for(Choice c : this.getChoices()){
            PollChoice pe = (PollChoice)c;
            report +=  pe.getTitle() + " " + pe.getVotes().toString() + "\n";
        }
        return report;
    }
}
