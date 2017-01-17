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
<a href="#" data-activates="mobile-demo" class="button-collapse"><i class="material-icons">menu</i></a>
<ul class="right hide-on-med-and-down">
<li><a href="addVillage.html">Add village</a></li>
<li><a href="villagesList.html">All villages</a></li>
<li><a href="/logout" >Log out</a></li>
</ul>
<ul class="side-nav" id="mobile-demo">
<li><a href="sass.html">Show users</a></li>
<li><a href="badges.html">Add users</a></li>
<li><a href="collapsible.html">Javascript</a></li>
<li><a href="mobile.html">Mobile</a></li>
</ul>
</div>
</nav>`
    }
)
export class PlayerHeader{

}