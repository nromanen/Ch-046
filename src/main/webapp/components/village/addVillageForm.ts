/**
 * Created by okunetc on 23.01.2017.
 */
import {Component, OnInit} from "@angular/core";
import {Village} from "../village/village";
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
 
      <form #heroForm="ngForm">
  <div class="row container">
  <div class="input-field col s6 offset-s3">
          <input id="name" type="text" class="validate" [(ngModel)]="this.str" name="name" #name="ngModel" required pattern="^[0-9]{1,3}">
          <label for="name">Name</label>
          <div *ngIf="name.errors && (name.dirty || name.touched)"
             class="alert alert-danger">
            <div [hidden]="!name.errors.required">
              Name is required
            </div>
            <div [hidden]="!name.errors.requiredpattern">
              Name must be at least 4 characters long.
            </div>
            <div [hidden]="!name.errors.maxlength">
              Name cannot be more than 24 characters long.
            </div>
        </div>
        </div>
  
  <div class="input-field col s6 offset-s3">
          <input id="x" type="text" class="validate">
          <label for="x">X</label>
        </div>
        <div class="input-field col s6 offset-s3">
          <input id="y" type="text" class="validate">
          <label for="y">Y</label>
        </div>

<div class="input-field col s6 offset-s3">
          <input id="population" type="text" class="validate">
          <label for="population">Population</label>
        </div>


  	 <div class="input-field col s6 offset-s3" >
    <select class="browser-default" id="select" >
    <option value="" disabled selected>Choose your option</option>
    <option value="1">Option 1</option>
    <option value="2">Option 2</option>
    <option value="3">Option 3</option>
  </select>
    </div>
  
<div class="input-field col s6 offset-s3">
Is capital?
</div>
<div class="switch ">
    <label class="col offset-s3">
         No
      <input type="checkbox">
      <span class="lever"></span>
      Yes
    </label>
  </div>

<div class="input-field col s6 offset-s3">
<!--<button class="btn waves-effect waves-light col offset-s5 " type="submit" name="action">Add-->
    <!--<i class="material-icons right">send</i>-->
  <!--</button>-->
  
 <button type="submit" class="btn btn-default"
             [disabled]="!heroForm.form.valid">Submit</button>
</div>
  </div>  	
  </form>
`
})
export class AddVillageForm implements OnInit{
    ngOnInit(): void {
        this.str='gdgfdgd';
    }
  a:number[];
  str:string;
  constructor(){
      this.a=[];
      this.a.push(5);
      this.str="kjjkkjkj";
  }
}