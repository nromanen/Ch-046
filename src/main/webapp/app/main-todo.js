System.register(['angular2/bootstrap', './apptodo.component', "angular2/http"], function(exports_1, context_1) {
    "use strict";
    var __moduleName = context_1 && context_1.id;
    var bootstrap_1, apptodo_component_1, http_1;
    return {
        setters:[
            function (bootstrap_1_1) {
                bootstrap_1 = bootstrap_1_1;
            },
            function (apptodo_component_1_1) {
                apptodo_component_1 = apptodo_component_1_1;
            },
            function (http_1_1) {
                http_1 = http_1_1;
            }],
        execute: function() {
            bootstrap_1.bootstrap(apptodo_component_1.AppTodoComponent, [http_1.HTTP_PROVIDERS]);
        }
    }
});
//# sourceMappingURL=main-todo.js.map