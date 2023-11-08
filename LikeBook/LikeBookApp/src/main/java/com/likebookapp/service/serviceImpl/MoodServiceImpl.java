package com.likebookapp.service.serviceImpl;

import com.likebookapp.model.entity.Mood;
import com.likebookapp.model.enums.MoodName;
import com.likebookapp.repository.MoodRepository;
import com.likebookapp.service.MoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MoodServiceImpl implements MoodService {

    private MoodRepository moodRepository;

    @Autowired
    public MoodServiceImpl(MoodRepository moodRepository) {
        this.moodRepository = moodRepository;
    }

    @Override
    public Mood getMood(MoodName moodName) {
        Mood mood = this.moodRepository.findByName(moodName);
        return mood;
    }
}
