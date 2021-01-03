package tech.dtech.athena.document.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tech.dtech.athena.document.model.DocumentType;

@Repository
public interface DocumentTypeRepository extends CrudRepository<DocumentType, Long> {

}
