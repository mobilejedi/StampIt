'use strict';


// Declare app level module which depends on filters, and services
angular.module('myApp', [
  'ngRoute',
  'ngResource',
  'myApp.filters',
  'myApp.services',
  'myApp.directives',
  'myApp.controllers'
])
.config(['$routeProvider', function($routeProvider) {	
	$routeProvider.when('/card_detail/:id_card', 
	{
		templateUrl: 'partials/card_detail.html', controller: 'CardDetailCtrl',
		resolve : {
			cardInfo : function(Card,$route){
				return Card.getInfo($route.current.params.id_card);
			}
		}
		
	});
	$routeProvider.when('/card_list', 
	{
		templateUrl: 'partials/card_list.html', controller: 'CardListCtrl',
		resolve : {
			activeCardList : function(Customer){
				return Customer.getActiveCardList();
			}
		}
	});
	$routeProvider.when('/customer_profile', 
	{
		templateUrl: 'partials/customer_profile.html', controller: 'CustomerProfileCtrl',
		resolve : {
			customerInfo : function(Customer){
				return Customer.getInfo();
			}
		}
	});
	$routeProvider.when('/faqs', {templateUrl: 'partials/faqs.html', controller: 'FaqsCtrl'});
	$routeProvider.when('/feedback', {templateUrl: 'partials/feedback.html', controller: 'FeedbackCtrl'});
	$routeProvider.when('/how_to', {templateUrl: 'partials/how_to.html', controller: 'HowToCtrl'});
	$routeProvider.when('/last_scans', 
	{
		templateUrl: 'partials/last_scans.html', controller: 'LastScansCtrl',
		resolve : {
			lastScansList : function(Scan){
				return Scan.lastScanList();
			}
		}
	});
	$routeProvider.when('/login', {templateUrl: 'partials/login.html', controller: 'LoginCtrl'});
	$routeProvider.when('/logout', {templateUrl: 'partials/logout.html', controller: 'LogoutCtrl'});
	$routeProvider.when('/main', 
	{
		templateUrl: 'partials/main.html', controller: 'MainCtrl',
		resolve : {
			customerInfo : function(Customer){
				return Customer.getInfo();
			},
			activeCardList : function(Customer){
				return Customer.getActiveCardList();
			}
		}
	});
	$routeProvider.when('/nearby', 
	{
		templateUrl: 'partials/nearby.html', controller: 'NearbyCtrl',
		resolve : {
			nearbyList : function(Merchant){
				return Merchant.getNearbyList();
			}
		}
	});
	$routeProvider.when('/password_recovery', {templateUrl: 'partials/password_recovery.html', controller: 'PasswordRecoveryCtrl'});
	$routeProvider.when('/setting', 
	{
		templateUrl: 'partials/setting.html', controller: 'SettingCtrl',
		resolve : {
			customerSetting : function(Customer){
				return Customer.getSetting();
			}
		}
	});
	$routeProvider.when('/signup', {templateUrl: 'partials/signup.html', controller: 'SignupCtrl'});
	$routeProvider.when('/splash', {templateUrl: 'partials/splash.html', controller: 'SplashCtrl'});
	
	$routeProvider.when('/TEST', {templateUrl: 'partials/TEST.html', controller: 'TESTCtrl'});
	$routeProvider.when('/error', {templateUrl: 'partials/error.html', controller: 'ErrorCtrl'});
	
	$routeProvider.otherwise({redirectTo: '/splash'});

}])
.run(['$rootScope','$location', function($rootScope, $location) {	
		$rootScope.$on('$routeChangeError', function(){
			console.log("errore cambio routing!");
			$location.url('/error');
		});
}])
;
