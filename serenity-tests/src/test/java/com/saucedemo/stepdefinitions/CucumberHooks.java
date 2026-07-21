package com.saucedemo.stepdefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import net.serenitybdd.screenplay.actors.OnlineCast;

import static net.serenitybdd.screenplay.actors.OnStage.drawTheCurtain;
import static net.serenitybdd.screenplay.actors.OnStage.setTheStage;

public class CucumberHooks {

    @Before
    public void prepareStage() {
        setTheStage(new OnlineCast());
    }

    @After
    public void closeStage() {
        drawTheCurtain();
    }
}
