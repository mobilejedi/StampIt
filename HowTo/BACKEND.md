#Backend

Il backend è stato realizzato effettuando il reverse engineering del db STAMPIT tramite Hibernate tools per ottenere il modello ad oggetti e poi esponendo le entity come risorse REST utilizzando il framework Spring Data REST.
Dalle entity generate sono stati eliminati i costruttori con parametri perchè non necessari ed è stata eliminata la specifica della dimensione delle collection in fase di inizializzazione.

Per esporre una entity come risorsa REST bisogna creare una interfaccia che estende CrudRepository o un' altra interfaccia che estende CrudRepository (come PagingAndSortingRepository) ed annotarla con @RepositoryRestResource.
Affinchè i repository vengano rilevati ed "attivati" dal framework va specificato il package in cui si trovano nel tag <jpa:repositpries> presente nel file di configurazione, in cui va specificato inoltre il transaction manager ed il entity manager factory che il framework deve usare.

Hibernate è configurato per usare un datasource definito in Tomcat(conf/server.xml, conf/context.xml).
Se si crea il repository di una entity che ha relazioni bidirezionali con altre entity di cui non esistono i repository si verifica una stackoverflow exception (si è provato ad utilizzare [questa soluzione] (http://keenformatics.blogspot.it/2013/08/how-to-solve-json-infinite-recursion.html) ma non ha funzionato).
Per abilitare il protocollo CORS è stato creato il servlet filter CorsFilter.
Da notare che i servizi forniscono le risposte secondo la specifica Hateoas, per cui oltre ai dati sono presenti i link alle risorse.

