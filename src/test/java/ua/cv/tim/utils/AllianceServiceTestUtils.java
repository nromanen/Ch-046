package ua.cv.tim.utils;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.transaction.annotation.Transactional;
import ua.cv.tim.configuration.WebConfigurationTest;
import ua.cv.tim.model.Alliance;

import java.util.List;

/**
 * Created by mmaksymtc on 26.01.2017.
 */
@Service("allianceServiceTestUtils")
@Transactional
@ContextConfiguration(classes = {WebConfigurationTest.class})
public class AllianceServiceTestUtils {

    @Autowired
    private SessionFactory session;

    public Alliance getAllianceByName(String allianceName){
        Query  query = session.getCurrentSession().createQuery("select a from Alliance a where a.name = :name");
        query.setParameter("name", allianceName);
        Alliance alliance = (Alliance) query.uniqueResult();
        return alliance;
    }

    public List<Alliance> getAllAlliances() {
        Query query = session.getCurrentSession().createQuery("select a FROM Alliance a order by a.name");
        List<Alliance> alliances = (List<Alliance>) query.list();
        return alliances;
    }
    public String getIdByName(String name) {
        Query query = session.getCurrentSession().createQuery("select a.uuid FROM Alliance a where name=:name");
        query.setParameter("name", name);
        String uuid = (String) query.uniqueResult();
        return uuid;
    }
}
