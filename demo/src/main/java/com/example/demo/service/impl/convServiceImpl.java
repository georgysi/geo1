package com.example.demo.service.impl;

import com.example.demo.service.convService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.json.CDL;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

@Service
@Slf4j
public class convServiceImpl implements convService {

    @Override
    public String getCSV() {
       String jsonString;
        JSONObject jsonObject;

        try {

            // Step 1: Reading the contents of the JSON file
            // using readAllBytes() method and
            // storing the result in a string
            jsonString = new String(
                    Files.readAllBytes(Paths.get("short.json")));
           System.out.print("GEORGY " + jsonString);
            // Step 2: Construct a JSONObject using above
            // string
            jsonObject = new JSONObject(jsonString);

            // Step 3: Fetching the JSON Array test
            // from the JSON Object
            JSONArray docs
                    = jsonObject.getJSONArray("data");

            // Step 4: Create a new CSV file using
            //  the package java.io.File
            File file = new File("C:\\Users\\georgys\\Desktop\\workspace\\Test.csv");

            // Step 5: Produce a comma delimited text from
            // the JSONArray of JSONObjects
            // and write the string to the newly created CSV
            // file
            String csvString = CDL.toString(docs);
            FileUtils.writeStringToFile(file, csvString);
        }

        // Catch block to handle exceptions
        catch (Exception e) {

            // Display exceptions on console with line
            // number using printStackTrace() method
            e.printStackTrace();
        }

        return "CSV downloaded";
    }

}
