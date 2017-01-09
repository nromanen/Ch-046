import {bootstrap} from 'angular2/bootstrap';
import {AppTodoComponent} from './apptodo.component';
import {HTTP_PROVIDERS} from "angular2/http";

bootstrap(AppTodoComponent, [HTTP_PROVIDERS]);
