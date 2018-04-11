package com.edudigital.cloudy.amp.textbook.custom.service;

import java.util.List;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.edudigital.cloudy.amp.textbook.base.entity.dto.GameDTO;
import com.edudigital.cloudy.amp.textbook.base.entity.dto.GameWjxInfoDTO;
import com.edudigital.cloudy.amp.textbook.base.entity.dto.GameWjxScoreDTO;
import com.edudigital.cloudy.amp.textbook.base.entity.dto.PageGameWjxDTO;
import com.edudigital.cloudy.amp.textbook.custom.service.fallback.GameServiceFallback;

@FeignClient(name = "textbook-service", fallback = GameServiceFallback.class)
public interface IGameService {

	@RequestMapping(value = "/textbook-service/game/listGames", method = { RequestMethod.GET })
	public List<GameDTO> listGames(@RequestParam("id") Integer id);

	@RequestMapping(value = "/textbook-service/game/listWjxScore", method = { RequestMethod.GET })
	public List<GameWjxScoreDTO> listWjxScore(@RequestParam("userId") Integer userId,
			@RequestParam("gameId") Integer gameId);

	@RequestMapping(value = "/textbook-service/game/addUserRelGame", method = { RequestMethod.POST })
	public int addUserRelGame(@RequestBody GameDTO gameDTO);

	@RequestMapping(value = "/textbook-service/game/getInitInfo", method = { RequestMethod.GET })
	public GameWjxInfoDTO getInitInfo(@RequestParam("userId") Integer userId,
			@RequestParam("gameGroupNo") Integer gameGroupNo);

	@RequestMapping(value = "/textbook-service/game/listRank", method = { RequestMethod.GET })
	public PageGameWjxDTO listRank(@RequestParam("gameGroupNo") Integer gameGroupNo, @RequestParam("flag") Integer flag,
			@RequestParam("page") Integer page, @RequestParam("size") Integer size);
	
	@RequestMapping(value = "/textbook-service/game/myRank", method = { RequestMethod.GET })
	public GameWjxInfoDTO myRank(@RequestParam("gameGroupNo") Integer gameGroupNo, @RequestParam("flag") Integer flag,
			@RequestParam("userId") Integer userId);
}
