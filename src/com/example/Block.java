package com.example;

import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.sql.Timestamp;

public class Block implements Serializable{

    private Header header;
    private Transaction tranx;

    public Block( String previousHash ) {
        this.header = new Header();
        this.header.previousHash = previousHash;
        this.header.timeStamp = new Timestamp( System.currentTimeMillis() ).getTime();
        generateHashValue();
    }

    public void setTranx(Transaction tranx) {
        this.tranx = tranx;
        generateMerkleRoot();
    }

    public Header getHeader() {
        return header;
    }

    public void generateMerkleRoot(){
        MerkleTree merkleTree = MerkleTree.getInstance(this.tranx.tranxLst);
        merkleTree.build();
        this.header.merkleRoot = merkleTree.getRoot();
        generateHashValue();
    }

    public void generateHashValue(){
        byte[] blockBytes = getBytes( this );
        this.header.currentHash = Hasher.hash( blockBytes, "SHA-256" );
    }

    private byte[] getBytes( Block block ){
        try( ByteArrayOutputStream baos = new ByteArrayOutputStream();
             ObjectOutputStream out = new ObjectOutputStream( baos );
        ) {
            out.writeObject( block );
            return baos.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public String toString() {
        return "Block [header=" + header + ", tranx=" + tranx + "]";
    }

    public class Header implements Serializable {

        private int index;
        private String currentHash;
        private String previousHash;
        private long timeStamp;
        private String merkleRoot;

        public int getIndex() {
            return index;
        }

        public void setIndex(int index) {
            this.index = index;
        }

        public String getCurrentHash() {
            return currentHash;
        }

        public void setCurrentHash(String currentHash) {
            this.currentHash = currentHash;
        }

        public String getPreviousHash() {
            return previousHash;
        }

        public void setPreviousHash(String previousHash) {
            this.previousHash = previousHash;
        }

        public long getTimeStamp() {
            return timeStamp;
        }

        public void setTimeStamp(long timeStamp) {
            this.timeStamp = timeStamp;
        }

        @Override
        public String toString() {
            return "Header [index=" + index + ", currentHash=" + currentHash + ", previousHash=" + previousHash
                    + ", timeStamp=" + timeStamp + "]";
        }
    }
}