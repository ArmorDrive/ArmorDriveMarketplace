package ua.com.marketplace.armordrive.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.com.marketplace.armordrive.domain.SaleListing;


public interface SaleListingRepository extends JpaRepository<SaleListing, Long> {
}
