package KledingBib.demo.service;


import KledingBib.demo.models.Upload;
import KledingBib.demo.repository.UploadRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;


@Service
public class UploadService {

    //    The next line is also possible instead of line 14 and 15
//    private static String storageLocation = "/Users/vanoo/IdeaProjects/upload-download/uploads"
    @Value("${my.upload_location}")
    private Path fileStoragePath;
    private final String fileStorageLocation;
    private final UploadRepository repository;

    public UploadService(@Value("${my.upload_location}") String fileStorageLocation, UploadRepository repository) {
        fileStoragePath = Paths.get(fileStorageLocation).toAbsolutePath().normalize();

        this.fileStorageLocation = fileStorageLocation;
        this.repository = repository;

        try {
            Files.createDirectories(fileStoragePath);
        } catch (IOException e) {
            throw new RuntimeException("Issue in creating file directory");
        }

    }

    public String storeFile(MultipartFile file, String url) {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        try {
            // Controleer of de bestandsnaam veilig is
            if (fileName.contains("..")) {
                throw new IllegalArgumentException("Ongeldige bestandsnaam: " + fileName);
            }

            // Kopieer het bestand naar de upload directory
            Path targetLocation = fileStoragePath.resolve(fileName);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

            return fileName;
        } catch (IOException e) {
            throw new RuntimeException("Kon het bestand niet opslaan: " + fileName, e);
        }
    }

