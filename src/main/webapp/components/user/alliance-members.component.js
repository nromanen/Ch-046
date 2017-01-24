/**
 * Created by vyach on 16.01.2017.
 */
System.register(['@angular/core'], function(exports_1, context_1) {
    "use strict";
    var __moduleName = context_1 && context_1.id;
    var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
        var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
        if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
        else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
        return c > 3 && r && Object.defineProperty(target, key, r), r;
    };
    var __metadata = (this && this.__metadata) || function (k, v) {
        if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
    };
    var core_1;
    var AllianceMembersComponent;
    return {
        setters:[
            function (core_1_1) {
                core_1 = core_1_1;
            }],
        execute: function() {
            AllianceMembersComponent = (function () {
                function AllianceMembersComponent() {
                    this.actionUpdate = new core_1.EventEmitter();
                    this.actionDelete = new core_1.EventEmitter();
                }
                AllianceMembersComponent.prototype.selectMember = function (member) {
                    console.log("AllianceMembersComponent.selectMember() method is working.");
                    this.selectedMember = member;
                };
                AllianceMembersComponent.prototype.updateMember = function (editedMember) {
                    console.log("AllianceMembersComponent.updateMember() method is working. Edited member is: " + JSON.stringify(editedMember));
                    if (editedMember === null) {
                        this.selectedMember = null;
                    }
                    else {
                        this.actionUpdate.emit(editedMember);
                        this.selectedMember = null;
                    }
                };
                AllianceMembersComponent.prototype.deleteMember = function (member) {
                    console.log("AllianceMembersComponent.deleteMember() method is working. Member is: " + JSON.stringify(member));
                    this.actionDelete.emit(member);
                };
                __decorate([
                    core_1.Input(), 
                    __metadata('design:type', Array)
                ], AllianceMembersComponent.prototype, "allianceMembers", void 0);
                __decorate([
                    core_1.Output(), 
                    __metadata('design:type', core_1.EventEmitter)
                ], AllianceMembersComponent.prototype, "actionUpdate", void 0);
                __decorate([
                    core_1.Output(), 
                    __metadata('design:type', core_1.EventEmitter)
                ], AllianceMembersComponent.prototype, "actionDelete", void 0);
                AllianceMembersComponent = __decorate([
                    core_1.Component({
                        selector: "alliance-members",
                        templateUrl: "components/user/alliance-members.html",
                    }), 
                    __metadata('design:paramtypes', [])
                ], AllianceMembersComponent);
                return AllianceMembersComponent;
            }());
            exports_1("AllianceMembersComponent", AllianceMembersComponent);
        }
    }
});
//# sourceMappingURL=alliance-members.component.js.map