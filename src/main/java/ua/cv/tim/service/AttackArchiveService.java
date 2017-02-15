package ua.cv.tim.service;

import ua.cv.tim.dto.AttackDTO;
import ua.cv.tim.model.AttackArchive;

import java.io.IOException;
import java.util.List;

/**
 * Created by rmochetc on 13.02.2017.
 */
public interface AttackArchiveService {
    void add(AttackArchive attackArchive);
    List<AttackArchive> getAll();
    List<AttackDTO> getById(String id) throws IOException;
}
