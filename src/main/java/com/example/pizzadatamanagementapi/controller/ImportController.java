package com.example.pizzadatamanagementapi.controller;

import com.example.pizzadatamanagementapi.service.DataImportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.text.ParseException;

@RestController
@RequestMapping("/pizza")
public class ImportController {
    private final DataImportService dataImportService;
    @Autowired
    public ImportController(DataImportService dataImportService) {
        this.dataImportService = dataImportService;
    }

    @PostMapping("/import")
    public ResponseEntity<String> importFileData(@RequestParam("filename") String filename) {
        try {
            dataImportService.importData(filename);
            return ResponseEntity.ok("Data imported successfully.");
        } catch (IOException | ParseException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to import sales data.");
        }
    }
}
