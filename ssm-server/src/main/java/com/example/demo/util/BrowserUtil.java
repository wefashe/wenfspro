package com.example.demo.util;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * @Author: Wenfs
 * @Email: wenfs@yonyou.com
 * @Date: 2018/12/6 10:11
 * @Description: TODO
 */
@Slf4j
@Component
public class BrowserUtil implements CommandLineRunner {

	@Value("${server.port:8080}")
	private String port;

	@Value("${custom.browser.browserPath:}")
	private String browserPath;

	@Value("${custom.browser.autoOpen:true}")
	private boolean autoOpen;

	@Override
	public void run(String... args) throws Exception {
		if (autoOpen) {
			String baseCmd = "cmd /c start";
			String indexUrl = "http://localhost:" + port;
			String cmd = baseCmd + " " + browserPath + " " + indexUrl;
			Runtime run = Runtime.getRuntime();
			try {
				run.exec(cmd);
				log.info("【 {} 】 run successfully at {} {}", cmd, LocalDate.now(), LocalTime.now());
			} catch (Exception e) {
				log.info("【 {} 】 run Unsuccessfully at {} {}", cmd, LocalDate.now(), LocalTime.now(), e);
			}
		}
	}
}
