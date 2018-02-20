package com.example.yanglei.myapplication;

/**
 * Created by yanglei on 2018/2/20.
 */

import static com.example.yanglei.myapplication.MainActivity.CheckID;
import static com.example.yanglei.myapplication.MainActivity.Checkpassword;
import static com.example.yanglei.myapplication.First.CheckEmpty;
import static org.junit.Assert.*;

import org.junit.Test;

public class ProjectTest {

    @Test
    public void testLoginpass(){
        String id,password;
        id="User";
        password="123456";
        assertTrue(CheckID(id));
        assertTrue(Checkpassword(password));
    }

    @Test
    public void testLoginFail(){
        String id,password;
        id="user";
        password="23456";
        assertFalse(CheckID(id));
        assertFalse(Checkpassword(password));
    }

    @Test
    public void testEmpty(){
        String name,pw;
        name="";
        pw="";
        assertTrue(CheckEmpty(name,pw));
    }

}
