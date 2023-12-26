package com.peppacatt.notes.springboottest.mytest.mydt;

import org.springframework.stereotype.Component;

@Component
public class InjectMockDao {
    public String myName() {
        return "dao";
    }
}
