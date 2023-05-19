# Házi feladat specifikáció

Információk [itt](https://viauac00.github.io/laborok/hf)

## Mobil- és webes szoftverek
### [2022.10.14]
### [A Raktár kezelő alkalmazás]
### [Huang Yixin] - ([XLAAPJ])
### [petica0224@gmail.com] 
### Laborvezető: [Pomázi Krisztián Dániel]

## Bemutatás
 Egy raktárfeldolgozó szoftvert csinálok otthoni használatra,
amihez különféle típusú terméket tudunk hozzáadni/törölni, megtekinthetjük a mennyiségét és részleteit(a mennyiséget tudjuk változtatni is)
valamint egy chartot, amely megszámolja a különböző típusú dolgok arányát a raktárban


## Főbb funkciók
Az alkalmazás két Activity-ből és egy dialógusból áll. Az első Activity a raktárban lévő terméket jeleníti meg,
a második egy chartot jelenít meg,  amely megszámolja a különböző típusú dolgok arányát a raktárban.
Belépünk erre az alkalmazásra, akkor a jobb alsó sarokban található két Button.
Az első Button kattintással megjelenik egy dialógus ,és ezzel a segítségével tudunk új terméket hozzáadni.
Meg kell adni egy termék  nevét, mennyiséget,típust,részletet.
A második Button segítségével megyünk át a chart képernyőre.
Az alkalmazás a termékek listáját RecyclerView-ben jeleníti meg,
Egy terméknek mennyiséget tudjuk változtatni "+" és "-" gombbal (minden termék mellett van ez a két gomb).
A létrehozott terméket törölni is tudjuk a termék melletti gombbal.
A lista elemeket és azok állapotát a Room nevű ORM library segítségével tárolja perzisztensen.

## Választott technológiák:

- (Activity)
- (fragmentek)
- (RecyclerView)
- (Perzisztens adattárolás)
- (Chartok)


