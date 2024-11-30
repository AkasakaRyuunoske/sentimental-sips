# Sentimental Sips: Un negozio di tè online

Benvenuti nel repository di Sentimental Sips, una piattaforma online dedicata alla vendita di tè.

## Chi siamo
Sentimental Sips è una piattaforma web che consente agli utenti di esplorare, selezionare e acquistare una varietà di tè.
La nostra missione è fornire una piattaforma accessibile e facile da usare per gli appassionati di tè e per i nuovi utenti.


## Guida Utilizzo

### 1. Inizializzazione:

Prima di usare l'applicativo assicurarsi di avere docker. Nella root directory eseguire 

`$ docker compose up`

che dovrebbe settare il mysql db sulla porta 3306 (default), inizzializare la schema del db e valorizare le tabelle con dei dati iniziali.

### 2. Compilazione:

Sembra che non ci sono problemi con la compilazione, testato con java 17 e 20 in ambiente windows con necessarie variabili di ambiente settati (come JAVA_HOME).
Ide usato: Intelij Ultimate Edition Build #IU-232.9921.47 del 2023.02

### 3. Deploy:
1. Deployare esclusivamente su Tomcat versione >= 10. Prima di deployare copiare due file nella cartella HTTPS configs che si trova nella root e mettere nella cartela /tomcat/conf entrambi i file (windows dovrebbe chiedere se vogliamo sostituire server.xml - dire si).
2. Creare il .war che in intelij si fa in build > build artifact > Sentimental Sips:war
3. Il file war mettere nella cartella /tomcat/webapps
4. eseguire `catalina.bat start` oppure `startup.bat` nella cartella tomcat/bin
5. in browser (o qualsiasi altro agent) apprire https://localhost:8443/HomePage e in teoria dovrebbere fnonziare tutto.
