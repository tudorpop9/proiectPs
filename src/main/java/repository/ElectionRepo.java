package repository;

import entity.Election;
import entity.PollChoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ElectionRepo extends JpaRepository<Election, String> {
    public Election findByTitle(String title);
    public void deleteByTitle(String title);
    public boolean existsByTitle(String title);

}
