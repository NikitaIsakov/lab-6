package ru.example;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface JewelryRepository extends JpaRepository<Jewelry, Long> {
    List<Jewelry> findByMaterial(String material);
}