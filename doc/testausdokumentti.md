## Yksikkötestaus

Rivi- ja mutaatiokattavuus on muilla kuin Logic luokalla hyvä. Testit testaavat bugeja, joita koodaamisen aikana tuli esille ja bugeja joita ajattelin että voisi tulla esille.

Logicin mutaatiotestaus executeCommandeissa on hieman katastrofaalinen. Pit päräyttää muutaman kymmenen instanssia pelistä päälle ja koska niissä checkLoss() pistää ruudulle valintadialogin jota ei paineta, testi aikakatkaisee itsensä vähän ajan päästä. Pit menee kuitenkin läpi, mutta en kirjoittanut enempää testejä tähän tämän takia.

Logicin metodeja, joissa on kutsuja graafiseen käyttöliittymään ei ole testattu, koska pit antoi muutaman sata riviä virheilmoituksia, joita en jaksanu alkaa miettimään. Ehkä tein jotain vain väärin..

Kaikki perustoiminnallisuudet on kuitenkin testattu kattavasti myös Logic luokassa, kuten muissakin luokissa.

## Manuaalinen testaus

Koska Logicin testaus on hieman vajaa, on peliä testattu kattavasti sitä pelaamalla. Peli on melko yksinkertainen (käytännössä kaksi eri toimintoa (liikkuminen eri suuntiin ja uusi peli), joten manuaalisella testauksella on luultavasti saavutettu kohtalaisen hyvä tulos. Bugeja ei ole tullut vastaan.

