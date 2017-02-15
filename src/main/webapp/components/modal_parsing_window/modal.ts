import {Component, Input, Output, EventEmitter, HostListener} from "@angular/core";
import {Credentials} from "./credentials";

@Component({
    selector: 'app-modal-pars',
    templateUrl: 'components/modal_parsing_window/modal.html',
    styleUrls: ['components/modal_parsing_window/modal.css']
})
export class ConfirmParsingComponent
{
    @Input() confirmMsg: string;
    @Output() notify: EventEmitter<Credentials> = new EventEmitter<Credentials>();
    login: string;
    pass: string;

    @HostListener('window:keydown', ['$event'])
    keyboardInput(event: any) {
        if (event.key === "Escape"){
            this.onDecline();
        }
    }

    onConfirm()
    {
        let cred: Credentials = new Credentials(this.login, this.pass, true);
        this.notify.emit(cred);
    }

    onDecline()
    {
        let cred: Credentials = new Credentials(this.login, this.pass, false);
        this.notify.emit(cred);
    }
}
