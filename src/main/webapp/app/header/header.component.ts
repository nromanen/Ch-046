/**
 * Created by rmochetc on 06.01.2017.
 */


import {Component} from 'angular2/core';



@Component({
    selector: 'my-header',
    template: `
<nav>
    <div class="nav-wrapper">
        <a href="#!" class="brand-logo">Logo</a>
        <a href="#" data-activates="mobile-demo" class="button-collapse"><i class="material-icons">menu</i></a>
        <ul class="right hide-on-med-and-down">
            <li><a href="todo">Todo</a></li>
            <li><a href="/">Alliances</a></li>
            <li><a href="#">Jingle</a></li>
            <li><a href="#">Mobile</a></li>
        </ul>
        <ul class="side-nav" id="mobile-demo">
            <li><a href="#">Show users</a></li>
            <li><a href="#">Add users</a></li>
            <li><a href="#l">Javascript</a></li>
            <li><a href="#l">Mobile</a></li>
        </ul>
    </div>
</nav>

    `,

})

export class HeaderComponent {

}