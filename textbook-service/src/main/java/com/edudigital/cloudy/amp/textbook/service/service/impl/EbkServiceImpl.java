package com.edudigital.cloudy.amp.textbook.service.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.edudigital.cloudy.amp.textbook.base.entity.ao.ChapterAO;
import com.edudigital.cloudy.amp.textbook.base.entity.ao.ChapterContextAO;
import com.edudigital.cloudy.amp.textbook.base.entity.ao.EbkAO;
import com.edudigital.cloudy.amp.textbook.base.entity.ao.QuestionAO;
import com.edudigital.cloudy.amp.textbook.base.entity.dto.AskDTO;
import com.edudigital.cloudy.amp.textbook.base.entity.dto.BookMajorDTO;
import com.edudigital.cloudy.amp.textbook.base.entity.dto.ContextDTO;
import com.edudigital.cloudy.amp.textbook.base.entity.dto.DiscussDTO;
import com.edudigital.cloudy.amp.textbook.base.entity.dto.EbkDTO;
import com.edudigital.cloudy.amp.textbook.base.entity.dto.EbkDataDTO;
import com.edudigital.cloudy.amp.textbook.base.entity.dto.GroupDTO;
import com.edudigital.cloudy.amp.textbook.base.entity.dto.MajorDTO;
import com.edudigital.cloudy.amp.textbook.base.entity.dto.PeriodDTO;
import com.edudigital.cloudy.amp.textbook.base.entity.dto.QuestionDTO;
import com.edudigital.cloudy.amp.textbook.base.entity.dto.TestDTO;
import com.edudigital.cloudy.amp.textbook.base.entity.dto.TextDTO;
import com.edudigital.cloudy.amp.textbook.base.entity.dto.TypeDTO;
import com.edudigital.cloudy.amp.textbook.service.dao.IEbkDAO;
import com.edudigital.cloudy.amp.textbook.service.service.IEbkService;
import com.edudigital.cloudy.amp.textbook.service.util.EntityUtils;
import com.edudigital.cloudy.amp.user.base.entity.dto.UserDTO;

@Service
public class EbkServiceImpl implements IEbkService {

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private IEbkDAO ebkDAO;

	@Override
	public List<EbkDTO> getEbkSaledNums(UserDTO user) {
		logger.info(Thread.currentThread().getStackTrace()[1].getMethodName());
		return ebkDAO.getEbkSaledNums(user);
	}

	@Override
	public int addEbk(EbkDTO ebkDTO) {
		logger.info(Thread.currentThread().getStackTrace()[1].getMethodName());
		return ebkDAO.addEbk(ebkDTO);
	}

	@Override
	public List<EbkDTO> listBooks(GroupDTO groupDTO, UserDTO userDTO, EbkDTO ebkDTO, Integer start, Integer size) {
		logger.info(Thread.currentThread().getStackTrace()[1].getMethodName());
		List<EbkDTO> boList = null;
		if (userDTO == null || 0 == userDTO.getType() % 2) {
			boList = ebkDAO.listBooks(groupDTO, userDTO, ebkDTO, start, size);
		} else {
			boList = ebkDAO.listBooksByAuthor(userDTO, ebkDTO, start, size);
		}
		return boList;
	}

	@Override
	public EbkDTO getUserBook(UserDTO userDTO, EbkDTO ebkDTO) {
		logger.info(Thread.currentThread().getStackTrace()[1].getMethodName());

		return ebkDAO.getUserBook(userDTO, ebkDTO);
	}

	@Override
	public int addUserBook(UserDTO userDTO, EbkDTO ebkDTO) {
		logger.info(Thread.currentThread().getStackTrace()[1].getMethodName());
		return ebkDAO.addUserBook(userDTO, ebkDTO);
	}

	@Override
	public List<EbkDataDTO> listEbkData(EbkDTO ebkDTO) {
		logger.info(Thread.currentThread().getStackTrace()[1].getMethodName());
		return ebkDAO.listEbkData(ebkDTO);
	}

	@Override
	public List<ChapterAO> listContents(EbkDTO ebkDTO) {
		logger.info(Thread.currentThread().getStackTrace()[1].getMethodName());
		return ebkDAO.listContents(ebkDTO);
	}

	@Override
	public List<TestDTO> listTest(EbkDTO ebkDTO) {
		logger.info(Thread.currentThread().getStackTrace()[1].getMethodName());
		return ebkDAO.listTest(ebkDTO);
	}

	@Override
	public List<TypeDTO> listTypes() {
		logger.info(Thread.currentThread().getStackTrace()[1].getMethodName());

		List<TypeDTO> bos = null;
		List<TypeDTO> dtos = ebkDAO.listTypes();

		try {
			bos = EntityUtils.exchange(dtos, TypeDTO.class.getName());
		} catch (Exception e) {
			logger.error(e.getMessage());
		}

		return bos;
	}

	@Override
	public List<ContextDTO> listContexts(EbkDTO ebkDTO) {
		logger.info(Thread.currentThread().getStackTrace()[1].getMethodName());
		return ebkDAO.listContexts(ebkDTO);
	}

	@Override
	public int addContext(ContextDTO contextDTO) {
		logger.info(Thread.currentThread().getStackTrace()[1].getMethodName());
		return ebkDAO.addContext(contextDTO);
	}

