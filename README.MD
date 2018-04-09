#FIITNotes 

Program bude slúi na jednoduché zdie¾anie/prístup k spo¾ahlivım študijnım materiálom. Prihlási sa budeme môc buï ako študent alebo ako uèite¾. Po prihlásení sa ako študent uvidíme zoznam predmetov vo forme tlaèidiel (dladíc). Na boku okna budú „ob¾úbené“ alebo „sledované“ predmety a v strede okna všetky ostatné. Po kliknutí na nejakı predmet uvidíme zoznam dostupnıch materiálov ako napríklad poznámky z prednášky èíslo 2. Kadı súbor bude ma vlastné komentáre kde sa môu študenti vyjadri k spo¾ahlivosti informácií alebo diskutova o uèive.
Po prihlásení sa ako uèite¾ budeme ma monos vytvori novı predmet a taktie prístup k predmetom ktoré sme u vytvorili. Pri u existujúcich predmetoch bude uèite¾ môc oznaèi súbory ako „dobré“ alebo „zlé-neúplné“ taktie bude ma monos nevhodné súbory vymaza alebo sám prida nejaké materiály ktoré budú špeciálne oznaèené. Uèite¾ bude môc pri komentároch odpoveda na prípadné otázky , prièom jeho správy budú oznaèené aby študenti videli na prvı pohlaï e to je uèite¾.

## Odovzdávanie II 

* Dedenie - Instructor a Student dedí z triedy User

* Agregácia - SubjectHandler agreguje objekty triedy Subject 

* Polymorfizmus - Userhandler obsahuje objekt currentUser typu User kde je uloenı Student alebo Instructor. Následne sa na currentUser zavolá metóda applyPrivileges() ktorá vykoná rozne zmeny podla typu usera

* Oddelená logika - logika rieená cez rozlièné handlery a GUIController 