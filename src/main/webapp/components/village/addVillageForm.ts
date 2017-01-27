/**
 * Created by okunetc on 23.01.2017.
 */
import {Component, OnInit, ViewChild, AfterViewChecked, Input, Output, EventEmitter} from "@angular/core";
import {Village} from "../village/village";
import {Army} from "../army/army";
import {VillageService} from "../services/villageService";
@Component({
    selector: "add-vill-form",
    templateUrl: "components/village/addVillageForm.html"
})
export class AddVillageForm implements OnInit,AfterViewChecked {
    ngOnInit(): void {

    }

    showAddArmyButton: boolean;
    village: Village;
    @Input() player;
    @Output() wasSubmitted: EventEmitter<any>;

    constructor(private villageService: VillageService) {
        this.village = new Village;
        this.village.armies = [];
        this.village.isCapital = false;
        this.wasSubmitted = new EventEmitter();
        this.showAddArmyButton = true;
    }

    ngAfterViewChecked() {

    }


    addArmies() {
        this.village.armies.push(new Army);
        console.log(this.village);
        this.showAddArmyButton = false;
    }

    onSubmit(village: Village) {
        this.player.villages.push(village);
        // this.villageService.add(village);
        this.village = new Village;
        this.village.armies = [];
        this.wasSubmitted.emit(false);
    }

    showAddArmy(ifShow: boolean) {
        this.showAddArmyButton = ifShow;
    }

}