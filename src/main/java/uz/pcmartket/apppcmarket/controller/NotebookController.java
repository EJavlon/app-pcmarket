package uz.pcmartket.apppcmarket.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pcmartket.apppcmarket.entity.Notebook;
import uz.pcmartket.apppcmarket.payload.ApiResponse;
import uz.pcmartket.apppcmarket.payload.MonoblocDto;
import uz.pcmartket.apppcmarket.service.NotebookService;

import java.util.List;

@RestController
@RequestMapping("/api/category/notebook")
public class NotebookController {
    @Autowired
    private NotebookService notebookService;

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> getNotebook(@PathVariable Integer id){
        ApiResponse apiResponse = notebookService.getNotebook(id);
        return ResponseEntity.status(apiResponse.getSuccess()?200:409).body(apiResponse);
    }

    @GetMapping
    public ResponseEntity<List<Notebook>> getNotebooks(){
        return ResponseEntity.status(notebookService.getNotebooks() != null ? 200 : 409).body(notebookService.getNotebooks());
    }

    @PostMapping
    public ResponseEntity<ApiResponse> addNotebook(@RequestBody MonoblocDto monoblocDto){
        ApiResponse apiResponse = notebookService.addNotebook(monoblocDto);
        return ResponseEntity.status(apiResponse.getSuccess() ? HttpStatus.CREATED : HttpStatus.CONFLICT).body(apiResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> editNotebook(@PathVariable Integer id, @RequestBody MonoblocDto monoblocDto){
        ApiResponse apiResponse = notebookService.editNotebook(id,monoblocDto);
        return ResponseEntity.status(apiResponse.getSuccess() ? HttpStatus.ACCEPTED : HttpStatus.CONFLICT).body(apiResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteNotebook(@PathVariable Integer id){
        ApiResponse apiResponse = notebookService.deleteNotebook(id);
        return ResponseEntity.status(apiResponse.getSuccess() ? HttpStatus.NO_CONTENT : HttpStatus.CONFLICT).body(apiResponse);
    }
}
