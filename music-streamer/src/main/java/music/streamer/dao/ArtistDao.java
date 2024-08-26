package music.streamer.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import music.streamer.entity.Artist;

public interface ArtistDao extends JpaRepository<Artist, Long> {

}
