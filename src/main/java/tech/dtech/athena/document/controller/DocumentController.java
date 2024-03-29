package tech.dtech.athena.document.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;
import tech.dtech.athena.document.model.DocumentType;
import tech.dtech.athena.document.model.DocumentTypeDTO;
import tech.dtech.athena.document.model.DocumentTypeForm;
import tech.dtech.athena.document.usecase.DocumentTypeService;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/documents")
public class DocumentController {

    @Autowired
    private DocumentTypeService documentTypeService;

    @GetMapping(path = "/types")
    public ResponseEntity<List<DocumentTypeDTO>> get() {
        List<DocumentType> documentTypes = documentTypeService.getAll();

        return ResponseEntity.ok(DocumentTypeDTO.from(documentTypes));
    }

    @GetMapping(path = "/types/{id}")
    public ResponseEntity<DocumentTypeDTO> get(@PathVariable long id) {
        return ResponseEntity.ok(new DocumentTypeDTO(documentTypeService.get(id)));
    }

    @PostMapping(path = "/types")
    public ResponseEntity<DocumentTypeDTO> create(@RequestBody @Valid DocumentTypeForm form, UriComponentsBuilder uriBuilder) {
        DocumentType documentType = documentTypeService.createNew(form.transform());
        URI uri = uriBuilder.path("documents/types/{id}").buildAndExpand(documentType.getId()).toUri();

        return ResponseEntity.created(uri).body(new DocumentTypeDTO(documentType));
    }

    @PutMapping(path = "/types/{id}")
    public ResponseEntity<DocumentTypeDTO> update(@PathVariable long id, @RequestBody @Valid DocumentTypeForm form) {
        DocumentType documentType = documentTypeService.update(id, form.transform());

        return ResponseEntity.ok(new DocumentTypeDTO(documentType));
    }

    @DeleteMapping(path = "/types/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id) {
        documentTypeService.delete(id);

        return ResponseEntity.noContent().build();
    }
}
