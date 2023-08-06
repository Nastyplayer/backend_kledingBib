package KledingBib.demo.controller;


import KledingBib.demo.dto.ItemDto;
import KledingBib.demo.dto.UploadWithItemDto;
import KledingBib.demo.models.Upload;
import KledingBib.demo.service.ItemService;
import KledingBib.demo.service.UploadService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
@CrossOrigin
@RestController

public class UploadController {
    private final UploadService service;
    private final ItemService itemService;
    private String id;


    public UploadController(UploadService service, ItemService itemService ) {
        this.itemService = itemService;
        this.service = service;
    }


    @GetMapping("/download/{fileName}")
    ResponseEntity<Resource> DownLoadFile(@PathVariable HttpServletRequest request) {     //String id,
        Resource resource = service.downLoadFile(id);      //(fileName);

        String mimeType;

        try {
            mimeType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException e) {
            mimeType = MediaType.APPLICATION_OCTET_STREAM_VALUE;
        }
        return ResponseEntity.ok().contentType(MediaType.parseMediaType(mimeType)).
                header(HttpHeaders.CONTENT_DISPOSITION, "inline;fileName=" + resource.getFilename()).body(resource);
    }

    @PostMapping("/upload")

    Upload FileUpload(@RequestParam("upload") MultipartFile upload) {
        // Hier  url. example "http://localhost:8083/download/naam.jpg"


        String url = ServletUriComponentsBuilder.fromCurrentContextPath().path("/download/").
                path(Objects.requireNonNull(upload.getOriginalFilename())).toUriString();  //old!!

        String textType = upload.getOriginalFilename();
        String fileName = service.storeFile(upload, url);


        return new Upload(fileName, textType, url);
    }




    // Nieuwe methode: download alle bestanden
//    @GetMapping("/downloadAllFiles")
//    public ResponseEntity<List<byte[]>> downloadAllFiles() {
//        List<byte[]> files = service.getAllFiles();
//
//
//        return ResponseEntity.ok()
//                .body(files);
//    }

    /////////////nuevo
    @GetMapping("/files")
    public ResponseEntity<List<String>> getFiles() {
        List<String> files = service.getFilesFromUploadDirectory();
        return ResponseEntity.ok().body(files);
    }



    @GetMapping("/downloadAllFiles")
    public ResponseEntity<List<UploadWithItemDto>> downloadAllFiles() {
        List<UploadWithItemDto> filesWithItems = new ArrayList<>();

        List<byte[]> files = service.getAllFiles();
        List<ItemDto> items = itemService.getAllItems();

        // Controleer of het aantal bestanden en items overeenkomt
        if (files.size() != items.size()) {
            // Behandel de foutmelding of gooi een exception als het aantal niet overeenkomt
        }

        // Combineer de bestanden en items in de FileWithItemDto
        for (int i = 0; i < files.size(); i++) {
            UploadWithItemDto UploadWithItemDto = new UploadWithItemDto();
            UploadWithItemDto.setFile(files.get(i));
            UploadWithItemDto.setItem(items.get(i));
            filesWithItems.add(UploadWithItemDto);
        }

        return ResponseEntity.ok().body(filesWithItems);
    }

}




//////////////////////////////////////////////////////////////////////////////
//<byte[]><byte[]>
//
//    @GetMapping("/downloadAllFiles")
//    public ResponseEntity<List<string>> downloadAllFiles() {
//
//        List<string> files = service.getDownload();
//
//        return ResponseEntity.ok()
//                .body(files);
//    }


//@PostMapping("/upload")
//  Upload singleFileUpload(@RequestParam("file") MultipartFile file){
//
//        // next line makes url. example "http://localhost:8080/download/naam.jpg"
//        String url = ServletUriComponentsBuilder.fromCurrentContextPath().path("/download/").
//                path(Objects.requireNonNull(file.getOriginalFilename())).toUriString();
//
//        String contentType = file.getContentType();
//
//        String fileName = service.storeFile(file, url);
//
//        return new Upload(fileName, contentType, url );
//    }

