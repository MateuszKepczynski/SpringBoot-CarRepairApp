package com.springapp.carrepairshop.carrepairshop.dao;

import com.springapp.carrepairshop.carrepairshop.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message,Integer>
{
}
