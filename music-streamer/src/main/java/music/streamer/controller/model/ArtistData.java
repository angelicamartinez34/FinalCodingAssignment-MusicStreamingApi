package music.streamer.controller.model;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import lombok.Data;
import lombok.NoArgsConstructor;
import music.streamer.entity.Album;
import music.streamer.entity.Artist;
import music.streamer.entity.Playlist;

@Data
@NoArgsConstructor
public class ArtistData {
	
	// added fields for an artist 

	Long artistId;
	String name;
	String genre;
	String topSong;
	Set<AlbumData> albums = new HashSet<>(); 
	
	// an artist has a specific id, a name, the genre of music, an artist top song and list of albums 
	
	public ArtistData(Artist artist) {
		this.artistId = artist.getArtistId();
		this.name = artist.getName();
		this.genre = artist.getGenre();
		this.topSong = artist.getTopSong();
		
		for(Album album : artist.getAlbums()) {
			this.albums.add(new AlbumData(album));
		}
	}
	
	public ArtistData(Long artistId, String name, String genre, String topSong) {
		this.artistId = artistId;
		this.name = name;
		this.genre = genre;
		this.topSong = topSong;
	}
	
	public Artist toArtist() {
		Artist artist = new Artist();
		
		artist.setArtistId(artistId);
		artist.setName(name);
		artist.setGenre(genre);
		artist.setTopSong(topSong);
	
		
		return artist;
		
		// this converts the DTO to an artist entity
	}
	
	@Data
	@NoArgsConstructor
	public static class AlbumData {
		private Long albumId;
		private String title;
		private String releaseDate;
		private Long artistId;
		private Set<PlaylistData> playlists = new HashSet<>();

		public AlbumData(Album album) {
			this.albumId = album.getAlbumId();
			this.title = album.getTitle();
			this.releaseDate = album.getReleaseDate();
			this.artistId = album.getArtist().getArtistId();
			
			this.playlists = album.getPlaylists().stream().map(PlaylistData::new).collect(Collectors.toSet());
		
			// an album has a specific id, a title, a release date, an artist associated with the album, and can be in a playlist
		}
		
		public Album toAlbum() {
			Album album = new Album();
			
			album.setAlbumId(albumId);
			album.setTitle(title);
			album.setReleaseDate(releaseDate);
			
		    return album;
		}
		
	}
	
	@Data
	@NoArgsConstructor
	public static class PlaylistData {
		private Long playlistId;
		private String name;
		private String description;
		private String createdAt;
		
		public PlaylistData(Playlist playlist) {
			this.playlistId = playlist.getPlaylistId();
			this.name = playlist.getName();
			this.description = playlist.getDescription();
			this.createdAt = playlist.getCreatedAt();
		}
		
		// a playlist has a specific id, a name, the description of the playlist, and a date when it was created
		
		public Playlist toPlaylist() {
			Playlist playlist = new Playlist();
			
			playlist.setPlaylistId(playlistId);
			playlist.setName(name);
			playlist.setDescription(description);
			playlist.setCreatedAt(createdAt);
			
			
			return playlist;
		}
	}

}
