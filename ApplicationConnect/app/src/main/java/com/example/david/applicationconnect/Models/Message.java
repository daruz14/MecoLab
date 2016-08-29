package com.example.david.applicationconnect.Models;

/**
 * Created by daruz14 on 21-08-16.
 */
public class Message {
    //Agregar a quien mierda se manda el mensaje
    private String sender;
    private String content;
    private String createdAt;
    private String receptor;


    public Message(String sender, String createdAt, String content, String receptor){
        this.sender = sender;
        this.createdAt = createdAt;
        this.content = content;
        this.receptor = receptor;
    }

    public String getSender() {
        return sender;
    }

    public String getReceptor() {
        return receptor;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

}
