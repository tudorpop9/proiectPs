package repository;

import entity.PollChoice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PollChoiceRepo extends JpaRepository<PollChoice, String> {
    public PollChoice findByTitle(String title);
    public void deleteByTitle(String title);


}
