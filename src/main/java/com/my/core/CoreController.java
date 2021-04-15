package com.my.core;

import com.alibaba.fastjson.JSONObject;
import com.my.core.commandManager.CommandManager;
import com.my.core.commandManager.commandbuidler.CommandBuidlerFactory;
import com.my.core.commandManager.data.CommandTasker;
import com.my.core.entity.StartParam;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.Random;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author M.Y
 * @date 2021/4/2
 * @since 1.0.0
 */
@SpringBootApplication
@RestController
@EnableAsync
public class CoreController {

    public static void main(String[] args) {
        SpringApplication.run(CoreController.class, args);
    }

    @Resource
    CommandManager manager;

    @PostMapping("/start")
    public JSONObject test(@RequestBody @Validated StartParam param) {
        String name;
        if(param.getName()==null || "".equals(param.getName())){
            name = getRandomString(10);
        }else{
            name = param.getName();
        }

        CommandTasker query = manager.query(name);
        if (query == null) {
            manager.start(name, CommandBuidlerFactory.createBuidler()
                    .add("ffmpeg").add("-f", "rtsp")
                    .add("-rtsp_transport", "tcp")
                    .add("-i", param.getUrl())
                    .add("-c", "copy")
                    .add("-f", "hls")
                    .add("-hls_time", "2.0")
                    .add("-hls_list_size", "1")
                    .add("-hls_wrap", "15")
                    .add("/tmp/hls/" + name + ".m3u8"));
        }else{
            query.setCreateTime(LocalDateTime.now());
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("url", "/hls/" + name + ".m3u8");
        jsonObject.put("name", name);
        jsonObject.put("code", 8200);
        jsonObject.put("message", "请求成功。");
        return jsonObject;
    }

    @GetMapping("/stop")
    public String stop(String name) {
        manager.stop(name);
        return "停止成功";
    }

    @PreDestroy
    public void destory() {
        manager.stopAll();
    }

    private static final Random RANDOM = new Random();

    private static String getRandomString(int length) {
        String str = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int number = RANDOM.nextInt(62);
            sb.append(str.charAt(number));
        }
        return sb.toString();
    }
}