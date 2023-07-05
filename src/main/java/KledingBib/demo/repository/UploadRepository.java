package KledingBib.demo.repository;


import KledingBib.demo.models.Upload;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UploadRepository extends JpaRepository<Upload, String> {
    Optional<Upload> findByFileName(String fileName);
}