	@Override
	public int addAsks(List<AskDTO> askDTOs) {
		logger.info(Thread.currentThread().getStackTrace()[1].getMethodName());
		return ebkDAO.addAsks(askDTOs);
	}

	@Override
	public List<AskDTO> listAsks(AskDTO askDTO) {
		logger.info(Thread.currentThread().getStackTrace()[1].getMethodName());
		return ebkDAO.listAsks(askDTO);
	}

	@Override
	public int addQuestion(QuestionDTO questionDTO) {
		logger.info(Thread.currentThread().getStackTrace()[1].getMethodName());
		return ebkDAO.addQuestion(questionDTO);
	}

	@Override
	public List<QuestionDTO> listQuestions(QuestionDTO questionDTO) {
		logger.info(Thread.currentThread().getStackTrace()[1].getMethodName());
		return ebkDAO.listQuestions(questionDTO);
	}

	@Override
	public int addBkNum(EbkDTO ebkDTO) {
		logger.info(Thread.currentThread().getStackTrace()[1].getMethodName());
		return ebkDAO.addBkNum(ebkDTO);
	}

	@Override
	public int updateBk(EbkDTO ebkDTO) {
		logger.info(Thread.currentThread().getStackTrace()[1].getMethodName());
		return ebkDAO.updateBk(ebkDTO);
	}

	@Override
	public List<TextDTO> listTexts(TextDTO textDTO) {
		logger.info(Thread.currentThread().getStackTrace()[1].getMethodName());
		return ebkDAO.listTexts(textDTO);

	}

	@Transactional(readOnly = false)
	@Override
	public int addText(TextDTO textDTO, int bookCode) {
		logger.info(Thread.currentThread().getStackTrace()[1].getMethodName());
		ebkDAO.upLen(bookCode);
		return ebkDAO.addText(textDTO);
	}

	@Override
	public List<DiscussDTO> listDiscuss(DiscussDTO discussDTO) {
		logger.info(Thread.currentThread().getStackTrace()[1].getMethodName());
		return ebkDAO.listDiscuss(discussDTO);
	}

	@Override
	public int addDiscuss(List<DiscussDTO> discussDTOs) {
		logger.info(Thread.currentThread().getStackTrace()[1].getMethodName());
		return ebkDAO.addDiscuss(discussDTOs);
	}

	@Override
	public List<MajorDTO> listCourses(MajorDTO courseDTO) {
		logger.info(Thread.currentThread().getStackTrace()[1].getMethodName());
		return ebkDAO.listCourses(courseDTO);
	}

	@Override
	public List<PeriodDTO> listPeriod() {
		logger.info(Thread.currentThread().getStackTrace()[1].getMethodName());
		return ebkDAO.listPeriod();
	}

	@Override
	public List<ChapterAO> listContents2(EbkDTO ebkDTO) {
		logger.info(Thread.currentThread().getStackTrace()[1].getMethodName());
		return ebkDAO.listContents2(ebkDTO);
	}

	@Override
	public List<EbkDataDTO> listData(EbkDTO ebkDTO) {
		logger.info(Thread.currentThread().getStackTrace()[1].getMethodName());
		return ebkDAO.listData(ebkDTO);
	}

	@Override
	public int delteContext(List<ChapterContextAO> chapterContextAOs) {
		logger.info(Thread.currentThread().getStackTrace()[1].getMethodName());
		return ebkDAO.deleteContext(chapterContextAOs);
	}

	@Override
	public List<ChapterContextAO> listChapterContexts(ContextDTO contextDTO, Integer start, Integer size) {
		logger.info(Thread.currentThread().getStackTrace()[1].getMethodName());
		return ebkDAO.listChapterContexts(contextDTO, start, size);
	}

	@Override
	public int countBooksByAuthor(UserDTO userDTO, EbkDTO ebkDTO) {
		logger.info(Thread.currentThread().getStackTrace()[1].getMethodName());
		return ebkDAO.countBooksByAuthor(userDTO, ebkDTO);
	}

	@Override
	public int countChapterContexts(ContextDTO contextDTO) {
		logger.info(Thread.currentThread().getStackTrace()[1].getMethodName());
		return ebkDAO.countChapterContexts(contextDTO);
	}

	@Override
	public List<EbkDTO> likeListBooks(BookMajorDTO bookMajorDTO) {
		logger.info(Thread.currentThread().getStackTrace()[1].getMethodName());
		return ebkDAO.likeListBooks(bookMajorDTO);
	}

	@Override
	public List<MajorDTO> listMajors(int type) {
		logger.info(Thread.currentThread().getStackTrace()[1].getMethodName());
		return ebkDAO.listMajors(type);
	}

	@Override
	public List<EbkAO> listWorkBooks(GroupDTO groupDTO, UserDTO userDTO, EbkDTO ebkDTO) {

		return ebkDAO.listWorkBooks(groupDTO, userDTO, ebkDTO);
	}

	@Override
	public List<QuestionAO> listEbkDataQuestion(EbkDTO ebkDTO, QuestionDTO questionDTO) {

		return ebkDAO.listEbkDataQuestion(ebkDTO, questionDTO);
	}

}
