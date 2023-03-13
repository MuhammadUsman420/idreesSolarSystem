package com.solar.service;


import com.solar.dto.EmailDetailsDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${spring.mail.username}") private String sender;

    public String sendSimpleMail(EmailDetailsDto details) {
        try {
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setFrom(sender);
            mailMessage.setTo(details.getRecipient());
            mailMessage.setSubject(details.getSubject());
//            mailMessage.setText(String.valueOf(details.getSolarFormDto()));
            mailMessage.setText("id : "+details.getSolarFormDto().getId()+"\n"+
                    "Name : "+details.getSolarFormDto().getFirstName()+" "+details.getSolarFormDto().getLastName()+"\n"+
                    "Company : "+details.getSolarFormDto().getCompany()+"\n"+
                    "Address : "+details.getSolarFormDto().getAddress()+"\n"+
                    "Country : "+details.getSolarFormDto().getCountry()+"\n"+
                    "Email : "+details.getSolarFormDto().getEmail()+"\n"+
                    "Phone Number : "+details.getSolarFormDto().getPhoneNumber()+"\n"+
                    "Consumption : "+details.getSolarFormDto().getConsumption()+"\n"+
                    "Notes : "+details.getSolarFormDto().getNotes()+"\n"+
                    "Roof Type : "+details.getSolarFormDto().getRoofType()+"\n"+
                    "Roof Inclination : "+details.getSolarFormDto().getRoofInclination()+"\n"+
                    "Roofing : "+details.getSolarFormDto().getRoofing()+"\n"+
                    "Building Height : "+details.getSolarFormDto().getBuildingHeight()+"\n"+
                    "Area : "+details.getSolarFormDto().getArea()+"\n"+
                    "Lease Roof Top : "+details.getSolarFormDto().getLeaseRooftop()+"\n"+
                    "Rent Roof Top : "+details.getSolarFormDto().getRentRooftop()+"\n"+
                    "Buy Roof Top : "+details.getSolarFormDto().getBuyRooftop()+"\n"+
                    "Attachment : "+details.getSolarFormDto().getAttachment()+"\n"+
                    "Location : "+details.getSolarFormDto().getLocations()+"\n"+
                    "Privacy  : "+details.getSolarFormDto().getPrivacyCheck()+"\n"
            );
            javaMailSender.send(mailMessage);
            return "Mail Sent Successfully...";
        }
        catch (Exception e) {
            e.printStackTrace();
            return "Error while Sending Mail";
        }
    }
}
