Programowanie urządzeń mobilnych laboratorium L_1_ 

# Dokumentacja projetu: ** Aplikacja do sprawdzania pogody dla danej lokalizacji **

## Zespoł projetowy:
Łukasz Śliwa

## Opis projektu

Aplikacja do sprawdzania pogody umożliwia użytkownikom uzyskanie aktualnych informacji o warunkach atmosferycznych w wybranej lokalizacji. Użytkownicy mogą przeszukiwać różne lokalizacje oraz sprawdzać prognozy pogody.

## Zakres projektu opis funkcjonalności:

- Wyszukiwanie lokalizacji - Umożliwienie wyszukiwania miejsc za pomocą nazwy lub lokalizacji GPS.
- Wyświetlanie pogody - Prezentacja aktualnych danych pogodowych, takich jak temperatura, wilgotność, prędkość wiatru, itd.
- Prognoza pogody - Dostarczanie prognoz na kolejne dni.
- Interfejs użytkownika - Intuicyjny i responsywny interfejs użytkownika.

## Panele / zakładki aplikacji 

- Panel główny

![image](https://github.com/Warmianin420/Aplikacja-Mobilna/blob/master/images/ss.png)

## Opis pozyskiwania danych pogodowych w aplikacji

Aplikacja korzysta z OpenWeather API do pobierania danych pogodowych dla wybranych lokalizacji. OpenWeather API to popularna platforma, która dostarcza dokładnych i szczegółowych danych pogodowych z różnych części świata.

Proces pozyskiwania danych:
Wybranie lokalizacji:

Użytkownik może wybrać miasto z listy miast.
Możliwe jest także pobranie danych na podstawie bieżącej lokalizacji użytkownika (jeśli przyznano odpowiednie uprawnienia do lokalizacji).
Wysyłanie żądania do API:

Aplikacja wysyła zapytanie HTTP do serwerów OpenWeather API, wykorzystując klucz API, aby uzyskać autoryzowany dostęp do danych.
Zapytanie może dotyczyć:
pogody na podstawie współrzędnych geograficznych (szerokość geofraficzna, długość geograficzna),
pogody na podstawie nazwy miasta.


Przetwarzanie odpowiedzi:

Dane zwracane przez OpenWeather API są w formacie JSON. Aplikacja przetwarza odpowiedź i wyodrębnia informacje takie jak:
nazwa miasta,
temperatura (w °C),
wilgotność (%),
ciśnienie atmosferyczne (hPa),
prędkość i kierunek wiatru,
widoczność (metry),
opis warunków pogodowych (np. "bezchmurnie", "zachmurzenie duże").


Prezentacja danych:

Informacje o pogodzie są wyświetlane użytkownikowi w czytelnej formie w dedykowanym polu tekstowym w aplikacji.
Korzyści z użycia OpenWeather API:
Dostęp do aktualnych danych pogodowych w czasie rzeczywistym.
Możliwość dostosowania wyników do lokalnych jednostek miary (np. °C, hPa).
Globalny zasięg - dane można pobierać dla dowolnego miejsca na świecie.
Uwagi dotyczące implementacji:
Aplikacja korzysta z biblioteki Retrofit do obsługi zapytań sieciowych oraz przetwarzania odpowiedzi.
Klucz API jest przechowywany w aplikacji i jest wykorzystywany do autoryzacji zapytań.
Przy pierwszym uruchomieniu aplikacji użytkownik jest proszony o przyznanie uprawnień do lokalizacji, aby umożliwić pobieranie danych na podstawie bieżącego położenia.

Link do dokumentacji OpenWeather API:
https://openweathermap.org/api

Wszystkie dane pogodowe są uzyskiwane w oparciu o warunki licencji OpenWeather API i są aktualizowane w czasie rzeczywistym na podstawie dostępu do serwerów OpenWeather.

## Wykorzystane uprawnienia aplikacji do:
- Lokalizacji

## Instalacja:

Instalacja aplikacji odbywa się za pomocą instalatora w formacie .apk dostępnego pod adresem:

https://github.com/UR-INF/24-25-pum-l1-24-25_pum_l1_sliwa/blob/main/Pogoda.apk
