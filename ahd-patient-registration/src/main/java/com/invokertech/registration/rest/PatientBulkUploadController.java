package com.invokertech.registration.rest;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.invokertech.registration.entity.Account;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

@RestController
public class PatientBulkUploadController {

   

    @PostMapping(path="/registrations/upload-csv-file",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)

    public String uploadCSVFile(@RequestParam("file") MultipartFile file) {

      

            // parse CSV file to create a list of `Account` objects
            try (Reader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {

                // create csv bean reader
                CsvToBean<Account> csvToBean = new CsvToBeanBuilder<Account>(reader)
                        .withType(Account.class)
                        .withIgnoreLeadingWhiteSpace(true)
                        .build();

                // convert `CsvToBean` object to list of users
                List<Account> accounts = csvToBean.parse();
               
            
            } catch (Exception ex) {
               
            }
        

        return "file-upload-status";
    }
}