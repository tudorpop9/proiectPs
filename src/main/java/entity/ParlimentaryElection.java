package entity;

import enums.ElectionType;

import java.util.Date;
import java.util.List;

public class ParlimentaryElection extends Election {

    public ParlimentaryElection(String title, ElectionType electionType, List<Choice> choices, Date startDate, Date endDate) {
    }

    @Override
    public String getReport() {
        return null;
    }
}
