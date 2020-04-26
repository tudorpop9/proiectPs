package Factory;

import entity.*;
import enums.ElectionType;

import java.util.Date;
import java.util.List;

public class ElectionFactory {

    public Election createElection(String title, ElectionType electionType, List<Choice> choices, Date startDate, Date endDate){
        Election e = null;

        switch (electionType){
            case MAYORAL:
                e = new MayoralElection(title, electionType, choices, startDate, endDate);
                break;
            case PRESIDENTIAL:
                e = new PresidentialElection(title, electionType, choices, startDate, endDate);
                break;
            case PARLIMENTARY:
                e = new ParlimentaryElection(title, electionType, choices, startDate, endDate);
                break;
            case POLL:
                e = new SimplePoll(title, electionType, choices, startDate, endDate);
                break;
            default:
                System.out.println("Unknown Election Type");
                break;
        }

        return e;
    }

}
