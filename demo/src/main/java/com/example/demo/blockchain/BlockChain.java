package com.example.demo.blockchain;

import com.google.gson.Gson;
import jdk.nashorn.internal.parser.JSONParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BlockChain {


    public static List<Map<String,Object>> getAllTransactionalBlocksByHashId(String hashId) throws IOException {
        List<Map<String, Object>> transactions = null;

        try {
            URL url = new URL("https://blockstream.info/api/block/" + hashId + "/txs/25");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                String response = reader.lines().collect(StringBuilder::new, StringBuilder::append, StringBuilder::append).toString();
                transactions = parseJsonToListMap(response);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return transactions;
    }

    public static String getBlockHash(int blockHeight) {
        String response = null;

        try {
            URL url = new URL("https://blockstream.info/api/block-height/" + blockHeight);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                 response = reader.lines().collect(StringBuilder::new, StringBuilder::append, StringBuilder::append).toString();

            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return response;
    }
//    private static Map<String, Object> parseJsonToSingleMap(String jsonString) {
//        System.out.println(jsonString);
//        Gson gson = new Gson();
//        return gson.fromJson(jsonString, Map.class);
//    }

    private static List<Map<String, Object>> parseJsonToListMap(String jsonString) {
        Gson gson = new Gson();
        return Arrays.asList(gson.fromJson(jsonString, Map[].class));
    }
}
