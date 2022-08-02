package com.mhk.repairproductws.repository;

import com.mhk.repairproductws.entity.Defect;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DefectRepository extends JpaRepository<Defect, Long> {

  List<Defect> findByProductOwnerNameEqualsIgnoreCase(String productOwnerName);


}
