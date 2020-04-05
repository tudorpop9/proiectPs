package service;

import entity.Person;
import entity.User;
import enums.RoleType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.PersonRepo;
import repository.UserRepo;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    public static final String emailRegex =  "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
    public static final String passwordRegex = "((?=.*[a-z])(?=.*\\d)(?=.*[A-Z])(?=.*[@#$%!]).{8,40})";

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private PersonRepo personRepo;

    /**
     * Returns all users in db
     * @return
     */
    public List<User> getAllUsers(){
        List<User> users = new ArrayList<>();
        userRepo.findAll().forEach(users::add);
        return users;
    }

    /**
     * Returns an user found by its person cnp
     * @param cnp
     * @return
     */
    public User getUserByCnp(Long cnp){
        return userRepo.findByPerson_cnp(cnp);
    }


    /**
     * Returns an user found by its email
     * @param email
     * @return
     */
    public User getUserByEmail(String email){
        return userRepo.findByEmail(email);
    }

    /**
     * A citizen becomes a member by adding an e-mail, a password, a phone number
     * @param usr
     * @param email
     * @param pwd
     * @param phoneNr
     */
    public void upgradeToMember(User usr ,String email, String pwd, String phoneNr){

        if(email.matches( emailRegex)){
            usr.setEmail(email);
        }else{
            //throw
        }

        if(pwd.matches(passwordRegex)) {
            usr.setPassword(pwd);
        }else{
            //throw
        }

        if(phoneNr.length() == 10){
            usr.setPhoneNumber(phoneNr);
        }else{
            //throw
        }
        usr.setRole(RoleType.MEMBER);

        if(!userRepo.existsByPerson(usr.getPerson())){
            //throw
        }
        userRepo.save(usr);
    }

    /**
     * A member can delete its account
     * @param user
     */
    public void downgradeToCitizen(User user){
        user.setRole(RoleType.CITIZEN);
        user.setPassword(null);
        if(userRepo.existsByPerson(user.getPerson())){
            userRepo.save(user);
        }else{
            //throw
        }
    }

    /**
     * Adds an user to the db
     * @param user
     */
    public void addUser(User user){
        if(user.getEmail().matches(emailRegex) && !userRepo.existsByPerson(user.getPerson()) && user != null){
            userRepo.save(user);
        }else{
            //throw
        }
    }

}