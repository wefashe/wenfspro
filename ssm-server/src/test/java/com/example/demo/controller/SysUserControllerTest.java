package com.example.demo.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.example.demo.entity.SysUser;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SysUserControllerTest {
	
	@Autowired
    private WebApplicationContext wac;

    private MockMvc mvc;
    private MockHttpSession session;
    
    @Before
    public void setupMockMvc(){
        mvc = MockMvcBuilders.webAppContextSetup(wac).build(); //初始化MockMvc对象
        session = new MockHttpSession();
        SysUser user =new SysUser();
        user.setUserName("root");
        user.setUserPass("root");
        session.setAttribute("user",user); //拦截器那边会判断用户是否登录，所以这里注入一个用户
    }

	@Test
	public void test() throws Exception {
//		String json="",参数
		
//		 mvc.perform(MockMvcRequestBuilders.get("/learn/resource/1001")
//	                .contentType(MediaType.APPLICATION_JSON_UTF8)
//	                .accept(MediaType.APPLICATION_JSON_UTF8)
////	                .content(json.getBytes())传参
//	                .session(session)
//	        )
//	       .andExpect(MockMvcResultMatchers.status().isOk())
//	       .andExpect(MockMvcResultMatchers.jsonPath("$.author").value("嘟嘟MD独立博客"))
//	       .andExpect(MockMvcResultMatchers.jsonPath("$.title").value("Spring Boot干货系列"))
//	       .andDo(MockMvcResultHandlers.print());
	}
	

}
