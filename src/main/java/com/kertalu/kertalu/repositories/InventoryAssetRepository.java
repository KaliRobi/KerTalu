package com.kertalu.kertalu.repositories;

import com.kertalu.kertalu.inventory.InventoryAsset;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InventoryAssetRepository extends JpaRepository<InventoryAsset, Long> {
    List<InventoryAsset> findByUserId(Long clientId);
}
