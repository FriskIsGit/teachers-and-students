package com.base.teachersstudents.service;

class Constraints{
    protected static final int MIN_EMAIL_LENGTH = 5;
    protected static final int MAX_EMAIL_LENGTH = 256;
    protected static final int MIN_NAME_LENGTH = 2;
    protected static final int MIN_AGE = 18;
    protected static boolean isNameInvalid(String name){
        return name.length() < MIN_NAME_LENGTH;
    }
    protected static boolean isLastnameInvalid(String lastname){
        return lastname.length() < MIN_NAME_LENGTH;
    }
    protected static boolean isEmailInvalid(String email){
        int len = email.length();
        return len < MIN_EMAIL_LENGTH || !email.contains("@") || len > MAX_EMAIL_LENGTH;
    }
    protected static boolean isAgeInvalid(int age){
        return age < MIN_AGE;
    }
}
