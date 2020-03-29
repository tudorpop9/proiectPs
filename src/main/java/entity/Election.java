package entity;

import enums.ElectionType;

import javax.persistence.*;
import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

@Entity
public class Election {
    @Id
    @Column(length = 50)
    private String title;

    @Column
    @Enumerated(value = EnumType.STRING)
    private ElectionType electionType;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Choice> choices;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Person> voters;

    @Column
    private Date startDate;

    @Column
    private Date endDate;

    public Election(String title, ElectionType electionType, List<Choice> choices, Date startDate, Date endDate) {
        this.title = title;
        this.electionType = electionType;
        this.choices = choices;
        this.startDate = startDate;
        this.endDate = endDate;
        this.voters = new TreeSet<>();
    }

    public Election() {
    }

    public String getTitle() {
        return title;
    }

    public ElectionType getElectionType() {
        return electionType;
    }

    public List<Choice> getChoices() {
        return choices;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }
}
