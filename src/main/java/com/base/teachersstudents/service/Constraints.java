package com.base.teachersstudents.service;

//retrieving an entity using a null value returns empty results with default spring generated repo methods
//therefore saving null fields shouldn't be possible either
class Constraints{
    protected static final int MIN_EMAIL_LEN = 5;
    protected static final int MAX_EMAIL_LEN = 256;
    protected static final int MIN_NAME_LEN = 2;
    protected static final int MIN_AGE = 18;
    protected static boolean isNameValid(String name){
        if(name == null){
            return false;
        }
        return name.length() >= MIN_NAME_LEN;
    }
    protected static boolean isLastnameValid(String lastname){
        if(lastname == null){
            return false;
        }
        return lastname.length() >= MIN_NAME_LEN;
    }
    protected static boolean isEmailValid(String email){
        if(email == null){
            return false;
        }
        int len = email.length();
        return len >= MIN_EMAIL_LEN && email.contains("@") && email.contains(".") && len <= MAX_EMAIL_LEN;
    }
    protected static boolean isAgeValid(int age){
        return isAdult(age);
    }
    private static boolean isAdult(int age){
        return age >= MIN_AGE;
    }
}
