package com.edudigital.cloudy.amp.textbook.service.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import com.edudigital.cloudy.amp.textbook.base.entity.ao.ChapterAO;
import com.edudigital.cloudy.amp.textbook.base.entity.ao.ChapterContextAO;
import com.edudigital.cloudy.amp.textbook.base.entity.ao.EbkAO;
import com.edudigital.cloudy.amp.textbook.base.entity.ao.QuestionAO;
import com.edudigital.cloudy.amp.textbook.base.entity.dto.BookMajorDTO;
import com.edudigital.cloudy.amp.textbook.base.entity.dto.EbkDTO;
import com.edudigital.cloudy.amp.textbook.base.entity.dto.GroupDTO;
import com.edudigital.cloudy.amp.textbook.base.entity.dto.QuestionDTO;
import com.edudigital.cloudy.amp.textbook.base.entity.dto.TypeDTO;
import com.edudigital.cloudy.amp.textbook.service.entity.po.AskPO;
import com.edudigital.cloudy.amp.textbook.service.entity.po.ContextPO;
import com.edudigital.cloudy.amp.textbook.service.entity.po.DiscussPO;
import com.edudigital.cloudy.amp.textbook.service.entity.po.EbkDataPO;
import com.edudigital.cloudy.amp.textbook.service.entity.po.EbkPO;
import com.edudigital.cloudy.amp.textbook.service.entity.po.GroupPO;
import com.edudigital.cloudy.amp.textbook.service.entity.po.MajorPO;
import com.edudigital.cloudy.amp.textbook.service.entity.po.PeriodPO;
import com.edudigital.cloudy.amp.textbook.service.entity.po.QuestionPO;
import com.edudigital.cloudy.amp.textbook.service.entity.po.TestPO;
import com.edudigital.cloudy.amp.textbook.service.entity.po.TextPO;
import com.edudigital.cloudy.amp.textbook.service.entity.po.UserBkPO;
import com.edudigital.cloudy.amp.textbook.service.entity.po.UserPO;
import com.edudigital.cloudy.amp.user.base.entity.dto.UserDTO;

/**
 * 
 * @author Tempo
 * @date 2017年7月26日 下午5:02:17
 *
 */
@Component
public interface EbkMapper {
	/**
	 * 
	 * @return
	 */
	public List<EbkPO> getEbkSaledNums(@Param("user") UserPO user);

	/**
	 * 
	 * @param user
	 * @return
	 */
	public int addEbk(@Param("b") EbkPO ebkPO);

	/**
	 * 
	 * @param groupPO
	 * @param userPO
	 * @param ebkPO
	 * @param start
	 * @param size
	 * @return
	 */
	public List<EbkPO> listBooks(@Param("g") GroupPO groupPO, @Param("u") UserPO userPO, @Param("e") EbkPO ebkPO,
			@Param("start") Integer start, @Param("size") Integer size);

	/**
	 * 
	 * @param userPO
	 * @return
	 */
	public List<EbkPO> listBooksByAuthor(@Param("u") UserPO userPO, @Param("e") EbkPO ebkPO,
			@Param("start") Integer start, @Param("size") Integer size);

	/**
	 * 
	 * @param userPO
	 * @param ebkPO
	 * @return
	 */
	public EbkPO getUserBook(@Param("u") UserPO userPO, @Param("b") EbkPO ebkPO);

	/**
	 * 
	 * @param userBkPO
	 * @return
	 */
	public int addUserBook(@Param("ub") UserBkPO userBkPO);

	/**
	 * 
	 * @param ebkPO
	 * @return
	 */
	public List<EbkDataPO> listEbkData(@Param("d") EbkPO ebkPO);

	/**
	 * 
	 * @param ebkPO
	 * @return
	 */
	public List<ChapterAO> listContents(@Param("b") EbkPO ebkPO);

	/**
	 * 
	 * @param ebkPO
	 * @return
	 */
	public List<ChapterAO> listContents2(@Param("b") EbkPO ebkPO);

	/**
	 * 
	 * @param ebkPO
	 * @return
	 */
	public List<TestPO> listTest(@Param("b") EbkPO ebkPO);

