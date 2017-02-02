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
