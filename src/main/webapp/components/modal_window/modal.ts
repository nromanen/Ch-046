import {Component} from "@angular/core";
/**
 * Created by rmochetc on 12.01.2017.
 */


@Component({
    selector: 'app-modal',
    //templateUrl: 'components/modal_window/modal.html',
    template: `

<!-- Modal Structure -->
<div *ngIf="ErrorMessageIsVisible" style="z-index: 999;display: block;opacity: 0.5;position: fixed; top: -100px; left: 0; bottom: 0; right: 0; height: 125%; width: 100%; background: #000;">
    <div style="
    z-index: 1003;
    color: black;
    display: block;
    opacity: 1;
    transform: scaleX(1);
    top: 10%;
    position: fixed;
    left: 0;
    right: 0;
    background-color: #fafafa;
    padding: 0;
    max-height: 70%;
    width: 55%;
    margin: auto;
    overflow-y: auto;
    border-radius: 2px;
    box-shadow: 0 8px 10px 1px rgba(0,0,0,0.14), 0 3px 14px 2px rgba(0,0,0,0.12), 0 5px 5px -3px rgba(0,0,0,0.3);">
 <div style="padding: 24px; opacity: 1;
    box-sizing: inherit;
    display: block;">
        <h4 style="    margin-top: 0;
    font-size: 2.28rem;
    line-height: 110%;
    margin: 1.14rem 0 0.912rem 0;
    font-weight: 400;
    box-sizing: inherit;
    display: block;">Confinm this action</h4>
        <p>{{ErrorMsg}}</p>
    </div>
    <div class="modal-footer">
        <a href="#!" (click)="hideErrorMsg()" class=" modal-action modal-close waves-effect waves-green btn-flat" style="float: right; margin: 6px 0; opacity: 1;">Agree</a>
        <a href="#!" (click)="hideErrorMsg()" class=" modal-action modal-close waves-effect waves-green btn-flat" style="float: right; margin: 6px 0; opacity: 1;">Disagree</a>
    </div>
    </div>
</div>


`
})
export class ErrorMessage
{
    private ErrorMsg: string;
    public ErrorMessageIsVisible: boolean;

    showErrorMessage(msg: string)
    {
        console.log("show message");
        console.log(msg);
        this.ErrorMsg = msg;
        this.ErrorMessageIsVisible = true;
    }

    hideErrorMsg()
    {
        this.ErrorMessageIsVisible = false;
    }
}
