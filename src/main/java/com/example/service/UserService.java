package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.entity.User;
import com.example.repository.UserRepository;

@Service
public class UserService {
    // Repositoryをフィールドに用意する
	private final  UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	  @Autowired
	  public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
	        this.userRepository = userRepository;
	        this.passwordEncoder = passwordEncoder;
	    }
	    public List<User> searchAll() {
	        // ユーザーTBLの内容を全検索
	        return userRepository.findAll();
}
	    public User insert(String name, String email,String password) {
	        // 保存したいEntityクラスのインスタンスを作成する
	        User user = new User();

	        // 引数で受けたname, departmentをEmployeeオブジェクトにセットします
	        user.setName(name);
	        user.setEmail(email);
	        user.setPassword(passwordEncoder.encode(password));

	        // データベースに保存する
	        return this.userRepository.save(user);
	    }
}