# ProgettoProgrammazioneOggetti
Progetto Programmazione ad oggetti Giuseppe Costantini Davide Vitaletti appello Giugno AA 2018/2019

## Descrizione

L'applicazione realizzata permette all'utente di effetturare interrogazioni su un dataset dato. In particolare, all'avvio, verrà scaricato
il file JSON che contiene l'URL attraverso cui accedere al dataset in formato CSV. Nel nostro caso, il dataset riguardava un catalogo di
buone pratiche culturali nella regioen Lazio. L'applicazione, dopo aver salvato questo file nella cartella apposita, effettua il parsing 
dei dati, creando gli oggetti delle classi create in JAVA. In modo specifico, per ogni riga vengono salvate le informazioni del titolo 
della pratica culturale, il numero ad essa associato, l'ente proponente, il sito internet di questo, la provincia, il comune e la lista 
degli enti partner. Se durante il parsing ci dovesse essere un errore di formattazione su una riga, il codice salva il tipo di errore e
passa alla riga successiva. 

Terminata questa fase, l'utente può effettuare delle richieste sul dataset utilizzando Postman. Le interrogazioni sono state implementate 
tramite API di tipo REST. innanzitutto, l'utente può visualizzare tutti i dati attraverso la richiesta "/data". Invece, scrivendo
"/metadata", verranno visualizzati i metadati relativi agli attributi del dataset. Di seguito, attraverso la richiesta "/countUnique" e
specificando un attributo, è possibile visualizzare per ogni elemento di quell'attributo quante volte è ripetuto all'interno del dataset.


## Diagramma delle classi

lol

## Diagramma delle sequenze
