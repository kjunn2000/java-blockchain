package com.example;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.InvalidKeyException;
import java.security.Key;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {

    static void configure() {
        Block genesis = new Block( "0" );
        Blockchain.nextBlock(genesis);
    }


    public static void main(String[] arg){
        Key key = SecretCharsKeyGen.keygen();
        String input = "symmetriccryptoexample";
        SymmCrypto symmCrypto = new SymmCrypto();
        try {
            String encrypt = symmCrypto.encrypt(input, key);
            System.out.println(encrypt);
            String decrypt = symmCrypto.decrypt(encrypt, key);
            System.out.println(decrypt);
        } catch (InvalidKeyException | BadPaddingException | IllegalBlockSizeException e) {
            e.printStackTrace();
        }
    }

//    public static void main(String[] args) throws Exception {
//        configure();
//
//        final List<Transaction> transactionList = new ArrayList<>();
//        transactionList.add(new Transaction());
//        List<String> dummyData = getAll("dummytranx.txt");
//        final AtomicInteger count = new AtomicInteger();
//
//        dummyData
//                .forEach(tranx -> {
//                    transactionList.get(transactionList.size()-1).add(tranx);
//                    count.incrementAndGet();
//                    if (count.get() == 5){
//                        Block prevBlock = Blockchain.get().getLast();
//                        Block newBlock = new Block( prevBlock.getHeader().getCurrentHash() );
//                        newBlock.getHeader().setIndex(prevBlock.getHeader().getIndex()+1);
//                        Transaction newTranx = transactionList.get(transactionList.size()-1);
//                        newBlock.setTranx(newTranx);
//                        Blockchain.nextBlock( newBlock);
//                        Blockchain.distribute();
//                        count.set(0);
//                        transactionList.add(new Transaction());
//                    }
//                });
//
//        if (count.get() > 0) {
//            Block prevBlock = Blockchain.get().getLast();
//            Block newBlock = new Block( prevBlock.getHeader().getCurrentHash() );
//            newBlock.getHeader().setIndex(prevBlock.getHeader().getIndex()+1);
//            newBlock.setTranx(transactionList.get(transactionList.size()-1));
//            Blockchain.nextBlock( newBlock);
//            Blockchain.distribute();
//        }
//    }

    public static List<String> getAll( String fileName ) throws IOException {
        File file = new File(fileName);
        if( file.exists() ) {
            return Files.readAllLines( Paths.get(fileName) );
        }else {
            throw new FileNotFoundException("File not found!");
        }
    }

}
