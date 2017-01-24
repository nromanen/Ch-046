/**
 * Created by okunetc on 23.01.2017.
 */
import {Component, OnInit, ViewChild, AfterViewChecked} from "@angular/core";
import {Village} from "../village/village";
import {NgForm} from "@angular/forms";
import {Army} from "../army/army";
import {UnitType} from "../UnitType/unitType";
import {VillageService} from "../services/villageService";
@Component({
    selector:"add-vill-form",
    template:`
    <!---->
    <!--<form class="col s12">-->
       <!--<div class="input-field col s6 offset-s3">-->
          <!--<input id="last_name" type="text" class="validate">-->
          <!--<label for="last_name">Last Name</label>-->
        <!--</div>-->

    <!--</form>-->
 
      <form #heroForm="ngForm" (ngSubmit)="onSubmit()">
  <div class="row container">
  <div class="input-field col s6 offset-s3">
          <input id="name" type="text" class="validate" required [(ngModel)]="this.village.name" name="name" #name="ngModel" minlength="3" maxlength="14" pattern="[A-z]*">
          <label for="name">Name</label>
          <div *ngIf="name.errors && (name.dirty|| x.touched)"
             class="alert alert-danger">
            <div [hidden]="!name.errors.required">
              Name is required
            </div>
            <div [hidden]="!name.errors.minlength">
             Name cannot be less than 3 characters long.
            </div>
            <div [hidden]="!name.errors.pattern">
             Name is incorrect!
            </div>
            <div [hidden]="!name.errors.maxlength">
              Name cannot be more than 14 characters long.
            </div>
        </div>
         <!--<div *ngIf="formErrors.name" class="alert alert-danger">-->
          <!--{{ formErrors.name }}-->
        <!--</div>-->
        </div>
  
  <div class="input-field col s6 offset-s3">
          <input id="x" type="text" class="validate" [(ngModel)]="village.x" required name="x" pattern="[0-9]*" #x="ngModel">
          <label for="x">X</label>
          <div *ngIf="x.errors && (x.dirty || x.touched)"
             class="alert alert-danger">
            <div [hidden]="!x.errors.required">
              X coordinate is required
            </div>
        
            <div [hidden]="!x.errors.pattern">
             Coordinates can contain chars only!
            </div>
            
        </div>
        </div>
        <div class="input-field col s6 offset-s3">
          <input id="y" type="text" class="validate" [(ngModel)]="village.y" name="y" pattern="[0-9]*" required #y="ngModel">
          <label for="y">Y</label>
          <div *ngIf="y.errors && (y.dirty || y.touched)"
             class="alert alert-danger">
            <div [hidden]="!y.errors.required">
              Y coordinate is required
            </div>
        
            <div [hidden]="!y.errors.pattern">
             Coordinates can contain chars only!
            </div>
        </div>
        </div>

<div class="input-field col s6 offset-s3">
          <input id="population" type="text" class="validate" required [(ngModel)]="village.population"
           name="population" pattern="[0-9]*" #population="ngModel">
          <label for="population">Population</label>
          <div *ngIf="population.errors && (population.dirty || population.touched)"
             class="alert alert-danger">
            <div [hidden]="!population.errors.required">
              Population  is required
            </div>
        
            <div [hidden]="!population.errors.pattern">
             Population value can contain chars only!
            </div>
        </div>
        </div>
  
<div class="input-field col s6 offset-s3">
Is capital?
</div>
<div class="switch ">
    <label class="col offset-s3">
         No
      <input type="checkbox" [(ngModel)]="village.isCapital" name="isCapital">
      <span class="lever"></span>
      Yes
    </label>
  </div>


  <div class="input-field col s6 offset-s3">
               <a (click)="addArmies()" class="btn-floating btn-large waves-effect waves-light red"><i class="material-icons">add</i></a>
</div>
  
  <div add-army *ngFor="let army of village.armies" [army]="army"></div>
  <div class="input-field col s6 offset-s3">
 <button type="submit" class="btn btn-default"
             [disabled]="!heroForm.form.valid">Submit</button>
</div>
  </div>  	
  </form>
`
})
export class AddVillageForm implements OnInit,AfterViewChecked{
    ngOnInit(): void {
    }
  village:Village;
  constructor(villageService:VillageService){
        this.village=new Village;
        this.village.armies=[];
        this.village.isCapital=false;
  }

    heroForm: NgForm;
    @ViewChild('heroForm') currentForm: NgForm;

    ngAfterViewChecked() {
        console.log(this.village);
        // this.formChanged();
    }

    formChanged() {
        // console.log(this.currentForm);
        // console.log(this.str);
        // alert(this.heroForm===null);
        if (this.currentForm === this.heroForm) {
            // alert("dgfdfg");
            // console.log(this.currentForm);
            // console.log(this.heroForm);
            return;
        }
        this.heroForm = this.currentForm;
        if (this.heroForm) {
            this.heroForm.valueChanges
                .subscribe(data => this.onValueChanged(data));
        }
    }

    onValueChanged(data?: any) {
        // alert("dgfdfg");
        if (!this.heroForm) {
            return; }
        const form = this.heroForm.form;

        for (const field in this.formErrors) {
            // clear previous error message (if any)
            this.formErrors[field] = '';
            const control = form.get(field);

            if (control && control.dirty && !control.valid) {
                const messages = this.validationMessages[field];
                for (const key in control.errors) {
                    this.formErrors[field] += messages[key] + ' ';
                }
            }
        }
    }

    formErrors = {
        'name': '',
        'power': ''
    };

    validationMessages = {
        'name': {
            'required':      'Name is required.',
            'minlength':     'Name must be at least 4 characters long.',
            'maxlength':     'Name cannot be more than 24 characters long.',
            'forbiddenName': 'Someone named "Bob" cannot be a hero.'
        },
        'power': {
            'required': 'Power is required.'
        }
    };


    addArmies(){
      this.village.armies.push(new Army);
      console.log(this.village);
    }

 onSubmit(){
        this.vi
 }

}