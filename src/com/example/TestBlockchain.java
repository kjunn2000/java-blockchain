package com.example;

public class TestBlockchain {

    public static void main(String[] args) {

        configure();

        tstBlockchain( new String[] { "ali", "peter", "mick", "johnny" }  );

    }

    static void tstBlockchain( String[] lst ) {

        Transaction t1 = new Transaction();
        t1.add( lst[0] );
        t1.add( lst[1] );
        t1.add( lst[2] );
        t1.add( lst[3] );
        Block blk = new Block(
                Blockchain.get().getLast().getHeader().getCurrentHash()
        );
        blk.setTranx(t1);
        Blockchain.nextBlock( blk );

        Blockchain.distribute();
    }

    /**
     * configure the system with a genesis block
     */
    static void configure() {
        Block genesis = new Block( "0" );
        Blockchain.nextBlock(genesis);
    }

}