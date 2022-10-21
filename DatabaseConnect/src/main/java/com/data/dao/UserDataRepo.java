package com.data.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.data.model.UserData;

public interface UserDataRepo extends JpaRepository<UserData, Integer>{

}
