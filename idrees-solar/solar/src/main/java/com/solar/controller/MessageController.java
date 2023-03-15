package com.solar.controller;

import com.solar.dto.MessageDto;
import com.solar.model.Message;
import com.solar.service.MessageService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/message")
public class MessageController {

    private MessageService messageService;

    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @GetMapping("")
    public ResponseEntity<List<Message>> getMessage(){
        List<Message> message= messageService.getAllMessage();
        return ResponseEntity.ok(message);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MessageDto> getMessageById(@PathVariable Long id) throws Exception {
        MessageDto messageDto = messageService.getMessageById(id);
        return  ResponseEntity.ok(messageDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMessage(@PathVariable Long id) throws Exception {
        messageService.deleteMessageById(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<MessageDto> updateMessageById(@PathVariable Long id , @RequestBody Message message) throws Exception {
        try{
            return ResponseEntity.ok(messageService.updateMessage(id, message));
        }catch (Exception e){
            System.out.println(e);
            throw new Exception("Cannot update No Message Exist having id "+id);
        }
    }

    @PostMapping()
    public ResponseEntity<MessageDto> addMessage(@RequestBody MessageDto messageDto) {
        try{
            return ResponseEntity.ok(messageService.addMessage(messageDto));
        }catch (Exception e){
            System.out.println(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
