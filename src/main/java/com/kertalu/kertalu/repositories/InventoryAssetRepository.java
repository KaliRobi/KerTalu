package com.kertalu.kertalu.repositories;

import com.kertalu.kertalu.inventory.InventoryAsset;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface InventoryAssetRepository extends JpaRepository<InventoryAsset, Long> {
//    List<InventoryAsset> findByUserId(Long clientId);
}
