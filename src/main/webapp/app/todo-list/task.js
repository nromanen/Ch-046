System.register([], function(exports_1, context_1) {
    "use strict";
    var __moduleName = context_1 && context_1.id;
    var Task;
    return {
        setters:[],
        execute: function() {
            /**
             * Created by Admin on 12.07.16.
             */
            Task = (function () {
                function Task(title, completed, id) {
                    this.title = title;
                    this.completed = completed;
                    this.id = id;
                }
                return Task;
            }());
            exports_1("Task", Task);
        }
    }
});
//# sourceMappingURL=task.js.map