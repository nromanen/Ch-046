package ua.cv.tim.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import ua.cv.tim.model.Village;
import ua.cv.tim.model.Village;
import ua.cv.tim.service.VillageService;
import ua.cv.tim.service.UserService;
import ua.cv.tim.service.VillageService;

import java.util.List;

/**
 * Created by Oleg on 08.01.2017.
 */

@RestController
public class VillagesRESTController {


    @Autowired
    VillageService villageService;



//    @RequestMapping(value = "/village/{id}/village",method = RequestMethod.GET)
//    public ResponseEntity<List<Village>> getUsersVillages(@PathVariable(name = "id") String id)
//    {
//        Village byIdWithVillages = villageService.getById(id);
//        List<Village> villages = byIdWithVillages.getVillages();
//        return new ResponseEntity<>(villages, HttpStatus.OK);
//    }

    @RequestMapping(value = "/village/{id}",method = RequestMethod.GET)
    public ResponseEntity<Village> getVillageById(@PathVariable(name = "id")String id){
        Village village=villageService.getById(id);
        if (village==null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(village,HttpStatus.OK);
    }

    @RequestMapping(value = "/village/",method = RequestMethod.POST)
    public ResponseEntity<Village> addVillage(@RequestBody Village village, UriComponentsBuilder builder){
        villageService.add(village);
        HttpHeaders headers=new HttpHeaders();
        headers.setLocation(builder.path("/village/{id}").buildAndExpand(village.getUuid()).toUri());
        return new ResponseEntity<>(headers,HttpStatus.CREATED);
    }

    @RequestMapping(value = "/village/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Village> updateVillage(@PathVariable(name = "id") String id, @RequestBody Village village){
        Village current_village=villageService.getById(id);
        if (current_village!=null) {
            current_village.setName(village.getName());
            current_village.setxCoord(village.getxCoord());
            current_village.setyCoord(village.getyCoord());
            current_village.setPopulation(village.getPopulation());
            current_village.setWall(village.getWall());
            current_village.setIsCapital(village.getIsCapital());
            current_village.setUuid(village.getUuid());
            current_village.setArmies(village.getArmies());
            current_village.setArmyRequests(village.getArmyRequests());

            return new ResponseEntity<>(current_village,HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/village/{id}",method = RequestMethod.DELETE)
    public ResponseEntity<Village> deleteVillage(@PathVariable(name = "id") String id){
        Village Village = villageService.getById(id);
        if (Village==null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        villageService.delete(Village);
        return new ResponseEntity<>(Village,HttpStatus.NO_CONTENT);
    }
}
