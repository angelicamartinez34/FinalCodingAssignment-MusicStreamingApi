package music.streamer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.jdbc.JdbcTestUtils;

import music.streamer.controller.model.ArtistData;
import music.streamer.entity.Artist;

public class StreamerServiceTestSupport {
	
	private static final String ARTIST_TABLE = "artist";
	
	// @formatter:off
	private ArtistData insertGenre1 = new ArtistData(
			1L,
			"Luke Bryan",
			"Country",
			"Play It Again"
			);
	
	private ArtistData insertGenre2 = new ArtistData(
			2L,
			"The Judds",
			"Country",
			"Why Not Me"
			);
	
	// @formatter:on
	
	@Autowired
	protected JdbcTemplate jdbcTemplate;
	
	@Autowired
	private StreamerController streamerController;

	protected ArtistData buildInsertArtist(int which) {
		
		return which == 1 ? insertGenre1 : insertGenre2;
	}


protected int rowsInArtistTable() {
	
	return JdbcTestUtils.countRowsInTable(jdbcTemplate, ARTIST_TABLE);
}

protected ArtistData insertArtist(ArtistData artistData) {
	Artist artist = artistData.toArtist();
	ArtistData clone = new ArtistData(artist);
	
	clone.setArtistId(null);
	return streamerController.createArtist(clone);
	
}
protected ArtistData retrieveArtist(Long artistId) {
	
	return streamerController.retrieveArtist(artistId);
}
}
