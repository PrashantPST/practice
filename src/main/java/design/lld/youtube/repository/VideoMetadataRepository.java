package design.lld.youtube.repository;


import design.lld.youtube.model.VideoMetadata;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface VideoMetadataRepository extends JpaRepository<VideoMetadata, String> {
  VideoMetadata getVideoMetadata(String videoId);
}

