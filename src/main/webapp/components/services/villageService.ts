import {Injectable} from "@angular/core";
import {Village} from "../village/village";
import {Http, Headers} from "@angular/http";
import {Observable} from "rxjs";
/**
 * Created by okunetc on 20.01.2017.
 */
@Injectable()
export class VillageService{

    villageURL="village/";
    update(village:Village){
        let a=JSON.stringify(village);
        // console.log(a);
        // console.log(village.name);
        let villageBefore=new Village();
        villageBefore.name=village.name;
        villageBefore.uuid=village.uuid;
        villageBefore.armies=village.armies;
        villageBefore.isCapital=village.isCapital;
        villageBefore.population=village.population;
        villageBefore.xCoord=village.xCoord;
        villageBefore.yCoord=village.yCoord;
        villageBefore.wall=village.wall;
        console.log('url is:');
        console.log(this.villageURL);
        console.log(village);
        alert(JSON.stringify(village));

        let headers = new Headers();
        headers.append('Content-Type', 'application/json');
        return this.http.put(this.villageURL+village.uuid,JSON.stringify(village), {headers: headers})
            .map(res=>{
                console.log(res);
            }).subscribe(res=>{
                console.log(res);
            });

        // this.http.put(this.villageURL + village.uuid, a, {
        //     headers: headers
        // }).subscribe(
        //     response => {
        //         console.log(response);
        //         // this.alliances[this.alliances.indexOf(alliance)] = newAlliance;
        //     },
        //     error => console.log(error)
        // );
    }

    constructor(private http:Http){

    }
}