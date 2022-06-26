package com.base.teachersstudents.service;

//retrieving an entity using a null value returns empty results with default spring generated repo methods
//therefore saving null fields shouldn't be possible either
class Constraints{
    protected static final int MIN_EMAIL_LENGTH = 5;
    protected static final int MAX_EMAIL_LENGTH = 256;
    protected static final int MIN_NAME_LENGTH = 2;
    protected static final int MIN_AGE = 18;
    protected static boolean isNameInvalid(String name){
        if(name == null){
            return true;
        }
        return name.length() < MIN_NAME_LENGTH;
    }
    protected static boolean isLastnameInvalid(String lastname){
        if(lastname == null){
            return true;
        }
        return lastname.length() < MIN_NAME_LENGTH;
    }
    protected static boolean isEmailInvalid(String email){
        if(email == null){
            return true;
        }
        int len = email.length();
        return len < MIN_EMAIL_LENGTH || !email.contains("@") || len > MAX_EMAIL_LENGTH;
    }
    protected static boolean isAgeInvalid(int age){
        return age < MIN_AGE;
    }
}
