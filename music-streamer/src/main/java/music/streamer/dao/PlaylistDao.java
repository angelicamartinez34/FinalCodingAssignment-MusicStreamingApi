package music.streamer.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import music.streamer.entity.Playlist;

public interface PlaylistDao extends JpaRepository<Playlist, Long> {

}
