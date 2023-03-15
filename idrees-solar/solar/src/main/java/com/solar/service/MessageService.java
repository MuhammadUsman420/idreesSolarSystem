package com.solar.service;

import com.solar.dto.EmailDetailsDto;
import com.solar.dto.LocationDto;
import com.solar.dto.MessageDto;
import com.solar.dto.SolarFormDto;
import com.solar.model.Location;
import com.solar.model.Message;
import com.solar.model.SolarForm;
import com.solar.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MessageService {

    private MessageRepository messageRepository;

    public MessageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    public List<Message> getAllMessage(){
        return messageRepository.findAll();
    }

    public MessageDto getMessageById(Long id) throws Exception {
        Optional<Message> message =  messageRepository.findById(id);
        if(message.isPresent()){
            return toDto(message.get());
        }else {
            throw new Exception();
        }
    }

    public void deleteMessageById(Long id) throws Exception {
        try {
            messageRepository.deleteById(id);
        }catch (Exception e){
            throw new Exception();
        }
    }


    public MessageDto addMessage(MessageDto messageDto) {
        MessageDto _messageDto = toDto(messageRepository.save(dto(messageDto)));
        return _messageDto;
    }

    public MessageDto updateMessage(Long id, Message message) {
        try {
            Message updateMessage = getAllMessage().stream().filter(el -> el.getId().equals(id)).findAny().get();
            if (updateMessage != null) {
                updateMessage.setMessage(message.getMessage());
                updateMessage.setEmail(message.getEmail());
                updateMessage.setFirstName(message.getFirstName());
                updateMessage.setLastName(message.getLastName());
            }
            return toDto(messageRepository.save(updateMessage));
        }catch (Exception e){
            throw new RuntimeException("Cannot Update Solar Form "+e);
        }
    }

    public Message dto(MessageDto messageDto){
        return Message.builder()
                .id(messageDto.getId())

                .email(messageDto.getEmail())
                .firstName(messageDto.getFirstName())
                .lastName(messageDto.getLastName())
                .message(messageDto.getMessage())

                .build();
    }

    public MessageDto toDto(Message message){
        return  MessageDto.builder()
                .id(message.getId())

                .firstName(message.getFirstName())
                .lastName(message.getLastName())
                .email(message.getEmail())
                .message(message.getMessage())

                .build();
    }
}
