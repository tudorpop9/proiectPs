package entity;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Party {
    @Id
    @Column(length = 20)
    private String acronym;

    @Column
    private String fullName;

    @Column
    private String description;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, mappedBy = "party")
    private List<Person> members;

    public Party(String acronym, String fullName, String description) {
        this.acronym = acronym;
        this.fullName = fullName;
        this.description = description;
        this.members = new ArrayList<>();
    }

    public Party(String acronym, String fullName, String description, List<Person> members) {
        this.acronym = acronym;
        this.fullName = fullName;
        this.description = description;
        this.members = members;
    }

    public Party() {
    }

    public String getAcronym() {
        return acronym;
    }

    public void setAcronym(String acronym) {
        this.acronym = acronym;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Person> getMembers() {
        return members;
    }

    public void setMembers(List<Person> members) {
        this.members = members;
    }

    public void addMembers(Person newPerson){
        this.members.add(newPerson);
    }
}
