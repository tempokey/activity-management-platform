package com.edudigital.cloudy.amp.textbook.custom.service.fallback;

import java.util.Map;

import org.springframework.stereotype.Component;

import com.edudigital.cloudy.amp.textbook.base.entity.dto.ContextDTO;
import com.edudigital.cloudy.amp.textbook.base.entity.dto.DiscussDTO;
import com.edudigital.cloudy.amp.textbook.base.entity.dto.EbkDTO;
import com.edudigital.cloudy.amp.textbook.base.entity.dto.MajorDTO;
import com.edudigital.cloudy.amp.textbook.base.entity.dto.PeriodDTO;
import com.edudigital.cloudy.amp.textbook.base.entity.dto.QuestionDTO;
import com.edudigital.cloudy.amp.textbook.base.entity.dto.TextDTO;
import com.edudigital.cloudy.amp.textbook.base.entity.dto.TypeDTO;
import com.edudigital.cloudy.amp.textbook.base.entity.dto.UserBookDTO;
import com.edudigital.cloudy.amp.textbook.custom.service.ITextBookService;
import com.edudigital.cloudy.amp.textbook.custom.util.EntityUtils;
import com.edudigital.cloudy.amp.util.base.util.constant.enumeration.ResCode;
import com.edudigital.cloudy.amp.util.base.util.entity.dto.ResDTO;
@Component
public class TextBookServiceFallBack implements ITextBookService {

	@Override
	public String savePicture(EbkDTO ebkDTO) {
		
		return "";
	}

	@Override
	public EbkDTO getUserBook(UserBookDTO userBookDTO) {
		return new EbkDTO();
	}

	@Override
	public ResDTO addBk(EbkDTO ebkDTO) {
		ResDTO resDTO = new ResDTO();
		EntityUtils.pkg(resDTO, ResCode.FAILURE_SERVICE);
		return resDTO;
	}

	@Override
	public ResDTO listBooks(Map<String, Object> map, Integer page, Integer size) {
		ResDTO resDTO = new ResDTO();
		EntityUtils.pkg(resDTO, ResCode.FAILURE_SERVICE);
		return resDTO;
	}

	@Override
	public ResDTO listTypes(TypeDTO typeDTO) {
		ResDTO resDTO = new ResDTO();
		EntityUtils.pkg(resDTO, ResCode.FAILURE_SERVICE);
		return resDTO;
	}

	@Override
	public ResDTO listContexts(String str, Integer page, Integer size) {
		ResDTO resDTO = new ResDTO();
		EntityUtils.pkg(resDTO, ResCode.FAILURE_SERVICE);
		return resDTO;
	}

	@Override
	public ResDTO addContext(ContextDTO contextDTO) {
		ResDTO resDTO = new ResDTO();
		EntityUtils.pkg(resDTO, ResCode.FAILURE_SERVICE);
		return resDTO;
	}

	@Override
	public ResDTO addQuestion(QuestionDTO questionDTO) {
		ResDTO resDTO = new ResDTO();
		EntityUtils.pkg(resDTO, ResCode.FAILURE_SERVICE);
		return resDTO;
	}

	@Override
	public ResDTO listQuestions(QuestionDTO questionDTO) {
		ResDTO resDTO = new ResDTO();
		EntityUtils.pkg(resDTO, ResCode.FAILURE_SERVICE);
		return resDTO;
	}

	@Override
	public String updateBk(EbkDTO ebkDTO) {
		return "error";
	}

	@Override
	public ResDTO addText(TextDTO textDTO, EbkDTO ebkDTO) {
		ResDTO resDTO = new ResDTO();
		EntityUtils.pkg(resDTO, ResCode.FAILURE_SERVICE);
		return resDTO;
	}

	@Override
	public ResDTO listTexts(TextDTO textDTO) {
		ResDTO resDTO = new ResDTO();
		EntityUtils.pkg(resDTO, ResCode.FAILURE_SERVICE);
		return resDTO;
	}

	@Override
	public ResDTO addDiscuss(DiscussDTO discuss) {
		ResDTO resDTO = new ResDTO();
		EntityUtils.pkg(resDTO, ResCode.FAILURE_SERVICE);
		return resDTO;
	}

	@Override
	public ResDTO listDiscuss(DiscussDTO discussDTO) {
		ResDTO resDTO = new ResDTO();
		EntityUtils.pkg(resDTO, ResCode.FAILURE_SERVICE);
		return resDTO;
	}

	@Override
	public ResDTO listCourses(MajorDTO majorDTO) {
		ResDTO resDTO = new ResDTO();
		EntityUtils.pkg(resDTO, ResCode.FAILURE_SERVICE);
		return resDTO;
	}

	@Override
	public ResDTO listPeriod(PeriodDTO periodDTO) {
		ResDTO resDTO = new ResDTO();
		EntityUtils.pkg(resDTO, ResCode.FAILURE_SERVICE);
		return resDTO;
	}

	@Override
	public ResDTO deleteContext(String str) {
		ResDTO resDTO = new ResDTO();
		EntityUtils.pkg(resDTO, ResCode.FAILURE_SERVICE);
		return resDTO;
	}

	@Override
	public ResDTO listMajors(Integer type) {
		ResDTO resDTO = new ResDTO();
		EntityUtils.pkg(resDTO, ResCode.FAILURE_SERVICE);
		return resDTO;
	}

	@Override
	public void getDataDownloadsNum() {
	}

	@Override
	public void add(String str) {
	}

	@Override
	public ResDTO listEbkData(Map<String, Object> map) {
		ResDTO resDTO = new ResDTO();
		EntityUtils.pkg(resDTO, ResCode.FAILURE_SERVICE);
		return resDTO;
	}

	@Override
	public ResDTO listContents(Map<String, Object> map) {
		ResDTO resDTO = new ResDTO();
		EntityUtils.pkg(resDTO, ResCode.FAILURE_SERVICE);
		return resDTO;
	}

	@Override
	public void getData(String str) {
	}

}
