'use strict';

/* Controllers */

angular.module('myApp.controllers', [])
	.controller('CardDetailCtrl', ['$scope','cardInfo', 'Merchant', function($scope, cardInfo, Merchant) {
		$scope.cardInfo = cardInfo;	
		
		$scope.showCard = true;
		
		$scope.toggleInfo = function(){
			if($scope.showCard){
				Merchant.getInfo(cardInfo.idMerchant).then(
				function(response){
					$scope.merchantInfo=response;
					$scope.showCard = ! $scope.showCard;
				},
				function(){
				});
			}else{
				$scope.showCard = ! $scope.showCard;
			}
		}
	}])
	.controller('CardListCtrl', ['$scope','activeCardList', 'Card', function($scope,activeCardList, Card) {
		$scope.activeCardList = activeCardList;
		
	}])
	.controller('CustomerProfileCtrl', ['$scope','customerInfo','Customer', function($scope, customerInfo, Customer) {
		$scope.customerInfo = customerInfo;	
		
		$scope.updateInfo = function(){
			Customer.updateInfo($scope.customerInfo);
		}	
	}])
	.controller('FaqsCtrl', ['$scope', function($scope) {
		
	}])
	.controller('FeedbackCtrl', ['$scope','Customer', function($scope, Customer) {
		$scope.sendFeedback = function(feedback){
			Customer.sendFeedback(feedback);
		}
	}])
	.controller('HowToCtrl', ['$scope', function($scope) {
		
	}])
	.controller('LastScansCtrl', ['$scope' , 'lastScansList', function($scope, lastScansList) {
		$scope.lastScansList = lastScansList;
	}])
	.controller('LoginCtrl', ['$scope','Auth', function($scope, Auth) {
		$scope.login = function(data){
			Auth.login(data);
		};
		$scope.loginFB = function(data){
			Auth.loginFB(data);
		};
	}])
	.controller('LogoutCtrl', ['$scope','Auth', function($scope,Auth) {
		$scope.logout = function(){
			Auth.logout();
		};		
	}])
	.controller('MainCtrl', ['$scope', 'Scan', 'customerInfo', 'activeCardList', '$location', function($scope, Scan, customerInfo, activeCardList, $location) {
		$scope.customerInfo = customerInfo;
		$scope.activeCardList = activeCardList;
		
		$scope.cardDetail = function(idMerchant,idCard){
			$location.path("/card_detail/"+idCard);
		};
		
		$scope.sendQrCode = function(codeData){
			Scan.sendQrCode(codeData);
		};
	}])
	.controller('NearbyCtrl', ['$scope','nearbyList', function($scope,nearbyList) {
		$scope.nearbyList = nearbyList.nearby_list;
	}])
	.controller('PasswordRecoveryCtrl', ['$scope','Auth','$location', function($scope, Auth,$location) {
		$scope.passwordRecovery = function(data){
			Auth.passwordRecovery(data);
		};
		
		
	}])
	.controller('SettingCtrl', ['$scope','customerSetting', 'Customer', function($scope,customerSetting, Customer) {
		$scope.customerSetting = customerSetting;
		
		$scope.updateSetting = function(){
			Customer.updateSetting($scope.customerSetting);
		}
	}])
	.controller('SignupCtrl', ['$scope','Auth','$location', function($scope, Auth,$location) {
		$scope.signup = function(data){
			Auth.signup(data);
		};
		
	}])
	.controller('SplashCtrl', ['$scope','$interval','$location', function($scope,$interval,$location) {
		$scope.timer = $interval(function($scope){
				$location.path("/login");
			},1000,1);
	}])
	
	.controller('ErrorCtrl', ['$scope','Customer','$location', function($scope,Customer,$location) {
		$scope.backToLogin = function(){
			Customer.reset();
			$location.path("/login");
		}
	}])
	
	
	.controller('TESTCtrl', ['$scope','BackendService', function($scope, BackendService) {
		$scope.successCB = function($scope){
			console.log("SUCCESSO");
		};
		$scope.errorCB = function($scope){
			console.log("ERRORE");
		};
		
		$scope.test = function(){
			BackendService.login($scope.successCB,$scope.errorCB);
			BackendService.login_fb($scope.successCB,$scope.errorCB);
			BackendService.signup($scope.successCB,$scope.errorCB);
			BackendService.password_recovery($scope.successCB,$scope.errorCB);
			BackendService.logout($scope.successCB,$scope.errorCB);
			BackendService.get_customer_info($scope.successCB,$scope.errorCB);
			BackendService.get_customer_setting($scope.successCB,$scope.errorCB);
			BackendService.update_customer_setting($scope.successCB,$scope.errorCB);
			BackendService.update_customer_picture($scope.successCB,$scope.errorCB);
			BackendService.update_customer_info($scope.successCB,$scope.errorCB);
			BackendService.send_feedback({"id_customer":"1"},{},$scope.successCB,$scope.errorCB);
			BackendService.nearby_list($scope.successCB,$scope.errorCB);
			BackendService.merchant_info($scope.successCB,$scope.errorCB);
			BackendService.active_card_list($scope.successCB,$scope.errorCB);
			BackendService.card_info($scope.successCB,$scope.errorCB);
			BackendService.send_qr_code($scope.successCB,$scope.errorCB);
			BackendService.last_scan_list($scope.successCB,$scope.errorCB);
		}
	}])
;
