package music.streamer.entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Entity
@Data
public class Artist {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long artistId;
	private String name;
	private String genre;
	private String topSong;
	
	@OneToMany(mappedBy = "artist")
	private Set<Album> albums = new HashSet<>(); 

}
