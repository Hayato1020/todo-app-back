package jp.cloudace.backend.todoapp.usecase.impl;

import jp.cloudace.backend.todoapp.usecase.Hello;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class HelloImpl implements Hello {

    private static final Logger logger = LoggerFactory.getLogger(HelloImpl.class);

    @Override
    public String action() {
        logger.debug("HelloImpl#action");
        return "Welcome to Todo App !";
    }
}