	/**
	 * 
	 * @return
	 */
	public List<TypeDTO> listTypes();

	/**
	 * 
	 * @param ebkPO
	 * @return
	 */
	public List<ContextPO> listContexts(@Param("b") EbkPO ebkPO);

	/**
	 * 
	 * @param contextPO
	 * @return
	 */
	public int addContext(@Param("c") ContextPO contextPO);

	/**
	 * 
	 * @param askPOs
	 * @return
	 */
	public int addAsks(@Param("q") QuestionPO questionPO);

	/**
	 * 
	 * @param askPO
	 * @return
	 */
	public List<AskPO> listAsks(@Param("a") AskPO askPO);

	/**
	 * 
	 * @param questionPO
	 * @return
	 */
	public int addQuestion(@Param("q") QuestionPO questionPO);

	/**
	 * 
	 * @param questionPO
	 * @return
	 */
	public List<QuestionPO> listQuestions(@Param("q") QuestionPO questionPO);

	/**
	 * 
	 * @param ebkPO
	 * @return
	 */
	public int addBkNum(@Param("b") EbkPO ebkPO);

	/**
	 * 
	 * @param ebkPO
	 * @return
	 */
	public int updateBk(@Param("b") EbkPO ebkPO);

	/**
	 * 
	 * @param textPO
	 * @return
	 */
	public List<TextPO> listTexts(TextPO textPO);

	/**
	 * 
	 * @param textPO
	 * @return
	 */
	public int addText(@Param("t") TextPO textPO);

	/**
	 * 
	 * @param discussPO
	 * @return
	 */
	public List<DiscussPO> listDiscuss(DiscussPO discussPO);

	/**
	 * 
	 * @param discussPOs
	 * @return
	 */
	public int addDiscuss(@Param("dis") List<DiscussPO> discussPOs);

	/**
	 * 
	 * @param coursePO
	 * @return
	 */
	public List<MajorPO> listCourses(@Param("c") MajorPO coursePO);

	/**
	 * 
	 * @return
	 */
	public List<PeriodPO> listPeriod();

	/**
	 * 
	 * @param ebkBO
	 * @return
	 */
	public List<EbkDataPO> listData(EbkPO ebkPO);

	/****
	 * length长度添加
	 * 
	 * @param bookCode
	 * @return
	 */
	public int upLen(@Param("bc") int bookCode);

	/**
	 * 
	 * @param chapterContextAOs
	 * @return
	 */
	public int deleteContext(@Param("c") List<ChapterContextAO> chapterContextAOs);

	/**
	 * 
	 * @param contextPO
	 * @param start
	 * @param size
	 * @return
	 */
	public List<ChapterContextAO> listChapterContexts(@Param("c") ContextPO contextPO, @Param("start") Integer start,
			@Param("size") Integer size);

	/**
	 * 查询书籍个数
	 * 
	 * @param userPO
	 * @param ebkPO
	 * @return
	 */
	public int countBooksByAuthor(@Param("u") UserPO userPO, @Param("b") EbkPO ebkPO);

	/**
	 * 
	 * @param contextPO
	 * @return
	 */
	public int countChapterContexts(@Param("c") ContextPO contextPO);

	/**
	 * 查询书籍
	 * 
	 * @param bookMajorDTO
	 * @return
	 */
	public List<EbkPO> likeListBooks(@Param("b") BookMajorDTO bookMajorDTO);

	/**
	 * 查询专业
	 * 
	 * @return
	 */
	public List<MajorPO> listMajors(int type);

	/**
	 * 
	 * @param ebkPO
	 * @return
	 */

	public List<EbkAO> listWorkBooks(@Param("g") GroupDTO groupDTO, @Param("u") UserDTO userDTO,
			@Param("e") EbkDTO ebkDTO);

	/**
	 * 移动端习题查询
	 * 
	 * @param ebkDTO
	 * @param questionDTO
	 * @return
	 */

	public List<QuestionAO> listEbkDataQuestion(@Param("b") EbkDTO ebkDTO, @Param("q") QuestionDTO questionDTO);
}
