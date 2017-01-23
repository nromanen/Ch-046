import {Component, OnInit} from "@angular/core";
import {Input} from "@angular/core/src/metadata/directives";
import {Player} from "../player/player";
import {CurrVillageArmiesService} from "../services/newVillageArmiesService";
import {Village} from "./village";
/**
 * Created by Oleg on 17.01.2017.
 */
@Component({
    selector:'edit-village',
    template:`<player-head></player-head>
<div>hello</div>
<div class="container">
    <div class="row">
        <form class="col s12">
            <div class="row">
                <div class="input-field col s6 offset-s3">
                    <input name="name"  [(ngModel)]="village.name" value="{{village.name}}" id="name" type="text" class="validate" #newName>
                    <label class="active" for="name">Name</label>
                </div>
            </div>

            <!--<div class="row">-->
                <!--<div class="input-field col s6 offset-s3">-->
                    <!--<input value="{{village.xCoord}}" id="xCoord" type="text" class="validate" #newX>-->
                    <!--<label class="active" for="xCoord">X coordinate</label>-->
                <!--</div>-->
            <!--</div>-->

            <!--<div class="row">-->
                <!--<div class="input-field col s6 offset-s3">-->
                    <!--<input value="{{village.yCoord}}" id="yCoord" type="text" class="validate" #newY>-->
                    <!--<label class="active" for="yCoord">Y coordinate</label>-->
                <!--</div>-->
            <!--</div>-->

            <!--<div class="row">-->
                <!--<div class="input-field col s6 offset-s3">-->
                    <!--<input value="{{village.population}}" id="population" type="text" class="validate" #newPopulation>-->
                    <!--<label class="active" for="population">Population</label>-->
                <!--</div>-->
            <!--</div>-->


            <!--<div class="row">-->
                <!--<div class="input-field col s6 offset-s3">-->
                    <!--<input value="{{village.wall}}" id="isCapital" type="text" class="validate" #newWall>-->
                    <!--<label class="active" for="isCapital">Wall</label>-->
                <!--</div>-->
            <!--</div>-->

            <!---->

            <!--<div class="row">-->
                <!--<div class="input-field col s6 offset-s3">-->
                    <!--<div class="switch">-->
                        <!--<label>-->
                            <!--No-->
                            <!--<input type="checkbox" name="isCapital" required (ngModel)="village.isCapital()">-->
                            <!--<span class="lever"></span>-->
                            <!--Yes-->
                        <!--</label>-->
                    <!--</div>-->
                <!--</div>-->
            <!--</div>-->

            <!--<div class="row">-->
                <!--<div class="col offset-s5">-->
                    <!--<button class="btn waves-effect waves-light" style="margin-top: 27px">Save</button>-->
                <!--</div>-->
            <!--</div>-->
        </form>
    </div>
</div>
`
})
export class EditVillageComponent implements OnInit{
    ngOnInit(): void {
        console.log(this.village.isCapital);
    }
 village:Village;
    constructor(private currVillageService:CurrVillageArmiesService)
    {
        this.village=currVillageService.village;
    }
}