package KledingBib.demo.controller;


import KledingBib.demo.models.Email;
import KledingBib.demo.repository.EmailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmailController {

@Autowired
    private EmailRepository emailRepository;


    @PostMapping("/sendMail")
    public String sendMail(@RequestBody Email email) {
        String status = emailRepository.sendMail(email);
        return status;
    }
    @PostMapping("/sendWithAttachment")
    public String sendWithAttachment(@RequestBody Email email) {
        String status = emailRepository.sendWithAttachment(email);
        return status;

}}
