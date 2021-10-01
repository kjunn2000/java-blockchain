package com.example;

import java.security.MessageDigest;

import org.apache.commons.codec.binary.Hex;

public class Hasher {

    //standard algorithm for hashing
    //md5, sha family

    //hash(String, String) : String
    public static String hash( String data, String algorithm )
    {
        String hash = null;
        //implementation code
        //MessageDigest
        try {
            MessageDigest md = MessageDigest.getInstance(algorithm);
            md.update( data.getBytes() );
            //add salt = add security value (secret of the security solution )
            md.update( Salt.get( 16 ) ); //salt is a random value!
            //digest
            byte[] hashBytes = md.digest();

            //convert byte arr to String type
            //hash = Base64.getEncoder().encodeToString(hashBytes);

            //tohex-recommended!
            //ref: https://commons.apache.org/proper/commons-codec/download_codec.cgi
            hash = String.valueOf( Hex.encodeHex(hashBytes) );

        } catch (Exception e) {
            e.printStackTrace();
        }
        return hash;
    }

    //overloaded hash( String, String, String ) : String
    public static String hash( String data, String salt, String algorithm ) {
        String hash = null;
        try {
            MessageDigest md = MessageDigest.getInstance(algorithm);
            md.update( data.getBytes() );
            md.update( salt.getBytes() ); //salt is a random value!
            //digest
            byte[] hashBytes = md.digest();
            hash = String.valueOf( Hex.encodeHex(hashBytes) );
        } catch (Exception e) {
            e.printStackTrace();
        }
        return hash;
    }

    //put salt value to the hashing
    //salt()

    //overloaded hash() for hashing the block object
    public static String hash( byte[] blockBytes, String algorithm ) {
        String hash = null;
        try {
            MessageDigest md = MessageDigest.getInstance(algorithm);
            byte[] hashBytes = md.digest( blockBytes );
            hash = String.valueOf( Hex.encodeHex(hashBytes) );
        } catch (Exception e) {
            e.printStackTrace();
        }
        return hash;
    }

}
