
import {Alliance} from "./alliance";
import {Component, OnInit} from "@angular/core";
import {AllianceService} from "../services/alliance-service";
import {PagerService} from "../services/pager.service";
import {Http, Headers} from "@angular/http";

@Component({
    selector: 'my-alliance',
    templateUrl: 'components/alliance/alliance.html',
    styleUrls: ['components/alliance/alliance.css']
})

export class AllianceComponent implements OnInit{

    alliances: Array<Alliance> ;
    errorMessage: string;


    private allAlliance: any[];

    pager: any = {};

    pagedAlliance: any[];

    selectedAlliance: Alliance = null;
    deletedAlliance: Alliance = null;

    confirmMsg: string;

    url = 'admin/allianceDTO/';

    constructor(private _http: Http, private _allianceService: AllianceService, private pagerService: PagerService){
    }

    ngOnInit() {
        this.getAlliances();
        //this.setPage(1);
    }

    onNotify(alliance : Alliance){
        this.selectedAlliance = alliance;
    }

    onNotifyDelete(confitmation : boolean){
        if (confitmation){
            this._allianceService.deleteAlliance(this.deletedAlliance);
        }
        this.deletedAlliance = null;
        this.setPage(this.pager.currentPage);
    }

    editAlliance(al: Alliance){
        this.selectedAlliance = al;
    }

    deleteAlliance(al: Alliance){
        console.log("ontest delete");
        this.confirmMsg = "Are you sure you want to delete alliance " + al.name + "?";
        this.deletedAlliance = al;

    }

    cancelEditing(){
        this.selectedAlliance = null;
    }

    setPage(page: number) {
        if (page < 1 || page > this.pager.totalPages) {
            return;
        }

        // get pager object from service
        this.pager = this.pagerService.getPager(this.alliances.length, page);

        // get current page of items
        this.pagedAlliance = this.allAlliance.slice(this.pager.startIndex, this.pager.endIndex + 1);
    }



    getAlliances() {
        this._allianceService.getAlliances()
            .subscribe(
                alliances => this.alliances = alliances,
                error =>  this.errorMessage = <any>error
            );
        console.log("subscribe");
        console.log(this.alliances);
    }


}