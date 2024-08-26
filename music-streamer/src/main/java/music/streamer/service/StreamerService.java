package music.streamer.service;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import music.streamer.controller.model.ArtistData;
import music.streamer.controller.model.ArtistData.AlbumData;
import music.streamer.controller.model.ArtistData.PlaylistData;
import music.streamer.dao.AlbumDao;
import music.streamer.dao.ArtistDao;
import music.streamer.dao.PlaylistDao;
import music.streamer.entity.Album;
import music.streamer.entity.Artist;
import music.streamer.entity.Playlist;

@Service
public class StreamerService {
	
	@Autowired
	private ArtistDao artistDao;
	
	// saves an artist and returns it 

	@Transactional(readOnly = false)
	public ArtistData saveArtist(ArtistData artistData) {
		Artist artist = artistData.toArtist();
		Artist dbArtist = artistDao.save(artist);
		
		return new ArtistData(dbArtist);
		
	}

	// retrieves an artist by Id and returns it
	
	@Transactional(readOnly = true)
	public ArtistData retrieveArtistById(Long artistId) {
		Artist artist = findArtistById(artistId);
		return new ArtistData(artist);
		
	}

	// finds an artist by Id 
	
	private Artist findArtistById(Long artistId) {
		return artistDao.findById(artistId)
				.orElseThrow(() -> new NoSuchElementException("Artist with Id= " + artistId + " was not found"));
	}

	// retrieves all artists and returns the list
	
	@Transactional(readOnly = true)
	public List<ArtistData> retrieveAllArtists() {
		
		// @formatter:off
		
		return artistDao.findAll().stream().map(ArtistData::new).toList();
		
		// @formatter:on
		
	
	}
	
	// deletes an artist by Id 

	@Transactional(readOnly = false)
	public void deleteArtist(Long artistId) {
		Artist artist = findArtistById(artistId);
		artistDao.delete(artist);
		
	}
	
	@Autowired
	
	private AlbumDao albumDao;
	
	// saves an album and returns it
	
	@Transactional(readOnly = false)
	public AlbumData saveAlbum(AlbumData albumData) {
		
		Album album = albumData.toAlbum();
		
		Artist artist = artistDao.findById(albumData.getArtistId()).orElseThrow(() -> new NoSuchElementException("artist with Id " + albumData.getArtistId() + "does not exist"));
		
		
		album.setArtist(artist);
		Album dbAlbum = albumDao.save(album);
		
		return new AlbumData(dbAlbum);
	}
	
	// retrieves album by Id and rturns it
	
	@Transactional(readOnly = false)
	public AlbumData retrieveAlbumById(Long albumId) {
		Album album = findAlbumById(albumId);
		 return new AlbumData(album);
	}
	
	
	
	public Album findAlbumById(Long albumId) {
		return albumDao.findById(albumId).orElseThrow(() -> new NoSuchElementException("album with Id =" + albumId + " not found"));
	
	}	
	
	// retrieves all albums
	
		@Transactional(readOnly= false)
		public List<AlbumData> retrieveAllAlbums() {
			
			// @formatter:off
			
			return albumDao.findAll().stream().map(AlbumData::new).toList();	
		// @formatter:on
	
}
	@Autowired
	
	private PlaylistDao playlistDao;
	
	// saves a playlist and returns it
	
	@Transactional(readOnly = false)
	public PlaylistData savePlaylist(PlaylistData playlistData) {
		Playlist playlist = playlistData.toPlaylist();
		Playlist dbPlaylist = playlistDao.save(playlist);
		
		return new PlaylistData(dbPlaylist);
	}
	
	@Transactional(readOnly = false)
	public PlaylistData retrievePlaylistById(Long playlistId) {
		Playlist playlist = findPlaylistById(playlistId);
		
		return new PlaylistData(playlist);
	}
	
	private Playlist findPlaylistById(Long playlistId) {
		return playlistDao.findById(playlistId).orElseThrow(() -> new NoSuchElementException("Playlist with Id =" + playlistId + " not found"));
	}

	// retrieves all playlists
	
@Transactional(readOnly = false)
	public List<PlaylistData> retrieveAllPlaylists() {
		
	// @formatter:off
	
	return playlistDao.findAll().stream().map(PlaylistData::new).toList();	}
	
// @formatter:on

}
