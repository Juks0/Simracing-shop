Simshop
Opis projektu

Simshop to system obsługujący sklep internetowy z akcesoriami simracingowymi. System umożliwia zarządzanie asortymentem (produkty: bazy, kierownice, pedały), obsługę zamówień, zwrotów oraz użytkowników i administratorów.
Funkcjonalności (w skrócie)

    Obiekty dziedziczenia: Product → Base, Wheel, Pedals

    Każdy produkt posiada unikalny numer seryjny, nazwę, cenę, zdjęcie, gwarancję

    Produkty mają cechy charakterystyczne dla typu (np. moment obrotowy, materiał, obecność sprzęgła)

    Produkty powiązane z markami (Brand) i rabatami (Discount)

    Zamówienia mogą zawierać wiele pozycji produktowych i status (możliwy zwrot)

    Obsługa użytkownika (User), administratora (Admin), superadmina (SuperAdmin)

    Walidacja danych, unikalność numerów seryjnych, automatyczne wyliczanie wieku itp.

    Serializacja danych (trwałość – ObjectPlus)

    Model gotowy do rozbudowy (np. rozbudowana obsługa zamówień)

Model klas

    Product (abstrakcyjna): serial, name, price, imgPath, quantity, guarantee, serialCounters (statyczny)

        Base: torque (enum), ifBoostMode (boolean)

        Wheel: size (int[1..3]), material (enum), rotationAngle (int)

        Pedals: ifClutch (opcjonalne)

    Brand: name, featuredProducts (do 3)

    Discount: percentage, oldPrice

    Order: data, status ("ORDERED", "RETURNED"), produkty + ilości, user, cena

    ReturnedOrder: returnDate, refundAmount, reason

    Person (abstrakcyjna): login, email, password, dateOfBirth, age (pochodny), address

        User: balance, lista zamówień

        Admin: favouriteTheme, lista zamówień, zarządzanie zniżkami, kontami itd.

        SuperAdmin: seniority, rozszerzone uprawnienia

Przykładowe przypadki użycia

    Rejestracja/logowanie użytkownika

    Przeglądanie kategorii produktowych (Base, Wheel, Pedals)

    Składanie nowego zamówienia, wybór produktów i ilości

    Podgląd i zwracanie zamówień

    Zarządzanie rabatami (Admin, SuperAdmin)

    Usuwanie konta (SuperAdmin), zmiana maila

Najważniejsze cechy implementacyjne

    Serializacja — dane zapisywane do pliku (ObjectPlus, trwałość)

    Atrybuty złożone/opcjonalne/powtarzalne/pochodne/klasowe

    Polimorfizm i dziedziczenie, interfejsy

    Kompozycja — zamówienia sklepu istnieją tylko w kontekście Admina

    Kolekcje uporządkowane (ordered), subset (zamówienia użytkownika), relacje asocjacyjne

    Dynamiczne przekształcenie obiektów (Order → ReturnedOrder)

    Walidacja kluczowych atrybutów (wiek, statusy, ograniczenia dla enumów)

Schemat GUI (propozycja)

Panel logowania:

    Login

    Hasło

Widok kategorii:

text
Nazwa  | Liczba produktów
-------|-----------------
Base   | 4
Wheel  | 8
Pedals | 3

Lista produktów:

text
Nazwa      | Cena     | Stan | Ilość
-----------|----------|------|------
Moza r9    | 15.00 $  | 20   |
Moza r16   | 1400.00 $| 3    |
Moza r21   | 367.00 $ | 24   |

Podsumowanie zamówienia:

    Łączna kwota oraz liczba pozycji

Rozszerzenia i plany rozwoju

    Zaawansowana obsługa zamówień

    Rozbudowane zarządzanie użytkownikami i rabatami

    Dodatkowe raporty i panele administracyjne

Informacje techniczne

Projekt napisany w języku Java (przygotowany pod rozbudowę w przyszłości).
Zaimplementowana serializacja danych do pliku extent.ser. Diagramy klas i przypadków użycia dostępne w dokumentacji wewnętrznej.
