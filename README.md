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

Deci asta cu afisatul nu e bine si sa facem un layout ca sa nu arate aplicatia fix ca la el si e gata
