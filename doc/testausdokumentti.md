## Yksikk�testaus

Rivi- ja mutaatiokattavuus on muilla kuin Logic luokalla hyv�. Testit testaavat bugeja, joita koodaamisen aikana tuli esille ja bugeja joita ajattelin ett� voisi tulla esille.

Logicin mutaatiotestaus executeCommandeissa on hieman katastrofaalinen. Pit p�r�ytt�� muutaman kymmenen instanssia pelist� p��lle ja koska niiss� checkLoss() pist�� ruudulle valintadialogin jota ei paineta, testi aikakatkaisee itsens� v�h�n ajan p��st�. Pit menee kuitenkin l�pi, mutta en kirjoittanut enemp�� testej� t�h�n t�m�n takia.

Logicin metodeja, joissa on kutsuja graafiseen k�ytt�liittym��n ei ole testattu, koska pit antoi muutaman sata rivi� virheilmoituksia, joita en jaksanu alkaa miettim��n. Ehk� tein jotain vain v��rin..

Kaikki perustoiminnallisuudet on kuitenkin testattu kattavasti my�s Logic luokassa, kuten muissakin luokissa.

## Manuaalinen testaus

Koska Logicin testaus on hieman vajaa, on peli� testattu kattavasti sit� pelaamalla. Peli on melko yksinkertainen (k�yt�nn�ss� kaksi eri toimintoa (liikkuminen eri suuntiin ja uusi peli), joten manuaalisella testauksella on luultavasti saavutettu kohtalaisen hyv� tulos. Bugeja ei ole tullut vastaan.

