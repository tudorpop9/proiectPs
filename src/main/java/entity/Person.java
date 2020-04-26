package entity;



import sun.print.resources.serviceui_it;

import javax.imageio.plugins.jpeg.JPEGImageReadParam;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
public class Person implements Serializable, Comparable {
    @Id
    private Long cnp;

    @Column
    private String lastName;

    @Column
    private String firstName;

    @Column
    private Date birthDate;

    @Column
    private String address;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "acronym")
    private Party party;


    public Person(Long cnp, String lastName, String firstName, Date birthDate, String address) {
        this.cnp = cnp;
        this.lastName = lastName;
        this.firstName = firstName;
        this.birthDate = birthDate;
        this.address = address;
        this.party = null;

    }

    public Person() {
    }

    public Long getCnp() {
        return cnp;
    }

    public void setCnp(Long cnp) {
        this.cnp = cnp;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Party getParty() {
        return party;
    }

    public void setParty(Party party) {
        this.party = party;
    }

    public long getAge(){
        return (System.currentTimeMillis() - this.birthDate.getTime())/31556926000L; // 1Year in millis
    }

    @Override
    public int compareTo(Object o) {
        Person comp = (Person)o;
        return this.cnp.compareTo(comp.getCnp());
    }

    @Override
    public String toString() {
        return lastName + " " + firstName;
    }

    @Override
    public boolean equals(Object obj) {
        Person p = (Person) obj;
        return this.cnp.equals(p.getCnp());
    }

    //Blood type maybe ?
}
