import {Component, Input, Output, EventEmitter} from "@angular/core";
import {Alliance} from "../alliance/alliance";
/**
 * Created by rmochetc on 12.01.2017.
 */


@Component({
    selector: 'app-modal',
    //templateUrl: 'components/modal_window/modal.html',
    template: `
    <div id="dialogoverlay"></div>
<div id="dialogbox">
    <div>
        <div id="dialogboxhead">Confirm</div>
        <div id="dialogboxbody">{{confirmMsg}}</div>
        <div id="dialogboxfoot"><button class='btn btn-danger' (click) = "onConfirm()">Confirm</button>&nbsp;&nbsp;&nbsp;&nbsp;<button class='btn btn-success' (click) = "onCancel()">Cancel</button></div>
    </div>
</div>

`,
    styles: [`#dialogoverlay{
    display: block;
    opacity: .8;
    position: fixed;
    top: 0px;
    left: 0px;
    background: black;
    width: 100%;
    height: 100%;
    z-index: 10;
}
#dialogbox{
    display: block;
    position: absolute;
    background: #fff;
    border-radius:7px;
    width:550px;
    z-index: 10;
     top: 20%;
  left: 35%;
}
#dialogbox > div{ background: #d4e1d9; margin:8px; }
#dialogbox > div > #dialogboxhead{ background: #fff; font-size:24px; padding:10px; color: #000000;  border-bottom: 1px solid lightgray;}
#dialogbox > div > #dialogboxbody{ background: #fff; font-size:19px; padding:20px; color: #000000; border-bottom: 1px solid lightgray;}
#dialogbox > div > #dialogboxfoot{ background: #fff; padding:10px; text-align:right; }`
]
})
export class ConfirmComponent
{
    @Input() confirmMsg: string;
    @Output() notify: EventEmitter<boolean> = new EventEmitter<boolean>();
    // private ErrorMsg: string;
    // public ErrorMessageIsVisible: boolean;

    showErrorMessage()
    {
        console.log("show message");
        // this.ErrorMsg = "TEST";
        // this.ErrorMessageIsVisible = true;
        // console.log(this.ErrorMessageIsVisible);
    }

    onConfirm()
    {
        this.notify.emit(true);
    }

    onCancel()
    {
        this.notify.emit(false);
    }
}
