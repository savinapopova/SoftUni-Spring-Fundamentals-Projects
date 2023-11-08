package com.likebookapp.service;

import com.likebookapp.model.entity.Mood;
import com.likebookapp.model.enums.MoodName;

public interface MoodService {
    Mood getMood(MoodName moodName);
}
