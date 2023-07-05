package KledingBib.demo.repository;

import KledingBib.demo.models.Email;

public interface EmailRepository {
    String sendMail(Email email);

    String sendWithAttachment(Email email);
}