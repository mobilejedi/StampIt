'use strict';

/* Services */


// Demonstrate how to register services
// In this case it is a simple value service.
angular.module('myApp.services', ['ngResource'])

.value('RestBaseEndpoint', 'http://192.168.43.142:8080/rest')

.factory('BackendService',	['$resource','RestBaseEndpoint',function($resource, RestBaseEndpoint){
	return $resource(
		'',
		{},
		{
			//AUTH
			login : { method : 'POST', url : RestBaseEndpoint+'/login' },
			login_fb : { method : 'POST', url : RestBaseEndpoint+'/login_fb' },
			signup : { method : 'POST', url : RestBaseEndpoint+'/signup' },
			password_recovery : { method : 'POST', url : RestBaseEndpoint+'/password_recovery' },
			logout : { method : 'POST', url : RestBaseEndpoint+'/logout' },
			//CUSTOMER
			active_card_list : { method : 'GET', url : RestBaseEndpoint+'/customers/:id_customer/activeCards'},//OK
			get_customer_info : { method : 'GET', url : RestBaseEndpoint+'/customers/:id_customer' },//OK
			//da decidere se remota o locale(per ora remota mockata)
			get_customer_setting : { method : 'GET', url : RestBaseEndpoint+'/get_customer_setting/:id_customer' },
			update_customer_setting : { method : 'PUT', url : RestBaseEndpoint+'/update_customer_setting/:id_customer' },
			
			//da vedere come inviare image
			update_customer_picture : { method : 'PUT', url : RestBaseEndpoint+'/update_customer_picture/:id_customer' },
			update_customer_info : { method : 'PUT', url : RestBaseEndpoint+'/customers/:id_customer' }, //OK
			send_feedback : { method : 'POST', url : RestBaseEndpoint+'/customers/:id_customer/feedbacks' },//OK
			//MERCHANT
			//custom da fare
			nearby_list : { method : 'GET', url : RestBaseEndpoint+'/nearby_list/'},
			
			merchant_info : { method : 'GET', url : RestBaseEndpoint+'/merchants/:id_merchant' },//OK
			//CARD
			card_info : { method : 'GET', url : RestBaseEndpoint+'/cards/:id_card' }, //OK
			
			//SCAN
			//custom da fare
			send_qr_code : { method : 'POST', url : RestBaseEndpoint+'/send_qr_code/:id_customer' },
			//custom da fare
			last_scan_list : { method : 'GET', url : RestBaseEndpoint+'/last_scan_list/:id_customer'},
		}
	);
}])

.factory('Auth', ['BackendService', 'Customer', '$location', function(BackendService, Customer, $location){
	var factory = {};
	
	factory.login = function(data){
		BackendService.login(data).$promise.then(
			function(result){
				Customer.setId(result.id);
				$location.path("/main");
			},
			function(reason){
				console.log("login error");
				$location.path("/login");
			}
		);
	};
	
	factory.loginFB = function(data){
		BackendService.login_fb(data).$promise.then(
			function(result){
				Customer.setId(result.id);
				$location.path("/main");
			},
			function(reason){
				console.log("loginfb error");
				$location.path("/login");
			}
		);
	};
	
	factory.logout = function(){
		BackendService.logout().$promise.then(
			function(result){
				Customer.reset();
				$location.path("/login");
			},
			function(reason){
				console.log("logout error");
				Customer.reset();
				$location.path("/login");
			}
		);
	};
	
	factory.signup = function(data){
		BackendService.signup(data).$promise.then(
			function(){
				$location.path("/login");
			},
			function(){
				console.log("signup error");
				$location.path("/login");
			}
		);
	};
	
	factory.passwordRecovery = function(data){
		BackendService.password_recovery(data).$promise.then(
			function(){
				$location.path("/login");
			},
			function(){
				console.log("recover error");
				$location.path("/login");
			}
		);
	};
	return factory;
}])

