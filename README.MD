## Roland Szarka 

## FIITNotes 

Program bude slúžiť na jednoduché zdieľanie/prístup k spoľahlivým študijným materiálom. Prihlásiť sa budeme môcť buď ako študent alebo ako učiteľ. Po prihlásení sa ako študent uvidíme zoznam predmetov vo forme tlačidiel (dlaždíc). Na boku okna budú „obľúbené“ alebo „sledované“ predmety a v strede okna všetky ostatné. Po kliknutí na nejaký predmet uvidíme zoznam dostupných materiálov ako napríklad poznámky z prednášky číslo 2. Každý súbor bude mať vlastné komentáre kde sa môžu študenti vyjadriť k spoľahlivosti informácií alebo diskutovať o učive.
Po prihlásení sa ako učiteľ budeme mať možnosť vytvoriť nový predmet a taktiež prístup k predmetom ktoré sme už vytvorili. Pri už existujúcich predmetoch bude učiteľ môcť označiť súbory ako „dobré“ alebo „zlé-neúplné“ taktiež bude mať možnosť nevhodné súbory vymazať alebo sám pridať nejaké materiály ktoré budú špeciálne označené. Učiteľ bude môcť pri komentároch odpovedať na prípadné otázky , pričom jeho správy budú označené aby študenti videli na prvý pohlaď že to je učiteľ.

## Odovzdávanie II 

* Dedenie - Instructor a Student dedí z triedy User

* Agregácia - SubjectHandler agreguje objekty triedy Subject 

* Polymorfizmus - Userhandler obsahuje objekt currentUser typu User kde je uložený Student alebo Instructor. Následne sa na currentUser zavolá metóda applyPrivileges() ktorá vykoná rozne zmeny podla typu usera

* Oddelená logika - logika riežená cez rozličné handlery a GUIController 

## Odovzdávanie III
 * Dedenie a polymorfizmus - 1. hierarchia ako pri odovzdávaní II 2. hierarchhia ri tiredach RegularComment a PriviledgedComment. Polymorfizmus pri metode applyPriviedges() . 
  
  * Agregácia , oddelená logika - rovnako ako pri odovzdávaní II 
  
  * zapuzdrenie - atribúty su private , pristupovanie k nim iba cez metody 
  
  * Vyuzity observer (odprezentované na cvičení) - zoznam listenerov v Subject handler , listenery sú vytvorené 
  pomocou interfaceov, nastavované v triede GUIController kde sa pridávajú nové cez subject handler 
  
  * Vlastné výnimky (odprezentované na cvičení) - IllegalArgException 
  
  * GUI, vlastný handler (odprezentované na cvičení) loginGUI, mainGUI triedy. Vlastny handler udalostí pri observeri kde 
  sa spracuje udalosť pridania nového predmetu. 
  
  * vhniezdené triedy a rozhrania (odprezentované na cvičení) - bniezdená trieda v triedne GUIController, vniezdený 
  interface v NewSubjectListener interface.
  
  * lambda výrazy (odprezentované na cvičení) - pri setOnAction metodach a pri observeri v GUIController triede kde sa metode OnNewSubject odovzdáva blok kodu cez lambda vyraz 
  
  
