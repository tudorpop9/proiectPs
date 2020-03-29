package entity;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

@MappedSuperclass
public class Choice implements Serializable {
    @Id
    @Column(length = 50)
    protected String title;

    @Column
    protected Long votes;

    public Choice(String title) {
        this.title = title;
        this.votes = 0L;
    }

    public Choice() {
    }

    public String getTitle() {
        return title;
    }

    public Long getVotes() {
        return votes;
    }

    /**
     * Increments the total votes of a Choice
     */
    public void incrementVotes(){
        this.votes++;
    }

    /**
     * Adds a number of votes to the total number
     * Could be used not to overload the db with value requests
     * @param value
     */
    public void bulkVotes(Long value){
        this.votes+=value;
    }
}
