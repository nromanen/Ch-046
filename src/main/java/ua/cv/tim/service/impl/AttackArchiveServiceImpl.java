package ua.cv.tim.service.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.cv.tim.dao.AttackArchiveDao;
import ua.cv.tim.dto.AttackDTO;
import ua.cv.tim.dto.AttackJson;
import ua.cv.tim.model.AttackArchive;
import ua.cv.tim.model.AuthorizedUser;
import ua.cv.tim.service.AttackArchiveService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by rmochetc on 13.02.2017.
 */

@Service (value = "attackArchiveService")
@Transactional
public class AttackArchiveServiceImpl implements AttackArchiveService {

    @Autowired
    private AttackArchiveDao attackArchiveDao;

    @Override
    public void add(AttackArchive attackArchive) {
        attackArchiveDao.add(attackArchive);
    }

    @Override
    public List<AttackArchive> getAll() {
        return attackArchiveDao.getAll();
    }

    @Override
    public List<AttackDTO> getById(String id) throws IOException {

        AuthorizedUser principal = (AuthorizedUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        ObjectMapper objectMapper = new ObjectMapper();
        List<AttackJson> attackJsons = objectMapper.readValue(attackArchiveDao.getById(id).getArchiveData(), new TypeReference<List<AttackJson>>() {});
        List<AttackDTO> archiveAttacks = getAttackArchive(attackJsons, principal.getAlliance().getName());
        return archiveAttacks;
    }

    private List<AttackDTO> getAttackArchive(List<AttackJson> attackJsons, String allianceName){
        List<AttackDTO> archiveAttacks = new ArrayList<>();
        for (AttackJson attack : attackJsons) {
            if (attack.getAllianceName().equals(allianceName)) {
                AttackDTO attackDTO = new AttackDTO();
                attackDTO.setEnemy(attack.getEnemy());
                attackDTO.setAttackTime("" + attack.getAttackTime());
                attackDTO.setPlayerName(attack.getPlayerName());
                attackDTO.setVillageName(attack.getVillageName());
                archiveAttacks.add(attackDTO);
            }
        }
        return archiveAttacks;
    }
}
