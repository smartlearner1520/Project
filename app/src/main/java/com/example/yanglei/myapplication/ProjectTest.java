package com.example.yanglei.myapplication;

/**
 * Created by yanglei on 2018/2/20.
 */


import android.widget.TextView;
import android.widget.Toast;

import static org.junit.Assert.*;

import org.junit.Test;

public class ProjectTest {

    MainActivity m = new MainActivity();
    First r = new First();
    @Test
    public void testLoginpass(){
        String state;
        state="SUCCESS";
        assertTrue(m.Check(state));
    }

    @Test
    public void testLoginFail(){
        String state;
        state="FAIL";
        assertFalse(m.Check(state));
    }

//    @Test
//    public void testEmpty(){
//        String id,pw;
//        id="";
//        pw="";
//        Boolean state=m.NotEmpty(id,pw);
//        assertFalse(state);
//    }
//
//    @Test
//    public void testEmptyid(){
//        String id,pw;
//        id="";
//        pw="mypassword";
//        Boolean state=m.NotEmpty(id,pw);
//
//        assertFalse(state);
//    }
//
//    @Test
//    public void testEmptypw(){
//        String id,pw;
//        id="myid";
//        pw="";
//        Boolean state=m.NotEmpty(id,pw);
//        assertFalse(state);
//    }
//
//    @Test
//    public void testNotEmpty(){
//        String id,pw;
//        id="myid";
//        pw="mypassword";
//        assertTrue(m.NotEmpty(id,pw));
//    }

    @Test
    public void testEmptyWhenReg(){
        String name,pw;
        name="";
        pw="";
        assertTrue(r.CheckEmpty(name,pw));
    }

}
