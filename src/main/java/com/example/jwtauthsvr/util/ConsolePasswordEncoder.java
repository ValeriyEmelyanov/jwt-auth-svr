package com.example.jwtauthsvr.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Scanner;

public class ConsolePasswordEncoder {

    public static void main(String[] args) {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        Scanner scanner = new Scanner(System.in);

        System.out.print("Ведите пароль для кодирования: ");
        String pwd = scanner.nextLine();
        System.out.println(passwordEncoder.encode(pwd));
    }

}
