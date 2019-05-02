# Sagas_Example

Questa applicazione rappresenta un esempio di utilizzo delle Sagas con l'aiuto del framework Eventuate-Tram.
Sono presenti un Consumer e un Order, è possibile compiere le operazioni CRUD su entrambi. 
In particolare quando viene creato un nuovo Order viene verificato che esista il consumerId indicato. 
Se esiste lo stato dell'Order sarà APPROVED altrimenti REJECTED.

# Build dell'App
Per fare il build dell'app eseguire il comando build_all_project.sh

# Run dell'App
Per fare il run dell'app eseguire il comando run_application.sh

Una volta partita l'applicazione sarà possibile provarla aprendo due pagine :
  - http://localhost:8083/swagger-ui.html per l'Order
  - http://localhost:8082/swagger-ui.html per il Consumer
