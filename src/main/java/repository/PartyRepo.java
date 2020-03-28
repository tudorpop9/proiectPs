package repository;

import entity.Party;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PartyRepo extends JpaRepository<Party, String> {
    public Party findByAcronym(String acronym);
    public Party deleteByAcronym(String acronym);
}
