import {Component, Input, Output, EventEmitter, HostListener} from "@angular/core";

@Component({
    selector: 'app-modal',
    templateUrl: 'components/modal_window/modal.html',
    styleUrls: ['components/modal_window/modal.css']
})
export class ConfirmComponent
{
    @Input() confirmMsg: string;
    @Output() notify: EventEmitter<boolean> = new EventEmitter<boolean>();

    @HostListener('window:keydown', ['$event'])
    keyboardInput(event: any) {
        if (event.key === "Escape"){
            this.onDecline();
        }
    }

    onConfirm()
    {
        this.notify.emit(true);
    }

    onDecline()
    {
        this.notify.emit(false);
    }
}
