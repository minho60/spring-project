package com.example.movie.service;

import com.example.movie.domain.Item;
import com.example.movie.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class ItemService {
    
    private final ItemRepository itemRepository;

    public List<Item> getAllItems() {
        return StreamSupport.stream(itemRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    public List<Item> getItemsByType(String type) {
        return itemRepository.findByType(type);
    }

    public Item getItemById(Long id) {
        return itemRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid item ID: " + id));
    }
}
