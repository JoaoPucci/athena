package tech.dtech.athena.document.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import tech.dtech.athena.document.model.DocumentType;

import java.util.Optional;

@Repository
public interface DocumentTypeRepository extends CrudRepository<DocumentType, Long> {

    Optional<DocumentType> findByName(String document);
}
