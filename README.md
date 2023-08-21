# zajavka_ogarnizer

Aplikacja służy usprawnieniu zarządzania firmą oferującą serwis sprzętu biurowego (komputery, drukarki, ksero, skanery itp.) podmiotom gospodarczym.
Projekt umożliwia kooperację między pracownikami serwisu. Powstał wskutek własnego doświadczenia pracy w serwisie gdzie takowej aplikacji brakowało.
</br>
</br>
Cały kontekst aplikacji opiera się o dodawanie i zarządzanie zadaniami zleconymi przez klientów, które dalej nazywać będę po prostu zadaniami.
</br>

Zadania podzielone są na trzy kategorie:
 - Serwisy wykonywane u klienta (away work)
 - Serwisy prowadzone w siedzibie firmy (service)
 - Zamówienia - (order)
 </br>

Dodając zadanie możliwym jest określić priorytet (priority) jego wykonania (low, medium, high).
</br>
</br>
Aplikacja przechowuje również podstawowe dane o klientach firmy. W celu ułatwienia dodawania zadań, wcześniej dodanego klienta
wybiera się z listy w panelu dodawania zadań. 
</br>
</br>
Zadanie można aktualizować wprowadzając dodatkowe informacje tekstowe oraz zmieniając etap (stage) wykonania na 
którym się obecnie znajduje. Wyróznione zostały następujące etapy:
 - nowe (just_added) - przypisywane domyślnie dla dodawanych zadań
 - w trakcie realizacji (in_progress)
 - w oczekiwaniu na części (waiting_for_parts)
 - gotowe do zafakturowania (to_invoice)
</br>
Zadanie można zatwierdzić w przypadku pomyślnej realizacji oraz usunąć w przypadku porzucenia realizacji.
W obu przypadkach zadanie trafi na listę zamkniętych zadań (odpowiednio: closed away work, closed service, closed order)
z adekwatną informacją o powodzeniu jego wykonania (w tabeli success). Całkowite usunięcie zadania z aplikacji następuje
po usunięciu go z listy zamkniętych zadań.
</br>

Użytkownicy podzieleni są na dwie kategorie (role):
 - serwisant (serviceman)
 - admin (admin)
</br>

Serwisant posiada uprawnienia do:
 - wyświetlania listy, dodawania, aktualizacji, zamykania (zatwierdzenie lub usunięcie) zadań
 - wyświetlania listy, dodawania klientów
</br>

Admin, prócz uprawnień serwisanta posiada również możliwość:
 - usuwania klientów
 - wyświetlania listy, dodawania, usuwania użytkowników
 - wyświetlania listy, usuwania zamkniętych zadań (closed away work, closed service, closed order)

</br>

Aplikacja posiada również funkcję pobrania z dedykowanego API (zajavka-ogarnizerAPI) losowych danych z osobna dla 
każdego rodzaju zadań i klientów. 




