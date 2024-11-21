package com.example.abhijit;

import com.example.abhijit.service.PostService;
import com.example.abhijit.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class AbhijitApplication implements CommandLineRunner {

	@Autowired
	private UserService userService;
	@Autowired
	private PostService postService;

	public static void main(String[] args) {
		SpringApplication.run(AbhijitApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		while (true) {
			try{
				Scanner scanner = new Scanner(System.in);
				while(scanner.hasNextLine()) {
					String input = scanner.nextLine();
					String[] inputArgs = input.split(" ");
					switch (inputArgs[0]) {
						case "RegisterUser":
							if(inputArgs.length != 3) {
								System.out.println("Invalid Input please try again");
								break;
							}
							userService.handleRegisterUser(Long.valueOf(inputArgs[1]),inputArgs[2]);
							break;
						case "UploadPost":
							String[] inputStrings = input.split("\"");
							userService.uploadPost(Long.valueOf(inputArgs[1]), inputStrings[inputStrings.length - 1]);
							break;
						case "InteractWithPost":
							if(inputArgs.length != 4) {
								System.out.println("Invalid Input please try again");
								break;
							}
							postService.interactWithPost(inputArgs[1],Long.valueOf(inputArgs[2]),inputArgs[3]);
							break;
						case "InteractWithUser":
							if(inputArgs.length != 4) {
								System.out.println("Invalid Input please try again");
								break;
							}
							userService.handleUserInteraction(Long.valueOf(inputArgs[3]),Long.valueOf(inputArgs[2]), inputArgs[1]);
							break;
						case "ShowFeed":
							if(inputArgs.length != 2) {
								System.out.println("Invalid Input please try again");
								break;
							}
							postService.showPosts(Long.valueOf(inputArgs[1]));
							break;
						default:
							System.out.println("Unknown Input. Please try again");;
					}
				}
			}catch (Exception e) {
				System.out.printf("New exception occurred: %s%n",e.getMessage());
			}
		}
	}
}

