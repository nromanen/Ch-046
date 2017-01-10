package ua.cv.tim.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ua.cv.tim.dao.PlayerDao;
import ua.cv.tim.model.Player;
import ua.cv.tim.model.User;

@Service("playerService")
@Transactional
public class PlayerService {

	@Autowired
	private PlayerDao playerDao;
	
	public void add(){
		Player player1 = new Player();
		
		playerDao.add(player1);
	}
}
