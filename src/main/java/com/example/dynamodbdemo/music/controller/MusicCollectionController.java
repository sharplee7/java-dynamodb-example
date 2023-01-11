package com.example.dynamodbdemo.music.controller;

import com.example.dynamodbdemo.music.domain.MusicCollection;
import com.example.dynamodbdemo.music.service.MusicCollectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MusicCollectionController {

    @Autowired
    private MusicCollectionService musicCollectionService;

    @PostMapping("/music")
    public MusicCollection saveCustomer(@RequestBody MusicCollection musicCollection) {
        return musicCollectionService.saveMusicCollection(musicCollection);
    }

    @GetMapping("/music/{artist}")
    public List<MusicCollection> getMusicCollectionByArtist(@PathVariable("artist") String artist) {
        return musicCollectionService.getMusicCollectionByArtist(artist);
    }

    @DeleteMapping("/music/{artist}")
    public String deleteMusicCollectionByArtist(@PathVariable("artist") String artist) {
        return  musicCollectionService.deleteMusicCollectionByArtist(artist);
    }

    @PutMapping("/music")
    public String updateMusicCollection(@RequestBody MusicCollection musicCollection) {
        return musicCollectionService.updateMusicCollection(musicCollection);
    }
}
