package entity;

import javax.mail.MessagingException;

public interface ElectionObserver {
    public void notify(Object obj) throws MessagingException;
}
