package entity;

import enums.CandidatePosition;


import javax.persistence.*;
import java.util.Objects;

@Entity
public class CandidateChoice extends Choice {
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Person person;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Party party;

    @Column
    @Enumerated(value = EnumType.STRING)
    private CandidatePosition candidatePosition;

    public CandidateChoice(String title, Person person, Party party, CandidatePosition candidatePosition) {
        super(title);
        this.person = person;
        this.party = party;
        this.candidatePosition = candidatePosition;
    }

    public CandidateChoice() {
    }

    public Person getPerson() {
        return person;
    }

    public Party getParty() {
        return party;
    }

    public CandidatePosition getCandidatePosition() {
        return candidatePosition;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CandidateChoice choice = (CandidateChoice) o;
        return person.equals(choice.person) &&
                party.equals(choice.party) &&
                candidatePosition == choice.candidatePosition;
    }

    @Override
    public int hashCode() {
        return Objects.hash(person, party, candidatePosition);
    }
}
