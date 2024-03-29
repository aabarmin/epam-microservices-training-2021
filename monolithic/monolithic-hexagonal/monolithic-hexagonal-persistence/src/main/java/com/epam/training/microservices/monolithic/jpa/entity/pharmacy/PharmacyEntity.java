package com.epam.training.microservices.monolithic.jpa.entity.pharmacy;

import com.epam.training.microservices.monolithic.model.pharmacy.PharmacyAvailable;
import com.google.common.collect.Sets;
import java.util.Set;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@Table(name = "pharmacies")
@EqualsAndHashCode(exclude = "available")
public class PharmacyEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  private String name;
  private String address;

  @OneToMany(mappedBy = "pharmacy", cascade = CascadeType.ALL)
  private Set<PharmacyAvailable> available = Sets.newHashSet();
}
