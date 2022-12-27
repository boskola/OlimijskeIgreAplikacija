INSERT INTO drzava (id, naziv, oznaka) VALUES (1, 'Srbija', 'SRB');
INSERT INTO drzava (id, naziv, oznaka) VALUES (2, 'Argentina', 'ARG');
INSERT INTO drzava (id, naziv, oznaka) VALUES (3, 'Amerika', 'USA');
INSERT INTO drzava (id, naziv, oznaka) VALUES (4, 'Francuska', 'FRA');
INSERT INTO drzava (id, naziv, oznaka) VALUES (5, 'Spanija', 'ESP');

INSERT INTO takmicar (id, ime_prezime, broj_medalja, datum_rodjenja, drzava_id) VALUES (1, 'Novak Djokovic', 5, '1982-06-21', 1);
INSERT INTO takmicar (id, ime_prezime, broj_medalja, datum_rodjenja, drzava_id) VALUES (2, 'Janko Tipsarevic', 2, '1972-06-21', 1);
INSERT INTO takmicar (id, ime_prezime, broj_medalja, datum_rodjenja, drzava_id) VALUES (3, 'Kylian Mbappe', 1, '1998-06-21', 4);
INSERT INTO takmicar (id, ime_prezime, broj_medalja, datum_rodjenja, drzava_id) VALUES (4, 'Dusan Tadic', 10, '1987-06-21', 1);
INSERT INTO takmicar (id, ime_prezime, broj_medalja, datum_rodjenja, drzava_id) VALUES (5, 'Lionel Messi', 9, '1985-06-21', 2);
INSERT INTO takmicar (id, ime_prezime, broj_medalja, datum_rodjenja, drzava_id) VALUES (6, 'Michael Phelps', 11, '1982-06-21', 3);
INSERT INTO takmicar (id, ime_prezime, broj_medalja, datum_rodjenja, drzava_id) VALUES (7, 'Ricky Rubio', 30, '1992-06-21', 5);
INSERT INTO takmicar (id, ime_prezime, broj_medalja, datum_rodjenja, drzava_id) VALUES (8, 'Kobe Bryant', 40, '1982-06-21', 3);
INSERT INTO takmicar (id, ime_prezime, broj_medalja, datum_rodjenja, drzava_id) VALUES (9, 'Mikael Jordan', 40, '1975-06-21', 3);


INSERT INTO prijava (id, datum_prijave, disciplina, takmicar_id) VALUES (1, '2022-06-21', 'Tenis', 1);
INSERT INTO prijava (id, datum_prijave, disciplina, takmicar_id) VALUES (2, '2022-06-22', 'Fudbal', 2);
INSERT INTO prijava (id, datum_prijave, disciplina, takmicar_id) VALUES (3, '2022-06-23', 'Plivanje', 3);


INSERT INTO korisnik (id, e_mail, korisnicko_ime, lozinka, ime, prezime, uloga)
              VALUES (1,'miroslav@maildrop.cc','miroslav','$2y$12$NH2KM2BJaBl.ik90Z1YqAOjoPgSd0ns/bF.7WedMxZ54OhWQNNnh6','Miroslav','Simic','ADMIN');
INSERT INTO korisnik (id, e_mail, korisnicko_ime, lozinka, ime, prezime, uloga)
              VALUES (2,'tamara@maildrop.cc','tamara','$2y$12$DRhCpltZygkA7EZ2WeWIbewWBjLE0KYiUO.tHDUaJNMpsHxXEw9Ky','Tamara','Milosavljevic','KORISNIK');
INSERT INTO korisnik (id, e_mail, korisnicko_ime, lozinka, ime, prezime, uloga)
              VALUES (3,'petar@maildrop.cc','petar','$2y$12$i6/mU4w0HhG8RQRXHjNCa.tG2OwGSVXb0GYUnf8MZUdeadE4voHbC','Petar','Jovic','KORISNIK');