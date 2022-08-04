package jp.cloudace.backend.todoapp.infra.controller;

import jp.cloudace.backend.todoapp.usecase.Hello;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.time.ZoneId;

@RestController
public class MainController {

    private static final Logger logger = LoggerFactory.getLogger(MainController.class);

    private final Hello hello;

    public MainController(Hello hello) {
        this.hello = hello;
    }

    @RequestMapping("/")
    public String helloCloud(HttpServletRequest request) {
        logger.debug("DemoController#HelloCloud");
        return hello.action();
    }

    @RequestMapping("/now")
    public String now() {
        logger.debug("DemoController#now");
        LocalDateTime now1 = LocalDateTime.now();
        LocalDateTime now2 = LocalDateTime.now(ZoneId.of("Asia/Tokyo"));
        String res = "LocalDateTime.now() : " + now1 + "<BR>";
        res += "LocalDateTime.now(ZoneId.of(\"Asia/Tokyo\")) : " + now2 + "<BR>";
        return res;
    }
}
