package music.streamer.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import music.streamer.entity.Album;

public interface AlbumDao extends JpaRepository<Album, Long> {

}
