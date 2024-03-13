package com.example.demo;

import com.example.demo.blockchain.BlockChain;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {


	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		String hashes = BlockChain.getBlockHash(680000);
		System.out.println(hashes);
		System.out.println(BlockChain.getAllTransactionalBlocksByHashId(hashes));
	}
}
