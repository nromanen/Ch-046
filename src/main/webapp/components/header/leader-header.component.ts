/**
 * Created by vyach on 26.01.2017.
 */

import {Component} from "@angular/core";


@Component({
    selector: 'leader-header',
    template: `
<nav>
    <div class="nav-wrapper">
        <a class="brand-logo">Logo</a>
        <ul class="right hide-on-med-and-down">
            <li><a routerLink="/leader" routerLinkActive="active">Main</a></li>
            <li><a routerLink="/leader/manager" routerLinkActive="active">Manage</a></li>
            <li><a href="/travian/logout">Logout</a></li>
        </ul>
    </div>
</nav>
    `
})
export class LeaderHeaderComponent {
    
}
