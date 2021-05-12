# Cieślar Karol - Projekt Rekrutacyjny

## O projekcie
Projekt został stworzony w celu pokazania swoich umiejętności programowania w języku Java.
Wykorzystany stack technologiczny to: Jpa Repository, Spring Boot, Maven, Hibernate

Program ma na celu pobieranie przykładowych postów z ogólnodostępnej bazy w formacie JSON.
Dane z JSON'a zapisywane są do lokalnej bazy danych. Program posiada automatyczną aktualizację bazy codziennie
o godzinie 12 nie uwzględniając przy tym edytowanych danych oraz usuniętych z lokalnej bazy.


### Funkcje
* Cykliczne pobieranie aktualizacji z formatu JSON i zapisywanie ich do lokalnej bazy danych (nie uwzględniając przy tym edytowanych danych oraz usuniętych z lokalnej bazy)
* Aktualizacja danych może być wywołana ręcznie poprzez API
* Możliwość edycji atrybutów postów poprzez API
* Możliwość usuwania postów z bazy danych (są one wówczas oznaczone jako usunięte)


### API
* Aktualizacja danych ``POST`` ``/posts/update``
* Pobieranie postów z podanym tytułem ``POST`` ``/posts/{post_title}``
* Oznaczanie posta jako usunięty  ``DELETE`` ``/posts/{post_id}``
* Edycja posta  ``PUT`` ``/posts/{post_id}`` Parametry: title, body


### Instalacja i Uruchomienie
Projekt uruchomić można w IDE lub za pomocą maven ``mvnw spring-boot:run``
