package music.streamer.entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class Album {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long albumId;
	private String title;
	private String releaseDate;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "artist_id", nullable = false)
	private Artist artist;
	
	
	@ManyToMany
	@JoinTable(name = "playlist_albums", joinColumns = @JoinColumn(name = "album_id"),
	inverseJoinColumns = @JoinColumn(name = "playlist_id"))
	private Set<Playlist> playlists = new HashSet<>();;

}
