import {Component, Input, Output, EventEmitter} from "@angular/core";
import {Alliance} from "../alliance/alliance";
/**
 * Created by rmochetc on 12.01.2017.
 */


@Component({
    selector: 'app-modal',
    templateUrl: 'components/modal_window/modal.html',
    styleUrls: ['components/modal_window/modal.css']
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
