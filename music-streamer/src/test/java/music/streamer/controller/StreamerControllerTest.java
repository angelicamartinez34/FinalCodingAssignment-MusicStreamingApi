package music.streamer.controller;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.jdbc.JdbcTestUtils;

import music.streamer.MusicStreamingApplication;
import music.streamer.controller.model.ArtistData;

@SpringBootTest(webEnvironment = WebEnvironment.NONE,
classes = MusicStreamingApplication.class)
@ActiveProfiles("test")
@Sql(scripts = {"classpath:schema.sql", "classpath:data.sql"})
@SqlConfig(encoding = "utf-8")
class StreamerControllerTest extends StreamerServiceTestSupport {

	@BeforeEach
	void setUp() {
		JdbcTestUtils.deleteFromTables(jdbcTemplate, "artist");
			
	}

	@Test
	void testInsertArtist() {
		// Given: An artist request
		ArtistData request = buildInsertArtist(1);
		ArtistData expected = buildInsertArtist(1);
		System.out.println(request);
		
		// When: the artist is added to the artist table
		ArtistData actual = insertArtist(request);
		
		// Then: the artist returned is what is excpected, excluding auto-generated id
		assertThat(actual).usingRecursiveComparison().ignoringFields("artistId").isEqualTo(expected);
		
		// And: there is one row in the artist table.
		assertThat(rowsInArtistTable()).isOne();
	}

	

	@Test
	void testInsertMultipleArtists() {
	    // Given: A list of artist requests
	    ArtistData request1 = buildInsertArtist(1);
	    ArtistData request2 = buildInsertArtist(2);
	    
	    ArtistData expected1 = buildInsertArtist(1);
	    ArtistData expected2 = buildInsertArtist(2);
	    
	    // When: The artists are added to the artist table
	    ArtistData actual1 = insertArtist(request1);
	    ArtistData actual2 = insertArtist(request2);
	    
	    // Then: The artists returned are what is expected, excludin auto-generated Ids
	    assertThat(actual1)
	        .usingRecursiveComparison()
	        .ignoringFields("artistId")
	        .isEqualTo(expected1);
	    
	    assertThat(actual2)
	        .usingRecursiveComparison()
	        .ignoringFields("artistId")
	        .isEqualTo(expected2);
	    
	    // And: There are two rows in the artist table
	    assertThat(rowsInArtistTable()).isEqualTo(2);
	}
	
	@Test
	void testRetrieveArtist() {
		// Given: an artist 
		ArtistData insertedArtist = insertArtist(buildInsertArtist(1));
		ArtistData expected = buildInsertArtist(1);
		
		// When: the artist is retrieved by artist Id
		ArtistData actual = retrieveArtist(insertedArtist.getArtistId());
		
		// Then: the artist is equal to the expected artist
		assertThat(actual).usingRecursiveComparison().ignoringFields("artistId").isEqualTo(expected);
	}

	


	
	
}