    public Resource downLoadFile(String fileName) {
        try {
            Path filePath = fileStoragePath.resolve(fileName).normalize();
            Resource resource = new UrlResource(filePath.toUri());

            if (resource.exists()) {
                return resource;
            } else {
                throw new RuntimeException("Bestand niet gevonden: " + fileName);
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException("Bestand niet gevonden: " + fileName, e);
        }
    }


    public List<byte[]> getAllFiles() {
        List<byte[]> files = new ArrayList<>();
        List<Upload> uploads = repository.findAll();

        for (Upload upload : uploads) {
            String fileName = upload.getFileName();
            Resource resource = downLoadFile(fileName);
            try {
                files.add(resource.getContentAsByteArray());
            } catch (IOException e) {
                throw new RuntimeException("Issue in reading the file", e);
            }
        }

        return files;
    }

    /////////nuevo////
    public List<String> getFilesFromUploadDirectory() {
        List<String> files = new ArrayList<>();
        try (Stream<Path> paths = Files.walk(fileStoragePath)) {
            paths.filter(Files::isRegularFile)
                    .map(Path::getFileName)
                    .map(Path::toString)
                    .forEach(files::add);
        } catch (IOException e) {
            throw new RuntimeException("Issue in reading the files from upload directory", e);
        }
        return files;
    }
}






///////////////////////////////
//    public List<Upload> getAllFiles() {
//        List<Upload> files = new ArrayList<>();
//
//        try (Stream<Path> paths = Files.walk(uploadPath)) {
//            paths.filter(Files::isRegularFile).forEach(path -> {
//                String fileName = path.getFileName().toString();
//                String url = "/download/" + fileName;
//
//                // Hier kun je andere informatie over het bestand toevoegen, zoals het bestandstype
//                String fileType = ""; // Vul het bestandstype in, bijvoorbeeld "image/jpeg"
//
//                files.add(new Upload(fileName, fileType, url));
//            });
//        } catch (IOException e) {
//            throw new RuntimeException("Fout bij het ophalen van de bestanden.", e);
//        }
//
//        return files;
//    }
//}
/////////////////////////////////////////







//
//@Service
//public class UploadService {
//
//    //    The next line is also possible instead of line 14 and 15
////    private static String storageLocation = "/Users/vanoo/IdeaProjects/upload-download/uploads"
//    @Value("${my.upload_location}")
//    private Path fileStoragePath;
//    private final String fileStorageLocation;
//    private final UploadRepository repository;
//
//    public UploadService(@Value("${my.upload_location}") String fileStorageLocation, UploadRepository repository) {
//        fileStoragePath = Paths.get(fileStorageLocation).toAbsolutePath().normalize();
//
//        this.fileStorageLocation = fileStorageLocation;
//        this.repository = repository;
//
//        try {
//            Files.createDirectories(fileStoragePath);
//        } catch (IOException e) {
//            throw new RuntimeException("Issue in creating file directory");
//        }
//
//
//    }
//
//    public String storeFile(MultipartFile file) {    ////////String url toegevoeg !!!!
//
//        String fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
//
//        Path filePath = Paths.get(fileStoragePath + "\\" + fileName);
//
//        try {
//            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
//        } catch (IOException e) {
//            throw new RuntimeException("Issue in storing the file", e);
//        }
//        repository.save(new Upload(fileName, file.getContentType(), filePath.toString())) ;    //filePath.toString()));  in plaats van url !!
//
//        return fileName;
//    }
//////////////////////////////////////////////from video //////////////////////////////////////////////////
//public Resource downLoadFile(String fileName) {
//    Path path = Paths.get(fileStorageLocation).toAbsolutePath().resolve(fileName);
//    Resource resource;
//
//    try {
//        resource = new UrlResource(path.toUri());
//        if (!resource.exists() || !resource.isReadable()) {
//            throw new RuntimeException("The file doesn't exist or is not readable");
//        }
//    } catch (MalformedURLException e) {
//        throw new RuntimeException("Issue in reading the file", e);
//    }
//    return resource;
//}
//
//
//
///////////////////// from video
//
////    } catch (MalformedURLException e) {
////        throw new RuntimeException("Issue in reading the file", e);
////    }
//
////    if (resource.exists() && resource.isReadable()) {
////        return resource;
////    } else {
////        throw new RuntimeException("The file doesn't exist or is not readable");
////    }}
//////////////////
//
//
//
//
//////////////////////////////////////////////////////////
//
////////////////////////////////////////////////////////////////////////////////////////dit extra als string
////        public List<String> getAllFiles() {
////            List<String> bytes[] = new ArrayList<>();
////            List<Upload> uploads = repository.findAll();
//
//    public List<byte[]> getAllFiles() {
//        List<byte[]> files = new ArrayList<>();
//        List<Upload> uploads = repository.findAll();
//
//        for (Upload upload : uploads) {
//            String fileName = upload.getFileName();
//            Resource resource = downLoadFile(fileName);
//            try {
//                files.add(resource.getContentAsByteArray());
//            } catch (IOException e) {
//                throw new RuntimeException("Issue in reading the file", e);
//            }
//        }
//
//        return files;
//    }
//}
//
//
///////////////////////////////////////////////////// dit als zoektoch
//
////            for (Upload upload : uploads) {
////               files.add(upload.getUrl());
//
//            ////
////                String fileName = upload.getUrl();
////                Resource resource = downLoadFile(fileName);
////                try {
////                    bytes[] .add(resource.getContentAsByteArray());
////                } catch (IOException e) {
////                    throw new RuntimeException("problem with reading the file", e);
////                }
////            }
////
////            return files;
////        }
////}
//
//        /*
//        - maak een for loop die door de "uploads" lijst loopt
//        - In de body van de for-loop:
//               -Haal de "filename" uit de upload en sla die op in een variabele van type String
//               -haal je elke foto op m.b.v de "downloadFile" methode
//               - dan krijg je een resoure terug, sla dit dus op als een variabele van type resource
//               - voeg "resource.getFile().getBytes()" toe aan de "files"-lijst
//        - Na de for loop: return de "files"-lijst
//         */
//
//
//
//       // Path uploads = null;
////        try (Stream<Path> paths = Files.walk(Path.of(fileStorageLocation))) {
////            paths.filter(Files::isRegularFile).forEach(path -> {
////                        String fileName = path.getFileName().toString();
////                        String url = "/download/" + fileName;
////// Hier kun je andere informatie over het bestand toevoegen, zoals het bestandstype
////                String fileType = ""; // Vul het bestandstype in, bijvoorbeeld "image/jpeg"
////
////                files.add(new Upload(fileName, fileType, url));
////            });
////        } catch (IOException e) {
////            throw new RuntimeException("Fout bij het ophalen van de bestanden.", e);
////        }
//
//
//
//
////    public List<Upload>  getDownload() {
////        // Directory path here
////     //  var list = new ArrayList<String>();
////     List<String> files = new ArrayList<String>();
////        File folder = new File(fileStorageLocation);
////        File[] listOfFiles = folder.listFiles();
////
////        for(int i = 0; i < Objects.requireNonNull(listOfFiles).length; i++){
////            if(listOfFiles[i].isFile()){
////               String name = listOfFiles[i].getName();
////                files.add(name);
////            }
////        }
////        List<Upload> Files = null;
////        return Files;
////    }
////
////}