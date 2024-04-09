package com.awaken.tool.demo003.service;

import com.awaken.tool.demo003.model.ClassRoom;
import com.awaken.tool.demo003.repository.ClassRoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClassRoomService {
    @Autowired
    private ClassRoomRepository repository;

    public void save(ClassRoom classRoom) {
        repository.save(classRoom);
    }
}
