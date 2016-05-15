var appConfig = {
		servidor: '/servant',
		versao_app: '1.0.0.SNAPSHOT',

		ambiente: {
			isNotMinify : 'true'
		},
		baseUrl :  'backend' ,
		defaultRoute : 'login',
		login: {
			url: '/backend'  + '/oauth/token',//'login.json',
			url_usuario: '/backend'  +  '/api/login/dadosUsuarioLogado',// 'usuario.json',
			sucesso: 'manterServidor',
			limite: 30
		},
		logout: {
			url: '/backend/api/login/logout'//logout.json
		}
};

var libDir =  'lib/';
var apiDir = appConfig.servidor + '/vendor/angular/api/' + appConfig.versao_arquitetura + '/';
var componentesDir = libDir + 'componentes/';
var bibliotecasDir =  libDir + 'vendor/';
var modulosDir = 'modules/';

requirejs.config({
	baseUrl:'app',
	paths: {
		'app'		: [ 'app'],
		'config'	: [ 'config'],
		'init'		: [libDir + 'init']
	},
	shim: {
		'app': {deps: ['init'], exports: 'app'},
		'config': {deps: ['init'], exports: 'config' }
	}, deps: ['app']
});

var _createRoute = function(rotas){
	
	
	var isMinify = appConfig.ambiente.isNotMinify == 'true';

	if(!isMinify) {
		sufixo = '.min';
		rotasMin = [];
		angular.forEach(rotas, function(value, key) {
			rotasMin.push(value + sufixo);
		});
		return rotasMin;
	}else
		return rotas;
};