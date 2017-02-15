/**
 * System configuration for Angular 2 samples
 * Adjust as necessary for your application needs.
 */
(function (global) {
    System.config({
        paths: {
            // paths serve as alias
            'npm:': 'node_modules/'
        },
        // map tells the System loader where to look for things
        map: {
            // our app is within the app folder
            app: 'components',

            // angular bundles
            '@angular/core': 'npm:@angular/core/bundles/core.umd.js',
            '@angular/common': 'npm:@angular/common/bundles/common.umd.js',
            '@angular/compiler': 'npm:@angular/compiler/bundles/compiler.umd.js',
            '@angular/platform-browser': 'npm:@angular/platform-browser/bundles/platform-browser.umd.js',
            '@angular/platform-browser-dynamic': 'npm:@angular/platform-browser-dynamic/bundles/platform-browser-dynamic.umd.js',
            '@angular/http': 'npm:@angular/http/bundles/http.umd.js',
            '@angular/router': 'npm:@angular/router/bundles/router.umd.js',
            '@angular/forms': 'npm:@angular/forms/bundles/forms.umd.js',

            // other libraries
            'rxjs': 'npm:rxjs',
            'underscore': 'npm:underscore/underscore.js',
            'moment': 'npm: moment',
            'ng2-bootstrap':              'npm:ng2-bootstrap',
            'primeng':                   'npm:primeng',
            'ng2-translate': 'npm:ng2-translate/bundles/ng2-translate.umd.js',
            'angular2-cookie':            'npm:angular2-cookie'

        },
        // packages tells the System loader how to load when no filename and/or no extension
        packages: {
            components: {
                main: './main.js',
                defaultExtension: 'js'
            },
            rxjs: {
                defaultExtension: 'js'
            },
            'ng2-translate' : { defaultExtension: 'js' },

            // ng2-bootstrap
            'ng2-bootstrap':              { format: 'cjs', main: 'bundles/ng2-bootstrap.umd.js', defaultExtension: 'js' },
            'moment':                     { main: 'moment.js', defaultExtension: 'js' },
            'primeng':                      { defaultExtension: 'js'},
            'angular2-cookie': {
                main: './core.js',
                defaultExtension: 'js'
            }
        }
    });
})(this);

