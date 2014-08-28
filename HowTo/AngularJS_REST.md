SCOPO GUIDA
Capire come eseguire delle chiamate ad un servizio REST remoto

REQUISITI NECESSARI (disponibili sul sito ufficiale di AngularJS)
-saper creare un controller
-saper creare una factory e conoscerne la struttura
-includere nel progetto la dipendenda ngResource e quindi rendere disponibile  $resource

ESEMPIO
Nel successivo esempio verrà chiamato un server REST presente all'indirizzo http://localhost:8080 e verranno implementate
le chiamate CRUD necessarie a gestire la risorsa /risorsaProva/<id_risorsa>

STEP
1)creazione factory dal nome 'backendFactory'
NB $resource restituisce gia una factory, quindi stiamo in pratica aggiungendo delle action personalizzate 

*************
angular.module('myApp.services', ['ngResource'])
.factory('backendFactory',function($resource){
		return $resource(
			'', //<-url di default se serve, altrimenti possiamo sovrascriverla nelle action personalizzate
			{}, //<-parametri di default opzionali
			{
				actionPUT : { method : 'PUT', url : 'http://localhost:8080/risorsaProva/:id' },
				actionGET : { method : 'GET', url : 'http://localhost:8080/risorsaProva/:id' },
				actionPOST : { method : 'POST', url : 'http://localhost:8080/risorsaProva/:id' },
				actionDELETE : { method : 'DELETE', url : 'http://localhost:8080/risorsaProva/:id' }
			}
		);
	}
);
************

la 'backendFactory' qui dichiarata non definisce nessuna funzione (vedere una factory di base sulla doc ufficiale di
angular) e al suo interno e restituisce un oggetto piu o meno di questo tipo

 { 
    //action di default istanziate in automatico
    'get':    {method:'GET'},
    'save':   {method:'POST'},
    'query':  {method:'GET', isArray:true},
    'remove': {method:'DELETE'},
    'delete': {method:'DELETE'},
    //action personalizzate
    'actionPUT' : { method : 'PUT', url : 'http://localhost:8080/risorsaProva/:id' },
    'actionGET' : { method : 'GET', url : 'http://localhost:8080/risorsaProva/:id' },
    'actionPOST' : { method : 'POST', url : 'http://localhost:8080/risorsaProva/:id' },
    'actionDELETE' : { method : 'DELETE', url : 'http://localhost:8080/risorsaProva/:id' 
    
  };
  
  - possiamo notare un prima sezione contenente i metodi di default che si basano sull'url di 
  default(in questo caso vuota) e i paramentri di default (anche qui vuoti perche ho preferito istanziarli direttamente
  sulla chiamata)
  - nella seconda sezione sono presetni i metodi personalizzati dove sono stati esplicitati 
  method e url (si poteva anche togliere url, ma in questo caso e' stato scelto di ridefinirla) ed e' stato 
  dichiarato il metodo corrispondente all'azione PUT che di default e' non presente
  
  questa factory rende disponibile ovunque venga richiamata la possibilita di eseguire le action sopra elencate 
  con questa sintassi a seconda dei tipi di 'method':
  
  GET,DELETE
    -backendFactory.<NOME_ACTION>(params,successCB,errorCB);
  PUT, POST
    -backendFactory.<NOME_ACTION>(params, data, successCB,errorCB);
  
  NB 
    1)params sono i parametri che verranno passati con queste regole
      -se un parametro corrisponde ad un segnaposto nella url (es ':id') verra inserito tra / /
      -se un paramentro non e' presente verraà accodato nella query string dell'url
      
      ESEMPIO 
      url = http://localhost:8080/risorsaProva/:id
      params = {"id" : "15", "nome" : "Mario" , "cognome" : "Rossi"}
      l'url costruita alla chiamata sarà -> http://localhost:8080/risorsaProva/15?nome=Mario&cognome=Rossi
    
    2)'data' e' l'oggetto che verrà incluso nel body del messaggio passato al servizo REST
    
    3) successCB e errorCB sono le due callback che verranno chiamate in caso di chiamata al servizio andata a buon fine 
       e chiamata al servizio terminata con errore

2)creazione del controller 

*************************************
angular.module('myApp.controllers', [])
  .controller('MyCtrl1', ['$scope', 'backendFactory', function($scope, backendFactory) {
		
		//CHIAMATE A SERVIZIO
		$scope.provaGET = function(){
			console.log("chiamata prova GET ");
			backendFactory.actionGET({"id":"1"},successCB,errorCB);
		}
		$scope.provaPUT = function(){
			console.log("chiamata prova PUT ");
			backendFactory.actionPUT({"id":"1"},{"nome" : "mario"},successCB,errorCB);
		}
		$scope.provaPOST = function(){
			console.log("chiamata prova POST ");
			backendFactory.actionPOST({"id":"1"},{"nome" : "mario"},successCB,errorCB);
		}
		$scope.provaDELETE = function(){
			console.log("chiamata prova DELETE ");
			backendFactory.actionDELETE({"id":"1"}, successCB, errorCB);
		}
		//CALLBACK
		var successCB = function(value, responseHeaders){
			console.log("callback restfull successo!");
			console.log("valore: "+value['response']);
		};
		var errorCB = function(httpResponse){
			console.log("callback restfull errore!");
		};
		
  }]);
***************************************************

In questo controller sono state definite 6 funzioni, 4 di chiamata a servizio REST e due come Callback di 
risposta, una di successo e una di errore.

La sintassi e' la stessa per tutte e 4 le chiamate a servizi tranne nelle POST e nelle PUT in cui sono e'
presente l'oggetto da mandare al server, in questo caso {"nome" : "mario"}.
Sono state mappate solo le funzioni personalizzate definite nella factory, ma nulla vieta di usare anche quelle di 
default (basta configurarle, per ora mi sono limitato a implementare le operazioni CRUD su una sola risorsa)
Una volta effettuata la chiamata al servizio interessato in caso di esito positivo verrà chiamata 'successCB' mentre
in caso negativo verrà chiamata 'errorCB'
