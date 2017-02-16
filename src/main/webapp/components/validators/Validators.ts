import {AbstractControl} from "@angular/forms";
/**
 * Created by okunetc on 15.02.2017.
 */
export function moreThanZeroValidator() {
    return (control: AbstractControl): {[key: string]: any} => {
        const coord = control.value;
        if (coord < 0)
            return {'moreThanZero': {coord}};
        return null;
    };
}