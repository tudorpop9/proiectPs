package entity;

import enums.ParlimentalType;

import javax.persistence.*;
import javax.websocket.OnOpen;
import java.util.List;

@Entity
public class ParlimentalChoice extends Choice {
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Party party;

    @Column
    private String governmentProgram;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Person> proposedPeople;

    @Column
    @Enumerated(value = EnumType.STRING)
    private ParlimentalType parlimentalType;

    public ParlimentalChoice(String title, Party party, String governmentProgram, List<Person> proposedPeople, ParlimentalType parlimentalType) {
        super(title);
        this.party = party;
        this.governmentProgram = governmentProgram;
        this.proposedPeople = proposedPeople;
        this.parlimentalType = parlimentalType;
    }

    public ParlimentalChoice() {
    }

    public Party getParty() {
        return party;
    }

    public String getGovernmentProgram() {
        return governmentProgram;
    }

    public List<Person> getProposedPeople() {
        return proposedPeople;
    }

    public ParlimentalType getParlimentalType() {
        return parlimentalType;
    }
}
