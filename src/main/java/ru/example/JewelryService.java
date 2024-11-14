package ru.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JewelryService {

    @Autowired
    private JewelryRepository jewelryRepository;

    public List<Jewelry> getAllJewelry() {
        return jewelryRepository.findAll();
    }

    public Jewelry getJewelryById(Long id) {
        return jewelryRepository.findById(id).orElse(null);
    }

    public void saveJewelry(Jewelry jewelry) {
        jewelryRepository.save(jewelry);
    }

    public void deleteJewelry(Long id) {
        jewelryRepository.deleteById(id);
    }

    public List<Jewelry> searchByMaterial(String material) {
        return jewelryRepository.findByMaterial(material);
    }

    public Jewelry updateJewelry(Long id, Jewelry jewelryDetails) {
        Optional<Jewelry> existingJewelry = jewelryRepository.findById(id);

        if (existingJewelry.isPresent()) {
            Jewelry jewelry = existingJewelry.get();

            jewelry.setName(jewelryDetails.getName());
            jewelry.setMaterial(jewelryDetails.getMaterial());
            jewelry.setDescription(jewelryDetails.getDescription());
            jewelry.setWeight(jewelryDetails.getWeight());
            jewelry.setPrice(jewelryDetails.getPrice());

            return jewelryRepository.save(jewelry);
        } else {
            return null;
        }
    }
}