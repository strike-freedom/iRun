package com.example.irun;

import android.text.SpannableString;

//聊天信息
public class ChatMsgEntity {

	private String name;//消息来自  
    private String date;//消息日期  
    private SpannableString message;//消息内容  
    private boolean isSend = true;//是否为发送的消息  
    
    public String getName() {  
        return name;  
    }  
  
    public void setName(String name) {  
        this.name = name;  
    }  
  
    public String getDate() {  
        return date;  
    }  
  
    public void setDate(String date) {  
        this.date = date;  
    }  
  
    public SpannableString getMessage() {  
        return message;  
    }  
  
    public void setMessage(SpannableString message) {  
        this.message = message;  
    }  
 
    public boolean getMsgType() {  
        return isSend;  
    }  
  
    public void setMsgType(boolean isSend) {  
        this.isSend = isSend;  
    } 
    
    public ChatMsgEntity() {
    	
    }
    
    public ChatMsgEntity(String name, String date, SpannableString message, boolean isSend) {  
        this.name = name;  
        this.date = date;  
        this.message = message;
        this.isSend = isSend;
    }
}
