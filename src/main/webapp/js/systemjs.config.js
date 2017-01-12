/**
 * System configuration for Angular samples
 * Adjust as necessary for your application needs.
 */
(function (global) {
    System.config({
        paths:{
            'angular2/*': 'node_modules/angular2/*',
            'rxjs/*': 'node_modules/rxjs/*',
            './*': 'components/*'
        },
        defaultJSExtensions: true,
        globalEvaluationScope: false,
        format: 'register',
        packages: {
            globalEvaluationScope: false,
            'components': {
                format: 'register',
                defaultJSExtensions: true,
                defaultExtension: 'js',
                globalEvaluationScope: false
            }
        }
    });
})(this);

