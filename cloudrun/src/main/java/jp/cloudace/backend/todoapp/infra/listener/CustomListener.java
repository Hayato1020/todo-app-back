package jp.cloudace.backend.todoapp.infra.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;

@Component
public class CustomListener implements ServletRequestListener {

    private static final Logger logger = LoggerFactory.getLogger(CustomListener.class);

    @Override
    public void requestInitialized(ServletRequestEvent sre) {
        logger.debug("******* RequestInitialized *******");
        // リクエスト開始時の処理を行う。
        // (実装は省略)
    }

    @Override
    public void requestDestroyed(ServletRequestEvent sre) {
        logger.debug("******* RequestDestroyed *******");
        // リクエスト終了時の処理を行う。
        // (実装は省略)
    }

}
