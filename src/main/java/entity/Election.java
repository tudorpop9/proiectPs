package entity;

import enums.ElectionType;

import javax.persistence.*;
import java.util.*;

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

    private List<ElectionObserver> observers;

    public Election(String title, ElectionType electionType, List<Choice> choices, Date startDate, Date endDate) {
        this.title = title;
        this.electionType = electionType;
        this.choices = choices;
        this.startDate = startDate;
        this.endDate = endDate;
        this.voters = new TreeSet<>();
        this.observers = new ArrayList<>();
    }

    public Election() {
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setElectionType(ElectionType electionType) {
        this.electionType = electionType;
    }

    public void setChoices(List<Choice> choices) {
        this.choices = choices;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
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

    /**
     * Adds a voter to the list so it cannot vote again
     * @param person
     */
    public void addVoter(Person person){
        this.voters.add(person);
    }

    /**
     * Adds voters to a list so it cannot vote again
     * @param people
     */
    public void addVoter(Person[] people){
        for(Person p: people)
            this.addVoter(p);
    }

    /**
     * Signals the presence of a voter the list
     * @param p
     * @return
     */
    public boolean containtsVoter(Person p){
        return this.voters.contains(p);
    }

    /**
     * Adds a vote choice to the list
     * @param c
     */
    public void addChoice(Choice c){
        this.choices.add(c);
    }

    /**
     * Adds an observer to the election, different observers should notify the population
     * by different means (ex text message, e-mail)
     * @param observer
     */
    public void addObserver(ElectionObserver observer){
        this.observers.add(observer);
    }

    /**
     * Removes an observer from the election, different observers should notify the population
     * by different means (ex text message, e-mail)
     * @param observer
     */
    public void removeObserver(ElectionObserver observer){
        this.observers.remove(observer);
    }

    /**
     * Should notify the population and start the election
     * @param msg
     */
    public void openPolls(String msg){
        for(ElectionObserver obs : observers){
            obs.notify(msg);
        }
    }


}
