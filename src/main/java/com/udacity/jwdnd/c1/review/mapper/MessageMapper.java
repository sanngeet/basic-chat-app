package com.udacity.jwdnd.c1.review.mapper;

import com.udacity.jwdnd.c1.review.model.ChatMessage;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface MessageMapper {
    @Select("SELECT * FROM messages")
    List<ChatMessage> getAllMessages();

    @Insert("INSERT INTO messages (username, messagetext) VALUES(#{username}, #{messageText})")
    @Options(useGeneratedKeys = true, keyProperty = "messageId")
    int addMessage(ChatMessage message);
}
