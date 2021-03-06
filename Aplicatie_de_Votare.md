# Aplicatie de Votare

## Descrierea Temei

Aplicatia este conceputa sa ofere posibilitatea digitalizarii sistemului de vot actual. Va oferi suport pentru toate tipurile de alegeri:

 - Prezindentiale
 - Parlamentare
 - Locale
 - Euro-Parlamentare
 
De asemenea va oferi posibilitatea crearii de sondaje oficiale, sau personale.

Toate acestea conform principilor votului unic si secret.
Aplicatia trebuie sa poata trimite cetatenilor notificari inaintea inceperii alegerior, in timpul alegerilor, iar la finalul acestora sa se poate observa rezultatele.

Aplicatia va avea suport pentru trei tipul de useri:

- Administrator
- Membru
- Cetatean

#### Administratorul
Aceasta este persoana cu cele mai multe responsabilitati si cel mai mult control asupra continutului bazei de date. 

Acesta trebuie in primul rand, sa creeze alegerile si sondajele oficiale conform programului si structurii actuale, iar la finalul acestora sa furnizeze un raport cu rezultatele si efectele lor.

Trebuie sa adauge persoane in baza de date conform cand acestea se nasc, obtin cetatenia. De asemenea trebuie sa stearga persoanele decedate sau cele care revoca cetatenia

Administratorul este responsabil si de adaugarea si stergerea partidelor politice in baza de date.

Trebuie sa poate vota din interfata corespunzatoare

### Membru

Aceste persoane sunt cele care au ales sa-si creeze un cont propriu in intermediul aplicatiei. Aceste persoane nu au limita de varsta si trebuie sa aiba cetatenie (sa fie in baza de date deja).

Facilitatile oferite de acest rol sunt de a crea sondaje proprii, de a le putea distribui si de a putea observa rezultatele acestora.

Trebuie sa poate vota din interfata corespunzatoare

### Cetatean

Cetatenii trebuie sa poate vota/participa la sondaje introducand doar codul numeric personal (cnp). In cazul alegerilor oficiale, trebuie sa aiba varsta de cel putin 18 ani.

De asemenea, trebuie sa primeasca notificari cu privire la alegerile in desfasurare si de a vizualiza rezultatele indiferent daca au votat sau nu.

## Tehnologi folosite

Pentru implementare am ales limbajul java impreuna cu framework-ul Spring-Boot pentru crearea efectiva a server-ului, SpringData si Hibernate pentru crearea si interactiunea cu baza de date.

## Implementare
Am intalnit dificultati in a face Spring-Boot-ul si SpringData sa functioneze dupa ce baza de date si abordarea initilala folosea clase EntityManager oferita de Hibernate pe acelasi proiect, motiv pentru care a fost nevoie sa incep un alt proiect 'curat'.

#### Use Case
Pentru implementare am pornit de la urmatoarea diagrama UseCase:
![UseCase](https://raw.githubusercontent.com/tudorpop9/proiectPs/master/UseCase.png)

#### Class Diagram
Starea curenta, simplificata, a relatiilor dintre clase se poate observa in urmatoarea diagrama:
![ClassDiagram](https://raw.githubusercontent.com/tudorpop9/proiectPs/master/ClassDiagram.png)

Partea aplicatiei organizata pe nivele si functionalitati,la momentul curent, se poate observa in urmatoarea figura:
![ClassDiagram2](https://raw.githubusercontent.com/tudorpop9/proiectPs/master/ClassControlletDiagram.png)

#### Sequence Diagram
Am adaugat o diagrama de secventa ce ilustreaza procesul de adaugare si creare a unui obiect de tip Election. Daca obiectul nu exista in DB, se va adauga si se va percepe mesajul "Success", daca obiectul exista deja atunci se va arunca o exceptie iar mesajul perceput este "Failed".
![Sequence Diagram](https://raw.githubusercontent.com/tudorpop9/proiectPs/master/AddElectionSeqDiag.jpg)

### Desing Patters

#### Observer Design Pattern
Acest design pattern este unul de tip comportamental avand la baza interactiunea dintre un emitator si unul sau mai mult receptori. Aceste componente se regasesc sub denumirea de Observable si Observes. Obiectele de tip observable notifica receptorii la schimbarea, sau accesarea unor date de interes.

Integrarea acestul pattern in aplicatia de votare consta in crearea unei clase/seturi de clase care va trimite o forma de notificare la toti userii din baza de date in momentul "deschiderii urnelor" unor alegeri. 
Momentan aplicatia contine suport pentru trimiterea unui e-mail la toti userii care au o adresa de e-mail salvata. Aceasi clasa poate fi folosita si pentru informarea cetatenilor la finalul alegerilor. Acest lucru s-a realizat folosind dependinta JavaMail.

Clasa "Election" suporta detinerea obiectelor ce implementeaza "ElectionObserver". In acest mod se pot crea diferite tipuri de Observeri care pot notifica populatia prin sms de exemplu. 

#### Factory Desing Pattern
Acest desing pattern este unul de tip creational aducand la dispozitie una dintre metodele cele mai bune de a crea obiecte. Ne ajuta sa pastram abstractizarea folosind polimorfismul si oferind o varietate de obiecte cu un comportament usor modificat.

Pentru integrarea acestui pattern in proiect am incercat sa creez un factory pentru obiectele de timp Choice deoarece service-urile era deja implementate. Problema/dificultatea cu folosirea unui factory pentru acestea este ca subclasele sunt foarte variate fata de clasa parinte, unele avand 4-5 noi campuri.

Renuntand la ideea prezentata anterior am decis sa folosesc un Factory Pattern pentru crearea tipurilor de "Alegeri" (clasa Election). Folosind tipurile de Alegeri pe care aplicatia trebuie sa le suporte am creat subclase pe baza acestora. Subclasele implementeaza o metoda de oferire a "raportului", a evidentei voturilor variantelor disponibile, fiecare avand o structura usor schimbata. 

 work in progress..