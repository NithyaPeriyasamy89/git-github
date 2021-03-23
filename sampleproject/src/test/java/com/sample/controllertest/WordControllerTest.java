package com.sample.controllertest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.sample.controller.WordController;
import com.sample.request.RequestTo;
import com.sample.service.WordService;

@ExtendWith(MockitoExtension.class)
@SpringBootTest(classes=WordController.class)
public class WordControllerTest {
	
	@InjectMocks
	private WordController wordController;
	
	 @MockBean
	 WordService service;
	
	
	@Test
	public void getCountOfGivenWords() {
		RequestTo req= new RequestTo();
		String[] searchText = {"sed","it"};
		req.setSearchText(searchText);
        Map<String,Integer> result= new HashMap<String,Integer>();
        result.put("sed", 2);
		when(service.getCountOfGivenWords(Mockito.any())).thenReturn(result);
		Map<String,Integer> expectedResult= wordController.getCountOfGivenWords(req);
		assertEquals(2, expectedResult.get("sed"));
	}

}
