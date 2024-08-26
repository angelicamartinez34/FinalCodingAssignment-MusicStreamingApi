package music.streamer.entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import lombok.Data;

@Entity
@Data
public class Playlist {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long playlistId;
	private String name;
	private String description;
	private String createdAt;
	
	@ManyToMany
	@JoinTable(name = "playlist_albums", joinColumns = @JoinColumn(name = "playlist_id"),
	inverseJoinColumns = @JoinColumn(name = "album_id"))
	private Set<Album> albums = new HashSet<>();

}
