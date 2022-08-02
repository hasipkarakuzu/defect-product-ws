package com.mhk.repairproductws.service;

import com.mhk.repairproductws.entity.Defect;
import com.mhk.repairproductws.repository.DefectRepository;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class DefectService {

  private final DefectRepository defectRepository;

  public DefectService(DefectRepository defectRepository) {
    this.defectRepository = defectRepository;
  }

  public ResponseEntity createDefect(Defect defect) {
    Map<String, Object> hm = new LinkedHashMap<>();
    hm.put("status", true);
    hm.put("createdDefect", defectRepository.save(defect));

    return new ResponseEntity(hm, HttpStatus.OK);
  }

  public ResponseEntity getDefectList(String ownerName) {
    Map<String, Object> hm = new LinkedHashMap<>();
    Defect defect = new Defect();

    Optional<Defect> optionalDefect = defectRepository.findById(defect.getId());
    if (optionalDefect.isPresent()) {
      List<Defect> defectList = defectRepository.findByProductOwnerNameEqualsIgnoreCase(ownerName);
      hm.put("status", true);
      hm.put("defectList", defectList);
    }
    hm.put("status", true);
    hm.put("defectList", defectRepository.findAll());

    return new ResponseEntity(hm, HttpStatus.OK);
  }

  public ResponseEntity updateDefect(Long defectId, Defect defect) {
    Map<String, Object> hm = new LinkedHashMap<>();
    Optional<Defect> optionalDefect = defectRepository.findById(defectId);
    if (optionalDefect.isPresent()) {
      Defect defectInDb = optionalDefect.get();
      defectInDb.setDefectType(defect.getDefectType());
      defectInDb.setDescription(defect.getDescription());
      defectInDb.setStatus(defect.getStatus());
      hm.put("status", true);
      hm.put("updatedDefect", defectRepository.saveAndFlush(defectInDb));
    } else {
      hm.put("status", false);
      hm.put("message", "an unexpected problem occurred. could not update defect");
    }

    return new ResponseEntity(hm, HttpStatus.OK);
  }

}
