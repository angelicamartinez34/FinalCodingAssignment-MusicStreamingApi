DROP TABLE IF EXISTS playlist_album;
DROP TABLE IF EXISTS playlist;
DROP TABLE IF EXISTS album;
DROP TABLE IF EXISTS artist;

CREATE TABLE artist (
artist_id int NOT NULL AUTO_INCREMENT,
name varchar(200) NOT NULL,
genre varchar(150) NOT NULL,
top_song varchar(300) NOT NULL,
PRIMARY KEY (artist_id)
);

CREATE TABLE album (
album_id int NOT NULL AUTO_INCREMENT,
artist_id int NOT NULL,
title varchar(150) NOT NULL,
release_date DATE NOT NULL,
PRIMARY KEY (album_id),
FOREIGN KEY (artist_id) REFERENCES artist (artist_id) ON DELETE CASCADE
);

CREATE TABLE playlist (
playlist_id int NOT NULL AUTO_INCREMENT,
name varchar(150) NOT NULL,
description varchar(200) NOT NULL,
created_at varchar(200) NOT NULL,
PRIMARY KEY (playlist_id)

);

CREATE TABLE playlist_album (
playlist_id int NOT NULL,
album_id int NOT NULL,
FOREIGN KEY (playlist_id) REFERENCES playlist (playlist_id) ON DELETE CASCADE,
FOREIGN KEY (album_id) REFERENCES album (album_id) ON DELETE CASCADE
);
