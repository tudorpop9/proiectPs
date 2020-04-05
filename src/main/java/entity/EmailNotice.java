package entity;

import javax.jws.soap.SOAPBinding;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class EmailNotice implements ElectionObserver{

    private static final String SUBJECT = "ElectionNoReply";
    private static final String EMAIL = "VoteAppNotification@gmail.com";
    private static final String PASSWORD = "ProiectPS-2020";

    private List<User> users;

    public EmailNotice() {
        users = new ArrayList<>();
    }

    @Override
    public void notify(Object obj) throws MessagingException {

        for(User u: users){
            this.sendNotification(u.getEmail(), (String)obj);
        }
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public void addAnUser(User usr){
        this.users.add(usr);
    }

    public void addMoreUsers(List<User> newUrs){
        for(User u : newUrs){
            this.users.add(u);
        }
    }

    public void deleteUser(User usr){
        this.users.remove(usr);
    }

    private void sendNotification(String to, String content) throws MessagingException {
        Session session = createSession();

        MimeMessage message = new MimeMessage(session);
        setEmailMessage(message,to, content);

        Transport.send(message);
    }

    private static void setEmailMessage(MimeMessage message, String to, String content) throws MessagingException{
        message.setText(content);
        message.setFrom(new InternetAddress(EMAIL));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
        message.setSubject(EmailNotice.SUBJECT);
    }

    private static Session createSession(){
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.1and1.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(EMAIL, PASSWORD);
            }
        });

        return session;
    }

}
