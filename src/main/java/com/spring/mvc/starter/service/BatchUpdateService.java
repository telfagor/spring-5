package com.spring.mvc.starter.service;

import com.spring.mvc.starter.dao.BatchUpdateDao;
import com.spring.mvc.starter.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BatchUpdateService {

    private final BatchUpdateDao batchUpdateDao;

    @Autowired
    public BatchUpdateService(BatchUpdateDao batchUpdateDao) {
        this.batchUpdateDao = batchUpdateDao;
    }

    public void simpleUpdate() {
        batchUpdateDao.simpleUpdate();
    }

    public void batchUpdate() {
        batchUpdateDao.testBatchUpdate();
    }
}
