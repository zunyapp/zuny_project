var gulp = require('gulp'),
	gp_concat = require('gulp-concat'),
	gp_rename = require('gulp-rename'),
	gp_uglify = require('gulp-uglify'),
	ngHtml2Js = require('gulp-ng-html2js'),
	webserver = require('gulp-webserver'),
	minifyHtml = require("gulp-minify-html");

// ARQUIVOS PARA MINICAR E CONCATENAR
var arquivos = [
    'app/*.js',
    'app/modules/**/.js',
    'app/lib/componentes/blockUi/blockUi.js',
    'app/lib/componentes/ios-mensagem-erro/dist/ios-mensagem-erro-concat.min.js',
    'app/lib/componentes/ios-validator-cnpj/ios-validator-cnpj.js',
    'app/lib/componentes/ios-validator-cpf/ios-validator-cpf.js',
    'app/lib/componentes/iosNotification/iosNotification.js',
    'app/lib/componentes/iosRoute/iosRoute.js',
    'app/lib/componentes/template/js/*.js',
    'app/lib/vendor/angular/angular.js',
    'app/lib/vendor/angular-block-ui/dist/angular-block-ui.js',
    'app/lib/vendor/angular-bootstrap/ui-bootstrap-tpls.min.js',
    'app/lib/vendor/angular-breadcrumb/angular-breadcrumb.min.js',
    'app/lib/vendor/angular-ellipsis/src/angular-ellipsis.js',
    'app/lib/vendor/angular-locale/angular-locale_pt-br.js',
    'app/lib/vendor/angular-translate/angular-translate.min.js',
    'app/lib/vendor/angular-translate-loader-partial/angular-translate-loader-partial.min.js',
    'app/lib/vendor/angular-ui-dashboard/js/angular-ui-dashboard.min.js',
    'app/lib/vendor/angular-ui-sortable/angular-ui-sortable.min.js',
    'app/lib/vendor/angularAMD/angularAMD.js',
    'app/lib/vendor/angularjs/angularjs.min.js',
    'app/lib/vendor/blockUi/blockUi.js',
    'app/lib/vendor/bootstrap/js/bootstrap.js',
    'app/lib/vendor/jquery/jquery-1.11.3.min.js',
    'app/lib/vendor/jquery-ui/js/jquery-ui.min.js',
    'app/lib/vendor/lo-dash/lodash.js',
    'app/lib/vendor/moment/min/moment.min.js',
    'app/lib/vendor/ng-table/dist/ng-table.min.js',
    'app/lib/vendor/ngMask/dist/ngMask.js',
    'app/lib/vendor/notify/js/angular-ui-notification.min.js',
    'app/lib/vendor/requirejs-domready/domReady.js',
    'app/lib/vendor/restangular/restangular.js',
    'app/lib/vendor/uiRouter/angular-ui-router.min.js',
    'app/lib/vendor/underscore/underscore.min.js',
    'app/lib/vendor/*.js'
    ];
// FIM --- ARQUIVOS PARA MINICAR E CONCATENAR

gulp.task('webserver', function() {
    gulp.src('./')
    .pipe(webserver({
        port: 8000,
        livereload: true,
				host: '0.0.0.0',
        open: 'http://localhost:8000/',
        proxies: [
	          {
	              source: '/backend', target: 'http://localhost:8081/backend'
	          }
	      ]
    }));
});

gulp.task('transformaJS', function(){
	gulp.src('app/modalMensagensCaixaPostal.html')
	.pipe(minifyHtml({
		empty: true,
		spare: true,
		quotes: true
	}))
	.pipe(ngHtml2Js({
		moduleName: 'webapp',
		prefix: 'app/'
	}))
	.pipe(gulp.dest('app'));
});

gulp.task('constroi', function(){
    gulp.src(arquivos)
    .pipe(gp_concat('concat.js'))
    .pipe(gulp.dest('dist'))
    .pipe(gp_rename('uglify.js'))
    .pipe(gp_uglify())
    .pipe(gulp.dest('dist'));
});
