package kh.mclass.shushoong;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import kh.mclass.shushoong.airline.controller.AirlineController;
import kh.mclass.shushoong.airline.model.service.AirlineService;

@SpringBootTest
@WebAppConfiguration
class ShushoongApplicationTests {
	
	//Mock MVC - Controller 가능. service, repository 불가
	@Autowired
	private WebApplicationContext wac;
	
	private MockMvc mockMvc;
	
	private AirlineController AirlineController;
	
//	private static AirlineService service; //@BeforeAll용
	private AirlineService service;
	
//	@BeforeAll   //JUnit 4의 @Before
//	static void setUp() {
//		//주로 service 객체 생성
//		System.out.println("========setUp");
//		service = new AirlineService();
//		
//	}
	
	@BeforeEach   //JUnit 4의 @Before
	void setUp() {
//		this.mockMvc = MockMvcBuilders.standaloneSetup(AirlineController).build();  //AirlineController ㄱ
		this.mockMvc = 
		
		//주로 service 객체 생성
		System.out.println("========setUp");
		this.service = new AirlineService();
		
	}
	
	@Test
	void contextLoads() {		
		System.out.println("========contextLoads");
	String a = "aaa";
	String b = "bbb";
	
	assertNotNull(a);
//	assertSame(b, a);
//	assertEquals(a, b);
//	assertTrue(a==b);
//	assertFalse(a==b);
//	assertArrayEquals(arr1, arr2);
	
	}
	
	@Test
	void contextLoads2() {		
		System.out.println("========contextLoads2");
		String a = "aaa";
		String b = "bbb";
		
		assertEquals(a, b);
		
	}

}
