package com.sofka.juancarlosmaya.forms.shop;


import org.openqa.selenium.By;

public class DashboardForm {

   public static final By TEXT_USER_NAME = By.id("user-name");
   public static final By TEXT_PASSWORD=  By.id("password");
   public static final By BUTTON_LOGIN= By.id("login-button");
   public static final By LABEL_TITLE= By.className("title");
   public static final By LABEL_ERROR_LOGIN= By.xpath("//h3[@data-test='error']");

}
