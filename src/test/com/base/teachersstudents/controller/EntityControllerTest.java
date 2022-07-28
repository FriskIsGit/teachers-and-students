package com.base.teachersstudents.controller;

import com.base.teachersstudents.TeachersStudentsApplication;
import com.base.teachersstudents.entities.Student;
import com.base.teachersstudents.entities.Teacher;
import com.base.teachersstudents.service.StudentService;
import com.base.teachersstudents.service.TeacherService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.io.UnsupportedEncodingException;

import static org.junit.jupiter.api.Assertions.*;

@AutoConfigureJsonTesters
@RunWith(SpringRunner.class)
@SpringBootTest(classes = TeachersStudentsApplication.class)
@TestPropertySource(locations = "classpath:application.properties")
public class EntityControllerTest{
    final static String API_ENDPOINT = "/api/v1/";
    @Autowired
    private TeacherService teacherService;
    @Autowired
    private StudentService studentService;

    @Autowired
    private JacksonTester<Teacher> teacherTester;

    @Autowired
    private JacksonTester<Student> studentTester;

    @Autowired
    WebApplicationContext webApplicationContext;

    private MockMvc mvc;
    @Autowired
    protected void setMvc() {
        mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }
    @Test
    public void getAPI(){
        final int expectedStatus = 200;
        MvcResult result;
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get(API_ENDPOINT);
        try{
            result = mvc.perform(request).andReturn();
        }catch (Exception exc){
            assertFalse(false);
            return;
        }

        int status = result.getResponse().getStatus();
        assertEquals(expectedStatus, status);
        String content;
        try{
            content = result.getResponse().getContentAsString();
        }catch (UnsupportedEncodingException ueExc){
            assertFalse(false);
            return;
        }
        assertTrue(content.startsWith("Welcome to API v1"));
    }
    @Test
    public void postStudent(){
        final int expectedStatus = 201;
        final String studentInJson = "{\"name\":\"Abram\",\"lastname\":\"Mangle\",\"email\":\"abrammangle@mail.og\",\"age\":20,\"major\":\"Technology\"}";
        MvcResult result;
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .post(API_ENDPOINT + "student")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(studentInJson);
        try{
            result = mvc.perform(request).andReturn();
        }catch (Exception exc){
            assertFalse(false);
            return;
        }
        assertEquals(expectedStatus, result.getResponse().getStatus());
    }
    @Test
    public void postTeacher(){
        final int expectedStatus = 201;
        final String teacherInJson = "{\"name\":\"Ileana\",\"lastname\":\"Slate\",\"email\":\"illieslate@mail.og\",\"age\":27,\"subject\":\"Economics\"}";
        MvcResult result;
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .post(API_ENDPOINT + "teacher")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(teacherInJson);
        try{
            result = mvc.perform(request).andReturn();
        }catch (Exception exc){
            assertFalse(false);
            return;
        }
        assertEquals(expectedStatus, result.getResponse().getStatus());
    }
    @Test
    public void lackOfContentType(){
        //unsupported media type
        final int expectedStatus = 415;
        final String content = "{\"name\":\"Ileana\",\"lastname\":\"Slate\",\"email\":\"illieslate@mail.og\",\"age\":27,\"subject\":\"Economics\"}";
        MvcResult result;
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .post(API_ENDPOINT + "teacher")
                .content(content);
        try{
            result = mvc.perform(request).andReturn();
        }catch (Exception exc){
            assertFalse(false);
            return;
        }
        assertEquals(expectedStatus, result.getResponse().getStatus());
    }
    @Test
    public void incorrectEntity(){
        //unprocessable entity
        final int expectedStatus = 422;
        final String teacherInJson = "{\"name\":\"Ileana\",\"lastname\":\"Slate\",\"email\":\"illieslate@mail.og\",\"subject\":\"Economics\"}";
        MvcResult result;
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .post(API_ENDPOINT + "teacher")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(teacherInJson);
        try{
            result = mvc.perform(request).andReturn();
        }catch (Exception exc){
            assertFalse(false);
            return;
        }
        assertEquals(expectedStatus, result.getResponse().getStatus());
    }
    @Test
    public void emptyContent(){
        //bad request
        final int expectedStatus = 400;
        final String content = "";
        MvcResult result;
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .post(API_ENDPOINT + "teacher")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(content);
        try{
            result = mvc.perform(request).andReturn();
        }catch (Exception exc){
            assertFalse(false);
            return;
        }
        assertEquals(expectedStatus, result.getResponse().getStatus());
    }
    @Test
    public void getTeacherAndStudentCount(){
        //bad request
        final int expectedStatus = 200;
        MvcResult result1;
        MvcResult result2;
        MockHttpServletRequestBuilder request1 = MockMvcRequestBuilders.get(API_ENDPOINT + "teachers/count");
        MockHttpServletRequestBuilder request2 = MockMvcRequestBuilders.get(API_ENDPOINT + "students/count");
        try{
            result1 = mvc.perform(request1).andReturn();
            result2 = mvc.perform(request2).andReturn();
        }catch (Exception exc){
            assertFalse(false);
            return;
        }
        assertEquals(expectedStatus, result1.getResponse().getStatus());
        assertEquals(expectedStatus, result2.getResponse().getStatus());
        try{
            String content1 = result1.getResponse().getContentAsString();
            String content2 = result2.getResponse().getContentAsString();
            long count1 = Long.parseLong(content1);
            long count2 = Long.parseLong(content2);
            assertTrue(count1 >= 0);
            assertTrue(count2 >= 0);
        }catch (UnsupportedEncodingException exc){
            assertFalse(false);
        }
    }
}
