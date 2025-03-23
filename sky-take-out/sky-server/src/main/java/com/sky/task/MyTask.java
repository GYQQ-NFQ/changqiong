// @author Tjzlo
// @version 2025/3/21 11:06

package com.sky.task;

import lombok.extern.slf4j.Slf4j;

import java.util.Date;

//@Component
@Slf4j
public class MyTask {

//    @Scheduled(cron = "0/10 * * * * *")
    public void myTask() {
        log.info("定时任务开始执行:{}",new Date());
    }


}
