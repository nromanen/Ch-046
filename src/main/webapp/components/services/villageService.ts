import {Injectable} from "@angular/core";
import {Village} from "../village/village";
import {Http, Headers} from "@angular/http";
import {Observable} from "rxjs";
import {error} from "util";
/**
 * Created by okunetc on 20.01.2017.
 */
@Injectable()
export class VillageService{
    villageURL="village/";
    villages:Array<Village>;
    update(village:Village){

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
        // return this.http.put(this.villageURL+village.uuid,JSON.stringify(village), {headers: headers})
        //     .map(res=>{
        //         console.log(res);
        //     },
        //     error=>{
        //         console.log('error');
        //     }).subscribe(res=>{
        //         console.log(res);
        //     });

        this.http.put(this.villageURL + village.uuid, JSON.stringify(village), {
            headers: headers
        }).subscribe(
            response => {
                console.log(response);
                let newVillage=response.json();
                console.log(newVillage);
                 this.villages[this.villages.indexOf(village)] = newVillage;
            },
            error => console.log(error)
        );
    }

    add(village:Village){
        const body = JSON.stringify(village);

        let headers = new Headers();
        headers.append('Content-Type', 'application/json');
        this.http.post(this.villageURL, body, {
            headers: headers
        }).map(res => res.json())
            .subscribe(
                response => {
                    console.log('Alliance created successful');
                    this.villages.push(response);
                },
                error => console.log(error)
            );
    }
    constructor(private http:Http){

    }
}