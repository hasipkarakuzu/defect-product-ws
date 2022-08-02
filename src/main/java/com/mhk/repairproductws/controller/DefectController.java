package com.mhk.repairproductws.controller;

import com.mhk.repairproductws.entity.Defect;
import com.mhk.repairproductws.service.DefectService;
import javax.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("defect")
public class DefectController {

  private final DefectService defectService;

  public DefectController(DefectService defectService) {
    this.defectService = defectService;
  }

  @PostMapping("create")
  ResponseEntity createDefect(@Valid @RequestBody Defect defect) {
    return defectService.createDefect(defect);
  }

  @GetMapping("list/{name}")
  ResponseEntity getDefectList(@PathVariable String name) {
    return defectService.getDefectList(name);
  }

  @PutMapping("update/{defectId}")
  ResponseEntity updateDefectStatus(@PathVariable Long defectId, @RequestBody Defect defect) {
    return defectService.updateDefect(defectId, defect);

  }

}
