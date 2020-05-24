package repository;

import entity.Person;
import entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.jws.soap.SOAPBinding;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {
    public Boolean existsByCnp(Long cnp);
    //public Boolean existsByPerson_cnp(Long cnp);

    public User findByPerson_cnp(Long cnp);
    public User findByCnp(Long cnp);
    public User findByEmail(String email);
    public User findByphoneNumber(String email);
    public void deleteByCnp(Long cnp);
    public void deleteByEmail(String email);
    public void deleteByphoneNumber(String email);
}
