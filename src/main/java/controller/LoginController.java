package controller;

import entity.Person;
import entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import service.PersonService;
import service.UserService;

@RestController
public class LoginController {

    @Autowired
    PersonService personService;
    @Autowired
    UserService userService;

    /**
     * Daca datele sunt valide, se returneza tipul de user (ADMIN/MEMBER/CITIZEN)
     * altfel fail
     * Pe baza acestor date ar trebuie sa se poate selecta ce tip de pagina sa se incarce
     * @param email
     * @param password
     * @return
     */
    @PostMapping("login/{email}/{password}")
    public String logInMethod(@PathVariable String email, @PathVariable String password){
        User user = userService.getUserByEmail(email);
        if(user != null && user.getPassword().equals(password)){
            return user.getRole().toString();
        }
        return "fail";
    }

    /**
     * Se cauta persoana in baza de date, daca se gaseste se va returna CNP-ul acestia
     * sau -1 in caz de esec
     * @param cnp
     * @return
     */
    @PostMapping("login/{cnp}")
    public Long beginVoteProcess(@PathVariable Long cnp){
        Person person = personService.getPerson(cnp);
        if(person != null){
            return cnp;
        }
        return -1L;
    }

}
