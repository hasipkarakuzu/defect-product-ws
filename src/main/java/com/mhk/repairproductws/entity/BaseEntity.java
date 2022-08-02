package com.mhk.repairproductws.entity;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Data
public class BaseEntity {

  @CreatedBy
  private String createdBy;

  @CreatedDate
  private Long createdAt;

  @LastModifiedBy
  private String lastModifiedBy;

  @LastModifiedDate
  private Long lastModifiedAt;

}
