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
 work in progress..