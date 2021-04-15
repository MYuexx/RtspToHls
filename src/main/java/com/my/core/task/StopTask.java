package com.my.core.task;

import com.my.core.commandManager.CommandManager;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import javax.annotation.Resource;
import java.time.LocalDateTime;

/**
 * 〈一句话功能简述〉<br>
 * 〈定时关闭推流，防止前端无法主动停止导致资源堆积〉
 *
 * @author M.Y
 * @date 2021/4/14
 * @since 1.0.0
 */
@EnableScheduling
@Configuration
public class StopTask {
    @Resource
    CommandManager manager;

    @Scheduled(cron = "* */10 * * * ?")
    public void stopTask(){
        manager.queryAll().stream().filter(c->c.getCreateTime().isBefore(LocalDateTime.now().plusMinutes(-10)))
                .forEach(c->manager.stop(c.getId()));
    }
}