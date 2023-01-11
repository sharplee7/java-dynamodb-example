package com.example.dynamodbdemo.music.service;

import com.example.dynamodbdemo.music.domain.MusicCollection;
import com.example.dynamodbdemo.music.repository.MusicCollectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MusicCollectionService {
    @Autowired
    private MusicCollectionRepository musicRepository;

    public MusicCollection saveMusicCollection(MusicCollection musicCollection) {
        return musicRepository.saveMusicCollection(musicCollection);
    }

    public List<MusicCollection> getMusicCollectionByArtist(String artist) {
        return musicRepository.getMusicCollectionByArtist(artist);
    }

    public String deleteMusicCollectionByArtist(String artist) {
        return  musicRepository.deleteMusicCollectionByArtist(artist);
    }

    public String updateMusicCollection(MusicCollection musicCollection) {
        return musicRepository.updateMusicCollection(musicCollection);
    }
}
