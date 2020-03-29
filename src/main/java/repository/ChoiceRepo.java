package repository;

import entity.CandidateChoice;
import entity.Choice;
import entity.PollChoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChoiceRepo extends JpaRepository<Choice, String> {
    public PollChoice findByTitle(String title);
    public void deleteByTitle(String title);
    public boolean existsByTitle(String title);

}
