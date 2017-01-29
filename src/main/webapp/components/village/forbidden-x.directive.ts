/**
 * Created by okunetc on 28.01.2017.
 */
import { Directive, Input, OnChanges, SimpleChanges } from '@angular/core';
import { AbstractControl, NG_VALIDATORS, Validator, ValidatorFn, Validators } from '@angular/forms';

/** A hero's name can't match the given regular expression */
export function forbiddenXValidator() {
    return (control: AbstractControl): {[key: string]: any} => {
        const coord = control.value;
        if (coord<-400 || coord>400)
            return {'forbiddenCoordinate': {coord}};
        return null;
    };
}

// @Directive({
    // selector: '[forbiddenName]',
    // providers: [{provide: NG_VALIDATORS, useExisting: ForbiddenValidatorDirective, multi: true}]
// })

// export class ForbiddenValidatorDirective implements Validator, OnChanges {
//     @Input() forbiddenName: string;
//     private valFn = Validators.nullValidator;
//
//     ngOnChanges(changes: SimpleChanges): void {
//         const change = changes['forbiddenName'];
//         if (change) {
//             const val: string | RegExp = change.currentValue;
//             const re = val instanceof RegExp ? val : new RegExp(val, 'i');
//             this.valFn = forbiddenXValidator(re);
//         } else {
//             this.valFn = Validators.nullValidator;
//         }
//     }
//
//     validate(control: AbstractControl): {[key: string]: any} {
//         return this.valFn(control);
//     }
// }
