# MyBreedMobileNet
Din tutorialul asta:https://colab.research.google.com/drive/1kd0WNKrnZnwQ31NjF_BAoAvqLd-vy_ff#scrollTo=4AvcUNlXQovL

Am facut reteaua si am antrenat-o, sunt mai multe retele, dar aia care merge e cea de MobileNetV2. Nu mai trebuie sa faci si tu, 
fiindca am pus-o deja in proiect.

E in folderul de assets, model.tflite. Label-urile pentru ea sunt in fisierul labels_doggo.txt
Aplicatia practic iti da o predictie, care are un index, si iti mapeaza indexul ala, cu un nume din labels_doggo.txt, care are nume de rase.
Am schimbat ByteBufferul si intrare de la retea, fiindca la el lucra cu byte, la noi cu float.

Din testele mele, predictia e in majoritatea cazurilor buna, adica in array-ul unde sunt predictile(il vezi la debug), valoarea cea mai mare
e la rasa care trebuie. Doar ca nu se afiseaza cum trebuie, problema e la functia getSortedResult, ca nu am putut sa o folosesc pe aia a lui
ca reteaua noastra outputuie float nu byte ca la el, si ceva se calculeaza prost dar nu stiu ce inca.

Cu ultimul commit, avem o lista in HistoryActivity(RecyclerViewAdapter) in care se salveaza un fel de istoric al recognitiilor.
Avem o baza de date pe local, exact ca in laborator.
Avem toate pozele incarcate(de la fiecare rasa cate una), care sa fie salvata in lista cand recunosti rasa respectiva.
Am schimbat culoarea in albastru, ca arata mai frumusel, am pus alta iconita de buton, in HistoryActivity, as pune alta si in CaptureActivity dar nu stiu inca ce iconita.

Avem un bug, cand se deschide aplicatia, nu poti sa faci direct poza, trebuie sa apesi intai pe buton, ca sa se porneasca si a doua activitate, altfel se inchide.