.factory('Customer', ['BackendService', '$location', function(BackendService, $location){
	var factory = {};
	
	var customer = {};
	
	factory.reset = function(){
		customer = {};
	};
	
	factory.getId = function(){
		return customer.id;
	};
	factory.setId = function(customerId){
		customer.id = customerId;
	};
	factory.getInfo = function(){
		return BackendService.get_customer_info({"id_customer": customer.id}).$promise.then(
				function(successData){
					return successData;
				},
				function(){
					$location.path("/error");
				}
			);
	};
	factory.getSetting = function(){
		return BackendService.get_customer_setting({"id_customer": customer.id}).$promise.then(
				function(successData){
					return successData;
				},
				function(){
					$location.path("/error");
				}
			);
		};	
	factory.updateSetting = function(data){
		BackendService.update_customer_info({"id_customer": customer.id},data).$promise.then(
			function(result){
				$location.path("/main");
			},
			function(reason){
				$location.path("/error");
			}
		);
	};		
	factory.updateInfo = function(data){
		BackendService.update_customer_info({"id_customer": customer.id},data).$promise.then(
			function(result){
				$location.path("/main");
			},
			function(reason){
				$location.path("/error");
			}
		);
	};	
	factory.sendFeedback = function(data){
		BackendService.send_feedback({"id_customer": customer.id},data).$promise.then(
			function(result){
				$location.path("/main");
			},
			function(reason){
				$location.path("/error");
			}
		);
	};
	factory.getActiveCardList = function(){
		return BackendService.active_card_list({"id_customer": customer.id}).$promise.then(
				function(successData){
					return successData;
				},
				function(){
					$location.path("/error");
				}
			);
	};
	
	return factory;
}])

.factory('Merchant', ['BackendService', 'Card', '$location', '$q', function(BackendService, Card,  $location, $q){
	var factory = {};
	
	factory.getNearbyList = function(){
		console.log("qui");
		var deferred = $q.defer();
		
		//funzione di cordova
		navigator.geolocation.getCurrentPosition(
			function(position){
				console.log("successo lettura posizione!");
				alert("pos: lat:"+position.coords.latitude+" lon:"+position.coords.longitude);
				return BackendService.nearby_list({"lat":position.coords.latitude,"lon":position.coords.longitude}).$promise.then(
					function(successData){
						deferred.resolve(successData);
					},
					function(){
						deferred.reject();
						//$location.path("/error");
					}
				);
			},
			function(error){
				console.log("Errore lettura gps");
				deferred.reject();
				//$location.path("/error");
			}
		);
		
		return deferred.promise;
		/*
		return BackendService.nearby_list(factory.getCoords()).$promise.then(
				function(successData){
					return successData;
				},
				function(){
					$location.path("/error");
				}
			);
		*/
	};
	
	factory.getInfo = function(idMerchant){
		return BackendService.merchant_info({"id_merchant":idMerchant}).$promise.then(
				function(successData){
					return successData;
				},
				function(){
					$location.path("/error");
				}
			);
	};
	
	return factory;
}])

.factory('Card', ['BackendService', '$location', function(BackendService, $location){
	var factory = {};
	
	factory.getInfo = function(idCard){
		return BackendService.card_info({"id_card":idCard}).$promise.then(
				function(successData){
					return successData;
				},
				function(){
					$location.path("/error");
				}
			);
	};
	return factory;
}])

.factory('Scan', ['BackendService', 'Customer', '$location', function(BackendService, Customer, $location){
	var factory = {};
	
	
	
	factory.sendQrCode = function(data){
		return BackendService.send_qr_code({"id_customer":Customer.getId()},data).$promise.then(
				function(successData){
					$location.path("/card_detail/"+successData.idCard);
				},
				function(){
					$location.path("/error");
				}
			);
	};
	
	factory.lastScanList = function(){
		return BackendService.last_scan_list({"id_customer":Customer.getId()}).$promise.then(
				function(successData){
					return successData;
				},
				function(){
					$location.path("/error");
				}
			);
	};
	
	return factory;
}])
;
