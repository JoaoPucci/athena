package tech.dtech.athena.document.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tech.dtech.athena.document.model.DocumentType;
import tech.dtech.athena.document.model.DocumentTypeDTO;
import tech.dtech.athena.document.usecase.GetDocumentTypesUseCase;

@RestController
@RequestMapping("/documents")
public class DocumentController {

    @Autowired
    private GetDocumentTypesUseCase useCase;

    @GetMapping(path = "/types")
    public ResponseEntity<List<DocumentTypeDTO>> get() {
        List<DocumentType> documentTypes = useCase.execute();

        return ResponseEntity.ok(DocumentTypeDTO.from(documentTypes));
    }
}
