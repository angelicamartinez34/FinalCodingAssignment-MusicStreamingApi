package music.streamer.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import music.streamer.controller.model.ArtistData;
import music.streamer.controller.model.ArtistData.AlbumData;
import music.streamer.controller.model.ArtistData.PlaylistData;
import music.streamer.service.StreamerService;

@RestController
@RequestMapping("/music_streamer")
@Slf4j
public class StreamerController {
	@Autowired
	private StreamerService streamerService;
	
	// methhod to create an artist
	
	@PostMapping("/artist")
	@ResponseStatus(code = HttpStatus.CREATED)
	public ArtistData createArtist(@RequestBody ArtistData artistData) {
		log.info("creating artist {}", artistData); 
		// log the request
		return streamerService.saveArtist(artistData);
		//calls the service to save the artist
	}
	
	// method to retrieve an artist by Id
	
	@GetMapping("/artist/{artistId}")
	public ArtistData retrieveArtist(@PathVariable Long artistId) {
		log.info("retrieving artist with Id= {}", artistId);
		// log the request
		return streamerService.retrieveArtistById(artistId);
		// calls the service to retrieve the artist
		
	}
	
	// method to retrieve all artists
	
	@GetMapping("/artists")
	public List<ArtistData> retrieveAllArtists() {
		log.info("retrieving all artists");
		return streamerService.retrieveAllArtists();
		
	}
	
	// method to update an existing artist

	@PutMapping("/artist/{artistId}")
	public ArtistData updateArtist(@PathVariable Long artistId, @RequestBody ArtistData artistData) {
		artistData.setArtistId(artistId);
		log.info("updating artist {}", artistData);
		return streamerService.saveArtist(artistData);
		
	}
	
	// method to delete an artist by Id
	
	@DeleteMapping("/artist/{artistId}")
	public Map<String, String> deleteArtist(@PathVariable Long artistId) {
		log.info("deleting artist with Id= " + artistId + ".");
		streamerService.deleteArtist(artistId);
		
		return Map.of("message", "artist with Id= " + artistId + " was deleted successfully");		
	}
	
	// method to create a new album

	@PostMapping("/album")
	@ResponseStatus(code = HttpStatus.CREATED)
	public AlbumData createAlbum(@RequestBody AlbumData albumData) {
		log.info("creating album {}", albumData);
		return streamerService.saveAlbum(albumData);
	}
	
	// method to retrieve all albums
	
	@GetMapping("/albums")
	public List<AlbumData> retrieveAllAlbums() {
		log.info("retrieving all albums..");
		return streamerService.retrieveAllAlbums();
	}
	
	// method to create a new playlist 
	
	@PostMapping("/playlist")
	@ResponseStatus(code = HttpStatus.CREATED)
	public PlaylistData createPlaylist(@RequestBody PlaylistData playlistData) {
		log.info("creating playlist {}", playlistData);
		return streamerService.savePlaylist(playlistData);
	}
	
	@GetMapping("/playlists")
	public List<PlaylistData> retrieveAllPlaylists() {
		log.info("retrieving all playlists");
		return streamerService.retrieveAllPlaylists();
	}
	
	
	
	
}
