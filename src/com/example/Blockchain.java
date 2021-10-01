package com.example;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.LinkedList;

import com.google.gson.GsonBuilder;

public class Blockchain implements Serializable {

    //master-binary-filename
    private static final String CHAIN_FILE 		= 	"master/chain.bin";

    //data-structure
    private static final LinkedList<Block> DB 	= 	new LinkedList<>();

    //ledger-filename
    private static final String LEDGER_FILE		=	"ledger.txt";

    //nextBlock() : append the block to the chain
    public static void nextBlock( Block newBlock ) {
        DB.add(newBlock);
        persist();
    }

    //persist() : write the chain to the master-file
    private static void persist() {
        /**
         * Helper classes for persisting the object (LinkedList) to the binary-file
         * 	1) FileOutputStream; 2) ObjectOutputStream
         */
        try(
                FileOutputStream fos = new FileOutputStream( CHAIN_FILE );
                ObjectOutputStream out = new ObjectOutputStream( fos );
        ) {
            out.writeObject( DB );
            System.out.println( ">>> Master file updated!" );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //get() : retrieve the chain from the master-file
    public static LinkedList<Block> get(){
        /**
         * Helper classes for reading the object (LinkedList) from the binary-file
         * 	1) FileInputStream; 2) ObjectInputStream
         */
        try(
                FileInputStream fis = new FileInputStream( CHAIN_FILE );
                ObjectInputStream in = new ObjectInputStream( fis );
        ) {
            return (LinkedList<Block>) in.readObject();
        } catch (IOException | ClassNotFoundException e  ) {
            e.printStackTrace();
            return null;
        }
    }

    //distribute() : printout the ledger records (demo)
    public static void distribute() {
        /**
         * convert the chain to the text form using Gson API
         */
        String chain = new GsonBuilder().setPrettyPrinting().create().toJson( DB );
        System.out.println( chain );
        try {
            Files.write(
                    Paths.get( LEDGER_FILE ),
                    chain.getBytes(),
                    StandardOpenOption.CREATE);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}