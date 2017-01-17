/**
 * Created by okunetc on 13.01.2017.
 */
System.register(["./player", "@angular/core", "../services/player.service", "@angular/router"], function(exports_1, context_1) {
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
    var player_1, core_1, player_service_1, router_1;
    var PlayerComponent;
    return {
        setters:[
            function (player_1_1) {
                player_1 = player_1_1;
            },
            function (core_1_1) {
                core_1 = core_1_1;
            },
            function (player_service_1_1) {
                player_service_1 = player_service_1_1;
            },
            function (router_1_1) {
                router_1 = router_1_1;
            }],
        execute: function() {
            PlayerComponent = (function () {
                function PlayerComponent(playerService, route) {
                    this.playerService = playerService;
                    this.route = route;
                    this.players = [new player_1.Player(), new player_1.Player(), new player_1.Player];
                }
                PlayerComponent.prototype.gt = function () {
                    this.playerService.activatedRoute = this.route;
                    // this.route.queryParams.subscribe((params:Params)=>
                    // {
                    //     console.log(params);
                    // });
                    // this.route.params.subscribe((params:Params)=>
                    // {
                    //     console.log(params);
                    // });
                    this.playerService.getById();
                };
                PlayerComponent = __decorate([
                    core_1.Component({
                        selector: 'player',
                        // templateUrl : 'components/player/player.html'
                        template: "\n<player-head></player-head>\n<player-list [players]=\"players\"></player-list>\n<button (click)=\"gt()\">button</button>\n"
                    }), 
                    __metadata('design:paramtypes', [player_service_1.PlayerService, router_1.ActivatedRoute])
                ], PlayerComponent);
                return PlayerComponent;
            }());
            exports_1("PlayerComponent", PlayerComponent);
        }
    }
});
//# sourceMappingURL=player.component.js.map