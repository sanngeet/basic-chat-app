package com.udacity.jwdnd.c1.review.spo;

import com.udacity.jwdnd.c1.review.model.ChatMessage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ChatPage {

    @FindBy(id = "messageText")
    private WebElement messageText;

    @FindBy(id = "submitButton")
    private WebElement submitButton;

    @FindBy(id = "logoutButton")
    private WebElement logoutButton;

    @FindBy(className = "chatMessageUsername")
    private WebElement firstMessageUsername;

    @FindBy(className = "chatMessageText")
    private WebElement firstMessageText;

    public ChatPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public void sendMessage(String message) {
        messageText.sendKeys(message);
        submitButton.click();
    }

    public ChatMessage getFirstMessage() {
        ChatMessage result = new ChatMessage();
        result.setMessage(firstMessageText.getText());
        result.setUsername(firstMessageUsername.getText());
        return result;
    }

    public void logOut() {
        logoutButton.click();
    }
}
