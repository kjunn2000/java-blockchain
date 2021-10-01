package com.example;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws Exception {
        Block curr = new Block("0");
        int count = 0;
        Transaction transaction = new Transaction();
        for (String tranx : getAll("dummytranx.txt")) {
            transaction.add(tranx);
            count++;
            if (count == 5){
                Block newBlock = new Block(curr.getHeader().getCurrentHash());
                newBlock.setTranx(transaction);
                transaction = new Transaction();
                curr = newBlock;
                count = 0;
                System.out.println(newBlock);
            }
            
        }
        if (count > 0) {
            Block newBlock = new Block(curr.getHeader().getCurrentHash());
            newBlock.setTranx(transaction);
            System.out.println(newBlock);
        }
    }

    public static List<String> getAll( String fileName ) throws IOException {
        File file = new File(fileName);
        if( file.exists() ) {
            return Files.readAllLines( Paths.get(fileName) );
        }else {
            throw new FileNotFoundException("File not found!");
        }
    }
}
