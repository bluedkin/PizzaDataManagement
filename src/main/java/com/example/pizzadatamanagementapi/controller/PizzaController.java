package com.example.pizzadatamanagementapi.controller;

import com.example.pizzadatamanagementapi.service.DataImportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.ParseException;

@RestController
@RequestMapping("/pizza")
public class PizzaController {
    private final DataImportService dataImportService;
    @Autowired
    public PizzaController(DataImportService dataImportService) {
        this.dataImportService = dataImportService;
    }

    @PostMapping("/import/all")
    public ResponseEntity<String> importAllData(@RequestParam("csvFilePath") String csvFilePath) {
        try {
            dataImportService.importData(csvFilePath);
            return ResponseEntity.ok("All data imported successfully.");
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to import sales data.");
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
    @PostMapping("/import")
    public ResponseEntity<String> importFileData(@RequestParam("csvFilePath") String csvFilePath) {
        try {
            dataImportService.importData(csvFilePath);
            return ResponseEntity.ok("Data imported successfully.");
        } catch (IOException | ParseException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to import sales data.");
        }
    }
}
