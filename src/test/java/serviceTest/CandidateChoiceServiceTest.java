package serviceTest;

import entity.CandidateChoice;
import entity.Choice;
import entity.Party;
import entity.Person;
import enums.CandidatePosition;
import exception.PersonRequirementsException;
import exception.WrongObjTypeException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import repository.ChoiceRepo;
import service.CandidateChoiceService;
import org.junit.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

    @Test
    public void getAllCandidateChoicesTest() throws ParseException {
        Date bdate = dateFormat.parse("14/04/1975");
        CandidateChoice choice = new CandidateChoice("testTitle", new Person(123L,"Trump","Trump", bdate,"home"),new Party(null,"idk",null,null), CandidatePosition.PRESIDENT);
        CandidateChoice choice2 = new CandidateChoice("goProtest", new Person(1223L,"Trump","Trump", bdate,"home"),new Party(null,"idk",null,null), CandidatePosition.PRESIDENT);
        CandidateChoice choice3 = new CandidateChoice("obj1", new Person(1231231223L,"Trump","Trump", bdate,"home"),new Party(null,"idk",null,null), CandidatePosition.PRESIDENT);
        List<CandidateChoice> expectedArr = new ArrayList<>();
        expectedArr.add(choice);
        expectedArr.add(choice2);
        expectedArr.add(choice3);
        Mockito.doReturn(expectedArr).when(repo).findAll(); // interesting syntax

        List<Choice> returned = service.getAllCandidates();
        Assert.assertEquals(returned, expectedArr);

    }

    @Test
    public void getCandidateChoiceCorrectType() throws ParseException {
        Date bdate = dateFormat.parse("14/04/1975");
        CandidateChoice choice = new CandidateChoice("testTitle", new Person(123L,"Trump","Trump", bdate,"home"),new Party(null,"idk",null,null), CandidatePosition.PRESIDENT);
        Mockito.when(repo.findByTitle("testTitle")).thenReturn(choice);

        try {
            service.getCandidateChoice("testTitle");
            Assert.assertTrue(true);
        } catch (WrongObjTypeException e) {
            fail();
        }
    }

    @Test
    public void getCandidateChoiceWrongType() throws ParseException {
        Date bdate = dateFormat.parse("14/04/1975");
        Choice choice = new Choice("testTitle");
        Mockito.when(repo.findByTitle("testTitle")).thenReturn(choice);

        try {
            service.getCandidateChoice("testTitle");
            fail();
        } catch (WrongObjTypeException e) {
            Assert.assertTrue(true);
        }
    }

    @Test
    public void updateCandidateChoiceCorrectType() throws ParseException {
        Date bdate = dateFormat.parse("14/04/1975");
        CandidateChoice choice = new CandidateChoice("testTitle", new Person(123L,"Trump","Trump", bdate,"home"),new Party(null,"idk",null,null), CandidatePosition.PRESIDENT);
        Mockito.when(repo.findByTitle("testTitle")).thenReturn(choice);

        try {
            service.updateCandidateChoice(choice);
            Assert.assertTrue(true);
        } catch (WrongObjTypeException e) {
            fail();
        }
    }

    @Test
    public void updateCandidateChoiceWrongType() throws ParseException {
        Date bdate = dateFormat.parse("14/04/1975");
        Choice choice = new Choice("testTitle");
        CandidateChoice badChoice = new CandidateChoice("testTitle", new Person(123L,"Trump","Trump", bdate,"home"),new Party(null,"idk",null,null), CandidatePosition.PRESIDENT);

        Mockito.when(repo.findByTitle("testTitle")).thenReturn(choice);

        try {
            service.updateCandidateChoice(badChoice);
            fail();
        } catch (WrongObjTypeException e) {
            Assert.assertTrue(true);
        }
    }

    //Trebuie sa mai testez update-ul iar ?(din cauza exceptiei WrongObjTypeException)
    @Test
    public void increaseVotesTest() throws ParseException, WrongObjTypeException {
        Date bdate = dateFormat.parse("14/04/1975");
        CandidateChoice toBeSent = new CandidateChoice("testTitle", new Person(1212342343L,"Donald","Trump", bdate,"home"),new Party(null,"idk",null,null), CandidatePosition.PRESIDENT);
        CandidateChoice expected = new CandidateChoice("testTitle", new Person(123L,"Trump","Trump", bdate,"home"),new Party(null,"idk",null,null), CandidatePosition.PRESIDENT);
        Mockito.when(repo.findByTitle("testTitle")).thenReturn(expected);

        expected.incrementVotes();
        service.increaseVotes(toBeSent);
        Assert.assertEquals(expected.getVotes(), toBeSent.getVotes());
    }

    @Test
    public void addVotesInBulkTest() throws ParseException, WrongObjTypeException {
        Date bdate = dateFormat.parse("14/04/1975");
        CandidateChoice toBeSent = new CandidateChoice("testTitle", new Person(1212342343L,"Donald","Trump", bdate,"home"),new Party(null,"idk",null,null), CandidatePosition.PRESIDENT);
        CandidateChoice expected = new CandidateChoice("testTitle", new Person(123L,"Trump","Trump", bdate,"home"),new Party(null,"idk",null,null), CandidatePosition.PRESIDENT);
        Mockito.when(repo.findByTitle("testTitle")).thenReturn(expected);

        expected.incrementVotes();
        expected.incrementVotes();
        expected.incrementVotes();
        expected.incrementVotes();

        service.addVotesInBulk(toBeSent, 4L);
        Assert.assertEquals(expected.getVotes(), toBeSent.getVotes());
    }
}
