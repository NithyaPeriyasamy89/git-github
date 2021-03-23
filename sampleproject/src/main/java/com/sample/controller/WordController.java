package com.sample.controller;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

import com.sample.request.RequestTo;
import com.sample.service.WordService;

@RestController
public class WordController {
@Autowired	
public WordService wordService;

private static final Logger logger = LoggerFactory.getLogger(WordController.class);

	@PostMapping(value = { "/getCountOfGivenWords" })
	public Map<String, Integer> getCountOfGivenWords(@RequestBody RequestTo searchText) {
		logger.info("req paylaod{} is ", searchText.toString());
		return wordService.getCountOfGivenWords(searchText.getSearchText());
	}
	
	@GetMapping(value = { "/getWordsCountInFile/{count}" })
	public void getFileDownload(HttpServletResponse response, @PathVariable int count) throws IOException {
		response.setContentType("text/csv");
		Map<String, Integer> resultMap = new HashMap<String, Integer>();
		resultMap = wordService.getWordsCountInFile(count);

		String headerKey = "Content-Disposition";
		String headerValue = "attachment; filename=CountedWords.csv";
		response.setHeader(headerKey, headerValue);

		ICsvBeanWriter csvWriter = new CsvBeanWriter(response.getWriter(), CsvPreference.STANDARD_PREFERENCE);
		StringBuilder builder = new StringBuilder();

		resultMap.forEach((key, value) -> {
			builder.append(key);
			builder.append("|");
			builder.append(value + " ");
		});
		csvWriter.writeComment(builder.toString());
		csvWriter.close();

	}
	
}
