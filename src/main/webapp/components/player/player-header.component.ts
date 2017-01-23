/**
 * Created by okunetc on 16.01.2017.
 */
import {Component} from "@angular/core";
@Component(
    {
        selector:'player-head',
        template:`<nav>
<div class="nav-wrapper">
<a href="#!" class="brand-logo">Logo</a>
<ul class="right hide-on-med-and-down">
<li><a href="#" >Add village</a></li>
<li><a href="#">All villages</a></li>
<li><a routerLink="/help" routerLinkActive="active">Ask help</a></li>
<li><a href="/logout" >Log out</a></li>
</ul>
</div>
</nav>`
    }
)
export class PlayerHeader{

}