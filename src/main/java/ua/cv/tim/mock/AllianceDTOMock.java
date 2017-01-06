package ua.cv.tim.mock;

import ua.cv.tim.dto.AllianceDTO;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rmochetc on 06.01.2017.
 */
public class AllianceDTOMock {

    private static List<AllianceDTO> alliances = new ArrayList<>();


    static {

        alliances.add(new AllianceDTO("Avalon", "todor", "testEmail1@test.com"));
        alliances.add(new AllianceDTO("Valhala", "parser","testEmail2@test.com"));
        alliances.add(new AllianceDTO("IT_Heroes", "sprig","testEmai32@test.com"));
        alliances.add(new AllianceDTO("Arven", "angular","testEmail4@test.com"));

    }

    public static void addAlliance(AllianceDTO allianceDTO){

        alliances.add(allianceDTO);

    }

    public static List<AllianceDTO> getAlliances() {
        return alliances;
    }

    public static void updateAlliances(AllianceDTO allianceDTO){

        alliances.set(getIdByAllianceId(allianceDTO.getUuid()),allianceDTO);
    }

    public static void delete(String id){
        alliances.remove(getIdByAllianceId(id));
    }

    private static int getIdByAllianceId(String id){
        for (int i = 0; i < alliances.size(); i++){
            if(alliances.get(i).getUuid().equals(id)){
                return i;
            }
        }
        return -1;
    }

    public static AllianceDTO getById(String  id){
        for (AllianceDTO alliance: alliances){
            if(alliance.getUuid().equals(id)){
                return alliance;
            }
        }
        return null;
    }

}
