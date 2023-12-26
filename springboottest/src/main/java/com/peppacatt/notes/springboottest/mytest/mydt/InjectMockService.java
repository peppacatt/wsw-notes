package com.peppacatt.notes.springboottest.mytest.mydt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InjectMockService {
    @Autowired
    InjectMockDao injectMockDao;

    public String myName() {
        return injectMockDao.myName();
    }
}
