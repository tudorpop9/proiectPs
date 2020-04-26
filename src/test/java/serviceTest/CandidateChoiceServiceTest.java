package serviceTest;

import entity.CandidateChoice;
import entity.Choice;
import entity.Party;
import entity.Person;
import enums.CandidatePosition;
import exception.PersonRequirementsException;
import exception.WrongObjTypeException;
import org.junit.Test;
import org.junit.internal.runners.statements.Fail;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import repository.ChoiceRepo;
import service.CandidateChoiceService;
import org.junit.*;
import start.App;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.fail;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=CandidateChoiceService.class)
public class CandidateChoiceServiceTest {
    @Autowired
    private CandidateChoiceService service;

    @MockBean
    private ChoiceRepo repo;

    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

    @Test
    public void addCandidateChoiceTestAgeOver35() throws ParseException {
        Date bdate = dateFormat.parse("14/04/1975");
        CandidateChoice choice = new CandidateChoice("testTitle", new Person(123L,"Trump","Trump", bdate,"home"),new Party(null,"idk",null,null), CandidatePosition.PRESIDENT);
        Mockito.when(repo.existsByTitle("testTitle")).thenReturn(false);
        try {
            System.out.println(choice.getPerson().getAge());
            service.addCandidateChoice(choice);
            Assert.assertTrue(true);

        } catch (PersonRequirementsException e) {
            fail();
        }
    }

    @Test
    public void addCandidateChoiceTestAgeUnder35() throws ParseException {
        Date bdate = dateFormat.parse("14/04/2010");
        CandidateChoice choice = new CandidateChoice("testTitle", new Person(123L,"Trump","Trump", bdate,"home"),new Party(null,"idk",null,null), CandidatePosition.PRESIDENT);
        Mockito.when(repo.existsByTitle("testTitle")).thenReturn(true);
        try {
            service.addCandidateChoice(choice);
            fail();

        } catch (PersonRequirementsException e) {
            Assert.assertTrue(true);
        }
    }

    @Test
    public void deleteChoiceByTitleTypeExpectedTypeTest() throws ParseException {
        Date bdate = dateFormat.parse("14/04/1975");
        CandidateChoice choice = new CandidateChoice("testTitle", new Person(123L,"Trump","Trump", bdate,"home"),new Party(null,"idk",null,null), CandidatePosition.PRESIDENT);
        Mockito.when(repo.findByTitle("testTitle")).thenReturn(choice);

        try {
            service.deleteChoiceByTitle("testTitle");
            Assert.assertTrue(true);
        } catch (WrongObjTypeException e) {
            fail();
        }
    }

    @Test
    public void deleteChoiceByTitleWrongTypeTest() throws ParseException {
        Date bdate = dateFormat.parse("14/04/1975");
        Choice choice = new Choice("testTitle");
        Mockito.when(repo.findByTitle("testTitle")).thenReturn(choice);

        try {
            service.deleteChoiceByTitle("testTitle");
            fail();
        } catch (WrongObjTypeException e) {
            Assert.assertTrue(true);
        }
    }
}
