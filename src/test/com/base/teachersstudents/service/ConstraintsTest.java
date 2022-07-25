package com.base.teachersstudents.service;

import org.junit.Test;

import static com.base.teachersstudents.service.Constraints.*;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ConstraintsTest{
    @Test
    public void nameTest1(){
        assertFalse(isNameValid("z"));
    }
    @Test
    public void nameTest2(){
        assertTrue(isNameValid("Jack"));
    }
    @Test
    public void mailTest1(){
        assertFalse(isEmailValid("46.3433.5.21.sadfqf"));
    }
    @Test
    public void mailTest2(){
        assertTrue(isEmailValid("abc@def.jkl"));
    }
    @Test
    public void mailTest3(){
        assertFalse(isEmailValid("a@g"));
    }
    @Test
    public void mailTest4(){
        assertTrue(isEmailValid("any@gm.domain"));
    }
    @Test
    public void lastname1(){
        assertTrue(isLastnameValid("Tawer"));
    }
    @Test
    public void lastname2(){
        assertTrue(isLastnameValid("An"));
    }
    @Test
    public void lastname3(){
        assertFalse(isLastnameValid("p"));
    }
    @Test
    public void lastname4(){
        assertFalse(isLastnameValid(null));
    }
    @Test
    public void ageTest1(){
        assertFalse(isAgeValid(12));
    }
    @Test
    public void ageTest2(){
        assertTrue(isAgeValid(18));
    }
}
