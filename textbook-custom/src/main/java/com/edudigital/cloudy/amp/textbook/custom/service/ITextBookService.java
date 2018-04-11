package com.edudigital.cloudy.amp.textbook.custom.service;

import java.util.Map;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.edudigital.cloudy.amp.textbook.base.entity.dto.ContextDTO;
import com.edudigital.cloudy.amp.textbook.base.entity.dto.DiscussDTO;
import com.edudigital.cloudy.amp.textbook.base.entity.dto.EbkDTO;
import com.edudigital.cloudy.amp.textbook.base.entity.dto.MajorDTO;
import com.edudigital.cloudy.amp.textbook.base.entity.dto.PeriodDTO;
import com.edudigital.cloudy.amp.textbook.base.entity.dto.QuestionDTO;
import com.edudigital.cloudy.amp.textbook.base.entity.dto.TextDTO;
import com.edudigital.cloudy.amp.textbook.base.entity.dto.TypeDTO;
import com.edudigital.cloudy.amp.textbook.base.entity.dto.UserBookDTO;
import com.edudigital.cloudy.amp.textbook.custom.service.fallback.TextBookServiceFallBack;
import com.edudigital.cloudy.amp.util.base.util.entity.dto.ResDTO;

@FeignClient(name = "textbook-service",fallback = TextBookServiceFallBack.class)
public interface ITextBookService {

	@RequestMapping(value = "/textbook-service/updateBk", method = RequestMethod.PUT)
	public String savePicture(@RequestBody EbkDTO ebkDTO);

	@RequestMapping(value = "/textbook-service/getUserBook", method = { RequestMethod.POST })
	public EbkDTO getUserBook(@RequestBody UserBookDTO userBookDTO);

	@RequestMapping(value = "/textbook-service/addBk", method = { RequestMethod.POST })
	public ResDTO addBk(@RequestBody EbkDTO ebkDTO);

	@RequestMapping(value = "/textbook-service/listBooks", method = { RequestMethod.GET })
	public ResDTO listBooks(@RequestParam Map<String, Object> map, @RequestParam("page") Integer page,
			@RequestParam("size") Integer size);

	@RequestMapping(value = "/textbook-service/listTypes", method = { RequestMethod.GET })
	public ResDTO listTypes(@RequestParam("typeDTO") TypeDTO typeDTO);

	@RequestMapping(value = "/textbook-service/listContexts", method = { RequestMethod.GET })
	public ResDTO listContexts(@RequestParam("str") String str, @RequestParam("page") Integer page,
			@RequestParam("size") Integer size);

	@RequestMapping(value = "/textbook-service/addContext", method = { RequestMethod.POST })
	public ResDTO addContext(@RequestBody ContextDTO contextDTO);

	@RequestMapping(value = "/textbook-service/addQuestion", method = { RequestMethod.POST })
	public ResDTO addQuestion(@RequestBody QuestionDTO questionDTO);

	@RequestMapping(value = "/textbook-service/listQuestions", method = { RequestMethod.GET })
	public ResDTO listQuestions(@RequestParam("questionDTO") QuestionDTO questionDTO);

	@RequestMapping(value = "/textbook-service/updateBk", method = { RequestMethod.PUT })
	public String updateBk(@RequestBody EbkDTO ebkDTO);

	@RequestMapping(value = "/textbook-service/addText", method = { RequestMethod.POST })
	public ResDTO addText(@RequestParam("textDTO") @RequestBody TextDTO textDTO,
			@RequestParam("ebkDTO") @RequestBody EbkDTO ebkDTO);

	@RequestMapping(value = "/textbook-service/listTexts", method = { RequestMethod.GET })
	public ResDTO listTexts(@RequestParam("textDTO") TextDTO textDTO);

	@RequestMapping(value = "/textbook-service/addDiscuss", method = { RequestMethod.POST })
	public ResDTO addDiscuss(@RequestBody DiscussDTO discuss);
	
	@RequestMapping(value = "/textbook-service/listDiscuss", method = { RequestMethod.GET })
	public ResDTO listDiscuss(@RequestParam("discussDTO") DiscussDTO discussDTO);

	@RequestMapping(value = "/textbook-service/listCourses", method = { RequestMethod.GET })
	public ResDTO listCourses(@RequestParam("majorDTO") MajorDTO majorDTO);

	@RequestMapping(value = "/textbook-service/listPeriod", method = { RequestMethod.GET })
	public ResDTO listPeriod(@RequestParam("periodDTO") PeriodDTO periodDTO);

	@RequestMapping(value = "/textbook-service/deleteContext", method = { RequestMethod.DELETE })
	public ResDTO deleteContext(@RequestParam("str") String str);

	@RequestMapping(value = "/textbook-service/listMajors", method = { RequestMethod.GET })
	public ResDTO listMajors(@RequestParam("type") Integer type);

	@RequestMapping(value = "/textbook-service/data/getDataDownloadsNum", method = { RequestMethod.GET })
	public void getDataDownloadsNum();

	@RequestMapping(value = "/textbook-service/add", method = { RequestMethod.POST })
	public void add(@RequestParam("str") String str);

	@RequestMapping(value = "/textbook-service/listEbkData", method = { RequestMethod.GET })
	public ResDTO listEbkData(@RequestParam Map<String, Object> map);

	@RequestMapping(value = "/textbook-service/listContents", method = { RequestMethod.GET })
	public ResDTO listContents(@RequestParam Map<String, Object> map);

	@RequestMapping(value = "/textbook-servicemob/getEbkCompressed", method = { RequestMethod.GET })
	public void getData(@RequestParam("str") String str);
}
